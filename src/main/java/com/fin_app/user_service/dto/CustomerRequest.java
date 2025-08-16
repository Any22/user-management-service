package com.fin_app.user_service.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.UniqueConstraint;
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
@JsonIgnoreProperties(value={"userPassword"}, allowSetters = true)    //static filter applied , explore dynamic filtering as well
public class CustomerRequest {

    @NotBlank(message = "user name is mandatory field")
    @Pattern(regexp = "^[a-z]+$",message="{userName.validation}")
    private String userName ;

    @NotBlank(message = "Password of at-least 8 characters is required")
    @Pattern(regexp = "^[a-zA-Z0-9@$&]{8,}$", message="{password.validation}")
    private String userPassword;

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[A-Z][a-z]+(?:\\s[A-Z][a-z]+)*$",message = "First letter should start with capital letter")
    private String firstName;

    @NotBlank(message = "Last Name should not be empty")
    @Pattern(regexp = "^[A-Z][a-z]+$")
    private String lastName;

    @Email(message="Please provide a valid email id")
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

