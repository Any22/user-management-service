package com.fin_app.user_service.dto;

import jakarta.validation.constraints.Email;
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
public class CustomerRequest {

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastName;

    private String accountType;

    @NotBlank(message = "Email address is mandatory")
    @Email
    private String emailAddress;

    private Long contactNumber;

    @NotBlank(message = "Postal address is mandatory")
    private Address postalAddress;

}

