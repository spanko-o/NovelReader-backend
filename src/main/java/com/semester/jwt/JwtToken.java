package com.semester.jwt;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})//注解可以用在方法上，可以用在类上
@Retention(RetentionPolicy.RUNTIME)//retention。保留策略。在运行时可以保留
public @interface JwtToken {
    boolean required() default true;
}
