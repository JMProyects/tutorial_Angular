package com.ccsw.tutorial.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El cliente ya tiene 2 juegos prestados!.")
public class SameClientMoreThanTwoGamesException extends Exception {

    public String msgException;

    public SameClientMoreThanTwoGamesException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }

}
