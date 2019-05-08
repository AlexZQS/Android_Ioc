package com.example.ioc_study

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.library.InjectManager

/**
 * @description ${DESC}
 * @author <a href="mailto:zhangqiushi@snqu.com">张秋实</a>
 * @time 2019/5/8 13:53
 * @version ${VERSION}
 */
class BaseActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //帮助子类进行布局、控件、事件的注入
        InjectManager.inject(this)
    }
}