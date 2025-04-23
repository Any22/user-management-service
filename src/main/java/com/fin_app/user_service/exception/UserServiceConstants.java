package com.fin_app.user_service.exception;

import org.springframework.stereotype.Component;


public enum UserServiceConstants {

    GENERAL_EXCEPTION_MESSAGE("general.exception"),
    NO_DATA_FOUND_MESSAGE("no.data.found.exception"),
    INVALID_CREDENTIALS_MESSAGE("invalid.credentials"),
    SUCCESSFULL_LOGIN_MESSAGE("successful.login");
    private final String type;

    UserServiceConstants(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return this.type;
    }
}
