package com.employeework.exception;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;

    public String getRequest() {
        return request;
    }

    private String request;
 552525ajrtrwioj jiotwiolkakglj gitchanges



    public ErrorDetails(Date date, String message, String request) {
        this.date = date;
        this.message = message;
        this.request = request;
    }



    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
