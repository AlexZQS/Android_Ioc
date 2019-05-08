package com.example.library.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author <a href="mailto:zhangqiushi@snqu.com">张秋实</a>
 * @version ${VERSION}
 * @description ${DESC}
 * @time 2019/5/8 15:37
 */
@Target(ElementType.FIELD) //属性之上
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectView {
    int value();
}
