package com.ccsw.tutorial.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("serial")
@ResponseStatus(code = HttpStatus.CONFLICT, reason = "El rango de fechas introducida es incorrecta. Vuelva a introducirla.")
public class InvalidDateRangeException extends Exception {
    public String msgException;

    public InvalidDateRangeException(String msgException) {
        this.setMsgException(msgException);
    }

    public String getMsgException() {
        return msgException;
    }

    public void setMsgException(String msgException) {
        this.msgException = msgException;
    }

}
