package com.ccsw.tutorial.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El nombre del cliente ya existe! Introduzca otro.")
public class AlreadyExistsException extends Exception {
    public String msgException;

    public AlreadyExistsException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }

}
