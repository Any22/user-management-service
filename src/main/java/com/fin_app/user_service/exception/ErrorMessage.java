package com.fin_app.user_service.exception;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
    private int errorCode;
    private String message;
}

