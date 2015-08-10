package com.kingofthehill.exceptions;

public class UnableToUploadToS3Exception extends RuntimeException {

    public UnableToUploadToS3Exception(Exception e) {
        super(e);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1968560269846444015L;

}
