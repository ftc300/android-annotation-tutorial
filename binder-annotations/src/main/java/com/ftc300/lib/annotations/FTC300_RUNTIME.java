package com.ftc300.lib.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Comment:
 * Author: ftc300
 * Date: 2018/9/13
 * Blog: www.ftc300.pub
 * GitHub: https://github.com/ftc300
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FTC300_RUNTIME {
    String value();
}
