package com.berktas.blogApi.core.exception;

import com.berktas.blogApi.core.exception.base.BaseException;
import lombok.Getter;

@Getter
public class IncorrectEntryException extends BaseException {
    public IncorrectEntryException(String message) {
        super(message);
    }
}
