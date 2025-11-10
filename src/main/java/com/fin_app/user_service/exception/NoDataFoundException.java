package com.fin_app.user_service.exception;
import java.io.Serial;

public class NoDataFoundException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

//        public NoDataFoundException() {
//            super();
//        }

        public NoDataFoundException(String message) {
            super(message);
        }

}

