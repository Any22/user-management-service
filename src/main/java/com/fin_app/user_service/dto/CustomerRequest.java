package com.fin_app.user_service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {

    @NotBlank(message = "User name is required")
    @Pattern(regexp = "^[a-z]+$")
    private String userName ;

    @NotBlank(message = "Password of at-least 8 characters is required")
    @Pattern(regexp = "^[a-zA-Z0-9@$&]{8,}$")
    private String userPassword;

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastName;


    @Email
    private String emailAddress;

    private Long contactNumber;

    @Valid
    @NotNull(message = "Postal Address is required")
    private Address postalAddress;

    private String accountType;


//    public String getUserPassword() {
//        return "*".repeat(userPassword.length()); // Always return masked value
//    }
//
//
//    // Optional: If you need the actual password internally
//    public String getActualPassword() {
//        return userPassword;
//    }

}

