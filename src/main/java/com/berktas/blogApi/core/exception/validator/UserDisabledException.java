package com.berktas.blogApi.core.exception.validator;

import com.berktas.blogApi.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class UserDisabledException extends BaseException {
    public UserDisabledException(String message) {
        super(message);
    }
}
