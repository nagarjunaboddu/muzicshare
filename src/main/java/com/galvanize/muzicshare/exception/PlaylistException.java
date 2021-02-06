package com.galvanize.muzicshare.exception;

public class PlaylistException extends Exception {


    private String errorType;

    public PlaylistException(String msg, String errorType) {

        super(msg);
        this.errorType = errorType;
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType;
    }

}
