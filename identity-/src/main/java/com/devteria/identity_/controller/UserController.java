package com.devteria.identity_.controller;

import com.devteria.identity_.dto.request.ApiResponse;
import com.devteria.identity_.dto.request.UserCreatetionRequest;
import com.devteria.identity_.dto.request.UserUpdateRequest;
import com.devteria.identity_.entity.User;
import com.devteria.identity_.service.UserService;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users")
    ApiResponse<User> CreateUser(@RequestBody @Valid UserCreatetionRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
         apiResponse.setResult(userService.createRquest(request));
        return apiResponse;
    }
    @GetMapping("/allUsers")
    List<User> allUser(){
       var authentication =  SecurityContextHolder.getContext().getAuthentication();
       log.info("Username: {}",authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));
        return userService.getAllUsers();
    }
    @GetMapping("/{UserId}")
    User getUser(@PathVariable("UserId") String UserId){
        return userService.getIdUser(UserId);
    }

    @GetMapping("/myInfo")
    User getMyInfo(){
        return userService.getMyInfo();
    }
    @PutMapping("{UserId}")
    User updateUser(@PathVariable("UserId") String UserId, @RequestBody UserUpdateRequest request ){


        return userService.UpdateUser(UserId,request);
    }
    @DeleteMapping("/{UserId}")
    void deteleUserId(@PathVariable("UserId")String UserId){
        userService.deleteUser(UserId);
    }
}
