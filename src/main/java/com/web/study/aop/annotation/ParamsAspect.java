package com.web.study.aop.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import lombok.extern.slf4j.Slf4j;


@Retention(RUNTIME)
@Target(METHOD)
public @interface ParamsAspect {

	
}
