package com.fin_app.user_service.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "user name is mandatory field")
    @Pattern(regexp = "^[a-z]+$",message="${userName.validation}")
    private String userName;

    @NotBlank(message = "Password of at-least 8 characters is required")
    @Pattern(regexp = "^[a-zA-Z0-9@$&]{8,}$", message="${password.validation}")
    private String password;

}
