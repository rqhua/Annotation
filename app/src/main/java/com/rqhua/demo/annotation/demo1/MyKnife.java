package com.rqhua.demo.annotation.demo1;

import android.app.Activity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2018/6/25.
 */

public class MyKnife {
    public static void bind(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        //获取所有属性
        Field[] fields = clazz.getFields();
        if (fields != null && fields.length > 0)
            for (int i = 0; i < fields.length; i++) {
                //反射获取 MyBindView 注解
                Field field = fields[i];
                MyBindView bindView = fields[i].getAnnotation(MyBindView.class);
                if (bindView != null) {
                    //获取int值，View id
                    int viewId = bindView.value();
                    // 获取findViewById的Method实例
                    try {
                        // 获取findViewById的Method实例
                        Method findViewMethod = clazz.getMethod("findViewById", int.class);
                        //调用findViewById 获取Veiw
                        Object view = findViewMethod.invoke(activity, viewId);
                        //设置可操作性
                        field.setAccessible(true);
                        //将结果赋值给 对象的 field
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
}
