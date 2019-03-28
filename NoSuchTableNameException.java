package org.apache.ibatis.test;

public class NoSuchTableNameException extends RuntimeException{

    private static final long serialVersionUID = 3880206498166270512L;

    public NoSuchTableNameException() {
        super();
    }

    public NoSuchTableNameException(String message) {
        super(message);
    }

    public NoSuchTableNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoSuchTableNameException(Throwable cause) {
        super(cause);
    }
}
