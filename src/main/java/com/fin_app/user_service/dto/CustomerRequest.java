package com.fin_app.user_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties
@JsonIgnoreProperties(value={"userPassword"}, allowSetters = true)    //static filter applied , explore dynamic filtering as well
public class CustomerRequest {
    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$",message = "First letter should be some capital letter")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastName;

    @Valid
    @NotNull(message = "Postal Address is required")
    private Address postalAddress;

    private Long contactNumber;

    private String accountType;

    @NotBlank(message = "Email is required")
    @Email(message="Please provide a valid email id")
    private String emailAddress;

    @NotBlank(message = "Password of at-least 8 characters is required")
    @Pattern(regexp = "^[a-zA-Z0-9@$&]{8,}$", message="{password.validation}")
    private String userPassword;
}

