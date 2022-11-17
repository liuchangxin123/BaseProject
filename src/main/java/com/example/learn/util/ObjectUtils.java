package com.example.learn.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * @ClassName ObjectUtils
 * @Description 判断对象是否完全为空工具类
 * @Date 2022/11/17 11:07
 * @Author pluto
 */
@Slf4j
public class ObjectUtils {

    /**
     * 判断对象是否完全为空
     *
     * @param object
     * @return
     */
    public static boolean objectCheckIsNotNull(Object object) {
        boolean flag = false; //定义返回结果，默认为false

        if (Objects.isNull(object)) {
            flag = false;
        } else {
            Class clazz = (Class) object.getClass(); // 得到类对象
            Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
            for (Field field : fields) {
                field.setAccessible(true);
                Object fieldValue = null;
                try {
                    fieldValue = field.get(object); //得到属性值
                    Type fieldType = field.getGenericType();//得到属性类型
                    String fieldName = field.getName(); // 得到属性名
                    log.info("属性类型：" + fieldType + ",属性名：" + fieldName + ",属性值：" + fieldValue);
                } catch (IllegalArgumentException e) {
                    log.error(e.getMessage(), e);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(), e);
                }
                if (fieldValue != null) {  //只要有一个属性值不为null 就返回true 表示对象不为null
                    flag = true;
                    break;
                }
            }
        }

        return flag;
    }
}
