package com.scm.mapper;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "User Name is required !")
    @Size(min = 3 ,message = "Minimum 3 Character is required")
    private String firstName;

    private String lastName;

    @Email(message = "Invalid Email address")
    private String email;

    @NotBlank(message = "Password is required !")
    @Size(min = 6,max = 10, message = "Minimum 6 character is required and Maximum 10 character is required")
    private String password;

    @NotBlank(message = "Phone number is required !")
    @Size(min = 8, max = 12, message = "Minimum 8 number is required and Maximum 12 Number is required")
    private String phoneNumber;

    @NotBlank(message = "About is required")
    private String about;

}
