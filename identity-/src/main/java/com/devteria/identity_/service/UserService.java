package com.devteria.identity_.service;
import com.devteria.identity_.dto.request.UserCreatetionRequest;
import com.devteria.identity_.dto.request.UserUpdateRequest;
import com.devteria.identity_.dto.response.UserResponse;
import com.devteria.identity_.entity.User;
import com.devteria.identity_.enums.Role;
import com.devteria.identity_.exception.AppException;
import com.devteria.identity_.exception.ErrorCode;
import com.devteria.identity_.repository.RoleRepository;
import com.devteria.identity_.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    @Autowired
    private  UserRepository userRepository ;
    RoleRepository roleRepository;
    PasswordEncoder passwordEncoder;
    public User createRquest(UserCreatetionRequest request){

        User user = new User();
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        user.setUsername(request.getUsername());
        user.setFirtsName(request.getFirtsName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());


        user.setDob(request.getDob());
        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

       // user.setRoles(roles);

        return userRepository.save(user);
    }
    @PreAuthorize("hasRole('ADMIN')")

    public List<User> getAllUsers(){
        log.info("In method get Users");
        return userRepository.findAll();
    }
    public User getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        log.info("Myinfo");

        User user  = userRepository.findByUsername(name).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return user;
    }
    @PostAuthorize("returnObject.username = authentication.name")
    public User getIdUser(String id){
        log.info("In method get Users by ID");
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public User UpdateUser(String id, UserUpdateRequest request){
        User user = getIdUser(id);

        user.setFirtsName(request.getFirtsName());
        user.setLastName(request.getLastName());
        user.setPassword(request.getPassword());
        user.setDob(request.getDob());

        user.setPassword(passwordEncoder.encode(request.getPassword()));;
        var roles = roleRepository.findAllById(request.getRoles());
        user.setRoles(new HashSet<>(roles));

        return userRepository.save(user);
    }
    public void deleteUser(String id){
        userRepository.deleteById(id);
    }
}
