package com.personal.zdy.learnandroid.base

import android.app.Application
import android.content.Context
import android.text.TextUtils
import android.util.Log
import com.argusapm.android.api.ApmTask
import com.argusapm.android.api.Client
import com.argusapm.android.core.Config
import com.argusapm.android.network.cloudrule.RuleSyncRequest
import com.argusapm.android.network.upload.CollectDataSyncUpload
import com.personal.zdy.learnandroid.R
import com.personal.zdy.learnandroid.util.ProcessUtils

/**
 * @author zhangdongyang
 * @date 2020/04/14
 */
class BaseApplication : Application() {

    private val TAG = this.javaClass.simpleName

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        Log.d(TAG,"Init Application")
        // 注：根据实际情况，来选择主进程
        // 如果您有常驻进程，则主进程是常驻进程
        // 如果您是单进程模型，则主进程是UI进程
        val isMainProcess = TextUtils.equals(packageName, ProcessUtils.getProcessName(this))
        val builder = Config.ConfigBuilder()
            .setAppContext(this)
            .setAppName(getString(R.string.app_name))
            .setRuleRequest(RuleSyncRequest())
            .setUpload(CollectDataSyncUpload())
            .setAppVersion(getString(R.string.app_version))
            .setApmid(getString(R.string.qihoo_app_key))

        //单进程应用可忽略builder.setDisabled相关配置。
        if (!isMainProcess) {
            //除了“主进程”，其他进程不需要进行数据上报、清理等逻辑。“主进程”通常为常驻进行，如果无常驻进程，即为UI进程。
            builder.setDisabled(ApmTask.FLAG_DATA_CLEAN)  //只有主进程才清理数据
                .setDisabled(ApmTask.FLAG_CLOUD_UPDATE)//只有主进程才执行云控
                .setDisabled(ApmTask.FLAG_DATA_UPLOAD)//只有主进程才执行数据上报
                .setDisabled(ApmTask.FLAG_COLLECT_ANR)//只有主进程才收集ANR
                .setDisabled(ApmTask.FLAG_COLLECT_BATTERY)  //只有主进程才收集电量
                .setDisabled(ApmTask.FLAG_COLLECT_FILE_INFO) //只有主进程才收集文件信息
                .setDisabled(ApmTask.FLAG_COLLECT_CPU);//只有主进程才收集CPU数据
        }
        //builder.setEnabled(ApmTask.FLAG_COLLECT_ACTIVITY_AOP); //activity采用aop方案时打开，默认关闭即可。
        //builder.setEnabled(ApmTask.FLAG_LOCAL_DEBUG); //是否读取本地配置，默认关闭即可。

        Client.attach(builder.build())
        // Client.isDebugOpen(true, getPackageName());//  是否展示debug模式悬浮窗。根据项目需求添加
        Client.startWork();
    }

    override fun getApplicationContext(): Context {
        return super.getApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}