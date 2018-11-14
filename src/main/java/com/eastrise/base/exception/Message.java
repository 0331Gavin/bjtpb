package com.eastrise.base.exception;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Message {
    @AliasFor("value")
    String msg() default "";

    @AliasFor("msg")
    String value() default "";
}
