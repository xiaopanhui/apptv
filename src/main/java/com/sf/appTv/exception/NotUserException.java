package com.sf.appTv.exception;

public class NotUserException extends BaseException{
    public NotUserException() {
        super(101, "没有用户");
    }
}
