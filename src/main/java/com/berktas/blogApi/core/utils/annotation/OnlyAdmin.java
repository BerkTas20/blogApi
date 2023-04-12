package com.berktas.blogApi.core.utils.annotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@IsAuthenticated
@PreAuthorize("hasRole('ROLE_ADMIN')")
public @interface OnlyAdmin {
}
