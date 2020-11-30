package com.gxyan.gmall.common.exception;

import lombok.Data;

/**
 * @author gxyan
 * @date 2020/11/30 0:57
 */
@Data
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String msg;
    private int code = 500;

    public ServiceException() {
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }
}
