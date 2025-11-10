package com.fin_app.user_service.exception;

public enum UserServiceConstants {

    GENERAL_EXCEPTION_MESSAGE("general.exception"),
    NO_DATA_FOUND_MESSAGE("no.data.found.exception"),
    INVALID_CREDENTIALS_MESSAGE("invalid.credentials"),
    USER_WITH_EMAIL_ALREADY_EXISTS("user.emailId.already.exists"),
    SUCCESSFUL_LOGIN_MESSAGE("successful.login"),
    ID_DOES_NOT_EXIST("no.such.id.found");
    private final String type;

    UserServiceConstants(String type) {
        this.type = type;
    }
    @Override
    public String toString(){
        return this.type;
    }
}
