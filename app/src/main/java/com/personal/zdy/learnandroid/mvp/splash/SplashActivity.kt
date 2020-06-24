package com.personal.zdy.learnandroid.mvp.splash

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.base.BaseActivity
import com.personal.zdy.learnandroid.base.IView
import com.personal.zdy.learnandroid.mvp.login.LoginActivity
import com.personal.zdy.learnandroid.util.ImageUtils
import kotlinx.android.synthetic.main.activity_splash.*
import kotlinx.coroutines.*
import kotlinx.coroutines.GlobalScope
import java.util.*

/**
 * 闪屏Activity
 *
 * @author zhangdongyang
 * @date 2020/04/14
 */
class SplashActivity : BaseActivity<SplashPresenter>() {

    // 倒计时结束时间(秒)
    private var recLen = 5
    private val timer = Timer()
    private val context = this
    private val timerTask = object : TimerTask() {
        override fun run() {
            GlobalScope.launch(Dispatchers.Main) {
                recLen--
                text_rec.text = "跳过 $recLen"
                if (recLen < 0) {
                    timer.cancel()
                    text_rec.visibility = View.GONE
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun initView() {
        super.initView()
        text_rec.setOnClickListener {
            timerTask.cancel()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun initData() {
        // 加载网络图片
        Glide.with(this)
            .load(ImageUtils.getAssetsSplashPicture(this))
            .error(R.mipmap.ic_launcher)
            .into(image_splash)

        timer.schedule(timerTask, 0, 1000)
    }

    override fun initLayout(): Int {
        return R.layout.activity_splash
    }

    override fun onBindPresenter(): SplashPresenter {
        return SplashPresenter()
    }
}