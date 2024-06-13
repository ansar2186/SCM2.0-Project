package com.scm.mapper;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private String about;

}
