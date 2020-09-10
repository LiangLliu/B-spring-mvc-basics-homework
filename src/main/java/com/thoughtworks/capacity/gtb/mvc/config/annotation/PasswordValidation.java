package com.thoughtworks.capacity.gtb.mvc.config.annotation;

import javax.validation.Constraint;
import javax.validation.constraints.NotBlank;


import javax.validation.constraints.Size;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@NotBlank(message = "密码不能为空")
@Size(min = 5, max = 12, message = "密码不合法")
@Constraint(validatedBy = { })
public @interface PasswordValidation {
}

