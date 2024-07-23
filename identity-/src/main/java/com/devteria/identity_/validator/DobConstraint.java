package com.devteria.identity_.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {DobValidator.class})

public @interface DobConstraint {
    String message() default "{Invalid data of birth}";
    int min();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
