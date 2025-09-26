package com.tonolandia.demo.annotation;

import com.tonolandia.demo.annotation.validators.FechaFinPosteriorAInicioValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FechaFinPosteriorAInicioValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaFinPosteriorAInicio {

    String message() default "{rango.fechas.invalido}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String inicio() default "inicio";
    String fin() default "fin";
}
