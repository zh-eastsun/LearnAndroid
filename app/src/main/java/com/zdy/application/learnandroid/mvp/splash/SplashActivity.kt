package com.zdy.application.learnandroid.mvp.splash

import android.content.Intent
import android.view.View
import com.bumptech.glide.Glide
import com.zdy.application.learnandroid.R
import com.zdy.application.common.base.mvp.BaseActivity
import com.zdy.application.learnandroid.mvp.login.LoginActivity
import com.zdy.application.common.util.ImageUtils
import com.zdy.application.learnandroid.databinding.ActivitySplashBinding
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

    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    // 倒计时结束时间(秒)
    private var recLen = 5
    private val timer = Timer()
    private val context = this
    private val timerTask = object : TimerTask() {
        override fun run() {
            GlobalScope.launch(Dispatchers.Main) {
                recLen--
                binding.textRec.text = "跳过 $recLen"
                if (recLen < 0) {
                    timer.cancel()
                    binding.textRec.visibility = View.GONE
                    val intent = Intent(context, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun initView() {
        super.initView()
        binding.textRec.setOnClickListener {
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
            .into(binding.imageSplash)

        timer.schedule(timerTask, 0, 1000)
    }

    override fun initLayout(): View {
        return binding.root
    }

    override fun onBindPresenter(): SplashPresenter {
        return SplashPresenter(this)
    }
}