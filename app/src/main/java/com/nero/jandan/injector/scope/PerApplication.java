package com.nero.jandan.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by lishiyan on 16/12/14.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerApplication {
}
