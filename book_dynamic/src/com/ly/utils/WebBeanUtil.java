package com.ly.utils;

import com.ly.entity.User;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebBeanUtil {
    public static <T> T copyParameterToBean(Map value, T bean){
        try {
            System.out.println("注入之前"+bean);
            BeanUtils.populate(bean,value);
            System.out.println("注入之后"+bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }
    public static int strToInt(String strInt,int defaultValue){
        try {
            return Integer.parseInt(strInt);
        }catch (Exception e){
//            e.printStackTrace();
        }
        return defaultValue;
    }
}
