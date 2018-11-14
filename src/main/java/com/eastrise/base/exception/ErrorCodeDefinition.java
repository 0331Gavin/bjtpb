package com.eastrise.base.exception;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
@Inherited
public @interface ErrorCodeDefinition {
    @AliasFor("thousands")
    int value() default 1;

    @AliasFor("value")
    int thousands() default 1;
}
