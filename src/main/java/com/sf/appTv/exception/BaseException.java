package com.sf.appTv.exception;

public class BaseException  extends RuntimeException{
    private int error;

    public int getError() {
        return error;
    }
    public BaseException( int error,String message) {
        super(message);
        this.error = error;
    }
    public BaseException(String message, Throwable cause, int error) {
        super(message, cause);
        this.error = error;
    }


}
