package ru.ftptpf.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import ru.ftptpf.validation.impl.UserInfoValidator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = UserInfoValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface UserInfo {

    String message() default "First name or Lastname should be filled in";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
