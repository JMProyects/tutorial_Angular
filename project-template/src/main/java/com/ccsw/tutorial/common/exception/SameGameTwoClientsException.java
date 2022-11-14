package com.ccsw.tutorial.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Este juego ya está reservado. Seleccione otro diferente.")
public class SameGameTwoClientsException extends Exception {
    public String msgException;

    public SameGameTwoClientsException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }

}
