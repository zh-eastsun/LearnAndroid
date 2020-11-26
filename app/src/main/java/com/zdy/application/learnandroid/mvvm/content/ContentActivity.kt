package com.zdy.application.learnandroid.mvvm.content

import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.zdy.application.common.base.mvvm.BaseActivity
import com.zdy.application.learnandroid.R
import com.zdy.application.learnandroid.databinding.ActivityContentBinding
import com.zdy.application.learnandroid.mvvm.home.HomeFragment
import com.zdy.application.learnandroid.mvvm.personal.PersonalFragment
import com.zdy.application.learnandroid.mvvm.square.SquareFragment
import com.zdy.application.learnandroid.mvvm.system.SystemFragment
import kotlinx.android.synthetic.main.activity_content.*

/**
 * Created by Android Studio.
 * User: zh.eastsun
 * Date: 11/20/20
 * Time: 11:52 PM
 */

class ContentActivity : BaseActivity(), View.OnClickListener {

    // 四个不同业务的Fragment
    private var homePageFragment: HomeFragment? = null
    private var systemPageFragment: SystemFragment? = null
    private var squarePageFragment: SquareFragment? = null
    private var personalPageFragment: PersonalFragment? = null
    private val fragmentManager by lazy { supportFragmentManager }

    private val binding by lazy { ActivityContentBinding.inflate(layoutInflater) }

    override fun otherOperate() {
        initView()
        setTabSelection(0)
    }

    override fun bindView() {
        setContentView(binding.root)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.home_page_layout -> setTabSelection(0)
            R.id.system_page_layout -> setTabSelection(1)
            R.id.square_page_layout -> setTabSelection(2)
            R.id.personal_page_layout -> setTabSelection(3)
        }
    }

    fun initView(){
        binding.homePageLayout.setOnClickListener(this)
        binding.systemPageLayout.setOnClickListener(this)
        binding.squarePageLayout.setOnClickListener(this)
        binding.personalPageLayout.setOnClickListener(this)
    }

    /**
     *  设置某一个Tab的选中状态
     *
     *  @param index 0:home 1:system 2:square 3:personal
     */
    private fun setTabSelection(index: Int) {
        // 先清除所有选中状态
        clearSelection()
        // 隐藏掉所有Fragment
        val fragmentTransaction = fragmentManager.beginTransaction()
        hideFragments(fragmentTransaction)

        when (index) {
            0 -> {
                home_page_image.setImageResource(R.drawable.home_icon_selected)
                home_page_text.setTextColor(resources.getColor(R.color.sugar_blue))
                if (homePageFragment == null) {
                    homePageFragment = HomeFragment()
                    fragmentTransaction.add(R.id.fragment_content, homePageFragment!!)
                } else {
                    fragmentTransaction.show(homePageFragment!!)
                }
            }

            1 -> {
                system_page_image.setImageResource(R.drawable.system_icon_selected)
                system_page_text.setTextColor(resources.getColor(R.color.sugar_blue))
                if (systemPageFragment == null) {
                    systemPageFragment = SystemFragment()
                    fragmentTransaction.add(R.id.fragment_content, systemPageFragment!!)
                } else {
                    fragmentTransaction.show(systemPageFragment!!)
                }
            }

            2 -> {
                square_page_image.setImageResource(R.drawable.square_icon_selected)
                square_page_text.setTextColor(resources.getColor(R.color.sugar_blue))
                if (squarePageFragment == null) {
                    squarePageFragment = SquareFragment()
                    fragmentTransaction.add(R.id.fragment_content, squarePageFragment!!)
                } else {
                    fragmentTransaction.show(squarePageFragment!!)
                }
            }

            3 -> {
                personal_page_image.setImageResource(R.drawable.personal_icon_selected)
                personal_page_text.setTextColor(resources.getColor(R.color.sugar_blue))
                if (personalPageFragment == null) {
                    personalPageFragment = PersonalFragment()
                    fragmentTransaction.add(R.id.fragment_content, personalPageFragment!!)
                } else {
                    fragmentTransaction.show(personalPageFragment!!)
                }
            }
        }

        fragmentTransaction.commit()
    }

    private fun hideFragments(transition: FragmentTransaction) {
        if (homePageFragment != null) {
            transition.hide(homePageFragment!!)
        }
        if (systemPageFragment != null) {
            transition.hide(systemPageFragment!!)
        }
        if (squarePageFragment != null) {
            transition.hide(squarePageFragment!!)
        }
        if (personalPageFragment != null) {
            transition.hide(personalPageFragment!!)
        }
    }

    /**
     * 清除所有选中状态
     */
    private fun clearSelection() {
        binding.homePageImage.setImageResource(R.drawable.home_icon_unselected)
        binding.systemPageImage.setImageResource(R.drawable.system_icon_unselected)
        binding.squarePageImage.setImageResource(R.drawable.square_icon_unselected)
        binding.personalPageImage.setImageResource(R.drawable.personal_icon_unselected)

        binding.homePageText.setTextColor(resources.getColor(R.color.sugar_green))
        binding.systemPageText.setTextColor(resources.getColor(R.color.sugar_green))
        binding.squarePageText.setTextColor(resources.getColor(R.color.sugar_green))
        binding.personalPageText.setTextColor(resources.getColor(R.color.sugar_green))
    }

}