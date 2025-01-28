package com.example.EmployeeManagementSystem.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Data
public class EmployeeDTO {
    @JsonProperty("first_name")
    @NotBlank(message = "First name is required")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "Address is required")
    private String address;

    @Email
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String phone;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
