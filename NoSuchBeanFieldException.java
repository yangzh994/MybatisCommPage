package org.apache.ibatis.test;

public class NoSuchBeanFieldException extends RuntimeException{

    private static final long serialVersionUID = 3880206998166270531L;

    public NoSuchBeanFieldException() {
        super();
    }

    public NoSuchBeanFieldException(String message) {
        super(message);
    }

    public NoSuchBeanFieldException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchBeanFieldException(Throwable cause) {
        super(cause);
    }

}
