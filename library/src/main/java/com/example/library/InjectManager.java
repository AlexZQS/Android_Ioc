package com.example.library;

import android.app.Activity;
import com.example.library.annotations.ContentView;
import com.example.library.annotations.InjectView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author <a href="mailto:zhangqiushi@snqu.com">张秋实</a>
 * @version ${VERSION}
 * @description 注入管理类
 * @time 2019/5/8 13:58
 */
public class InjectManager {
    public static void inject(Activity activity) {

        //布局的注入
        injectLayout(activity);


        //控件的注入
        injectViews(activity);


        //事件的注入
        injectEvents(activity);
    }

    //事件的注入
    private static void injectEvents(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获取类之上的注解
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            //获取这个注解的值
            int layoutId = contentView.value();
            //第一种方式
            //activity.setContentView(layoutId);

            try {
                //获取setContentView(R.layout.activity_main) 方法
                Method setContentView = clazz.getMethod("setContentView", int.class);
                //执行setContentView(R.layout.activity_main) 方法
                setContentView.invoke(activity, layoutId);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

    //控件的注入
    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //类的所有属性
        Field[] fields = clazz.getDeclaredFields();
        //循环每个属性
        for (Field field : fields) {
            // 获取属性的注解
            InjectView injectView = field.getAnnotation(InjectView.class);
            if (injectView != null) {
                //获取注解的值
                int viewId = injectView.value();
                //第一种方式
//                View view = activity.findViewById(viewId);

                try {
                    //获取findViewById(R.id.btn)方法
                    Method method = clazz.getMethod("findViewById", int.class);
                    Object view = method.invoke(activity, viewId);


                    //设置访问权限, private
                    field.setAccessible(true);

                    //属性的值赋给控件，在当前MainActivity
                    field.set(activity, view);


                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    //布局的注入
    private static void injectLayout(Activity activity) {

    }
}
