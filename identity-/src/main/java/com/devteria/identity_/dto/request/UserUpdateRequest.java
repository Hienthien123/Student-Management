package com.devteria.identity_.dto.request;

import com.devteria.identity_.entity.Role;
import com.devteria.identity_.validator.DobConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter

public class UserUpdateRequest {

    private  String password;

    private String firtsName;

    private  String lastName;
    @DobConstraint(min =18,message = "INVALID_DOB")
    private LocalDate dob;
    List<String> roles;
}
