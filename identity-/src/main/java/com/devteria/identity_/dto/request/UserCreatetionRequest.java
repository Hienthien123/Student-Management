package com.devteria.identity_.dto.request;

import java.time.LocalDate;

import com.devteria.identity_.validator.DobConstraint;
import jakarta.validation.constraints.Size;


import lombok.*;
import lombok.experimental.FieldDefaults;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreatetionRequest {
    @Size(min = 3 ,message = "USERNAME_INVALID")
    private  String username;
    @Size(min = 8, message = "PASSWORD_INVALID")
    private  String password;
    private String firtsName;

    private  String lastName;
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
    @DobConstraint(min =18,message = "INVALID_DOB")
    private LocalDate dob;
}
