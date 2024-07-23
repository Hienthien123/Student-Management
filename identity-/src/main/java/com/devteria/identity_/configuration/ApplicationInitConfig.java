package com.devteria.identity_.configuration;

import com.devteria.identity_.dto.response.UserResponse;
import com.devteria.identity_.entity.User;
import com.devteria.identity_.enums.Role;
import com.devteria.identity_.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@Slf4j
public class ApplicationInitConfig {
    PasswordEncoder passwordEncoder;
    @Bean
    @ConditionalOnProperty(prefix = "spring",
            value ="datasource.driverClassName",
            havingValue = "com.mysql.cj.jdbc.Driver"
    )
    ApplicationRunner applicationRunner(UserRepository userRepository){
        log.info("Init Application");
        return args -> {
           if(userRepository.findByUsername("admin").isEmpty()) {
               var roles = new HashSet<String>();
               roles.add(Role.ADMIN.name());
               User user = User.builder()
                       .username("admin")
                      // .roles(roles)
                       .password(passwordEncoder.encode("admin"))
                       .build();
               userRepository.save(user);
               log.warn("admin user has been created with default password: admin, please change it");
           }
           log.warn("admin user craete succsesfull");
        };
    }
}
