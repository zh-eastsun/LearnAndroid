package com.personal.zdy.learnandroid.mvp.splash

import android.content.Intent
import android.os.Handler
import android.view.View
import android.view.WindowManager
import com.bumptech.glide.Glide
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.base.BaseActivity
import com.personal.zdy.learnandroid.mvp.login.LoginActivity
import com.personal.zdy.learnandroid.net.RequestApi
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * 闪屏Activity
 *
 * @author zhangdongyang
 * @date 2020/04/14
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContract.View {

    // 倒计时结束时间(秒)
    private var recLen = 5
    private val timer = Timer()
    private val timerTask = object : TimerTask() {
        override fun run() {
            MainScope().launch {
                recLen--
                text_rec.text = "跳过 $recLen"
                if (recLen < 0) {
                    timer.cancel()
                    text_rec.visibility = View.GONE
                }
            }
        }
    }
    private val handler = Handler()


    override fun doSthBeforeSetContentView() {
        super.doSthBeforeSetContentView()
        // 设置全屏
        val flag = WindowManager.LayoutParams.FLAG_FULLSCREEN
        window.setFlags(flag, flag)
    }

    override fun initView() {
        super.initView()
        text_rec.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun initData() {
        // 加载网络图片
        Glide.with(this)
            .load(RequestApi.SPLASH_URL)
            .error(R.mipmap.ic_launcher)
            .into(image_splash)

        timer.schedule(timerTask, 1000, 1000)
    }

    override fun initLayout(): Int {
        return R.layout.activity_splash
    }

    override fun onBindPresenter(): SplashPresenter {
        return SplashPresenter()
    }

    override fun doWork() {
        super.doWork()
        handler.postDelayed({
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}