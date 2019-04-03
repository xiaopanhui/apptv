package com.sf.appTv.exception;

public class NoFondException extends BaseException {

    public NoFondException() {
        super(400, "No Found");
    }
}
