package com.fin_app.user_service.exception;
import java.io.Serial;

public class DuplicateRecordExist extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public DuplicateRecordExist(String message) {
        super(message);
    }
}

