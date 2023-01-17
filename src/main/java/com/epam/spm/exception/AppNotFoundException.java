package com.epam.spm.exception;

public class AppNotFoundException extends IllegalArgumentException {
    ErrorCode code;
    //todo another exception

    public AppNotFoundException(String s, ErrorCode code) {
        super(s);
        this.code = code;
    }

    public ErrorCode getErrorCode() {
        return code;
    }
}
