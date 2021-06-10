package com.ns.cjcq.security.validator.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidatorException extends AuthenticationException {

    public ValidatorException(String msg) {
        super(msg);
    }
}
