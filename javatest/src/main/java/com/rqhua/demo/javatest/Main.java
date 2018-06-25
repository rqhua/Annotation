package com.rqhua.demo.javatest;

import java.lang.reflect.Field;

public class Main {

    @ReflectParam("Rqhua")
    public String username;

    public static void main(String[] args) {
        reflectGetAnnotion();
    }

    //test-1 反射获取注解信息
    private static void reflectGetAnnotion() {
        Main main = new Main();
        ReflectParam annotation = null;

        Class clazz = main.getClass();
        try {
            Field field = clazz.getField("username");
            annotation = field.getAnnotation(ReflectParam.class);
            System.out.println(annotation.value());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
