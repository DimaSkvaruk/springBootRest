package com.example.work.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionDetails {

    private String message;
    private HttpStatus httpStatus;
    private Date dateTime;

    public ExceptionDetails(String message, HttpStatus httpStatus, Date dateTime) {
        this.message = message;
        this.httpStatus = httpStatus;
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
