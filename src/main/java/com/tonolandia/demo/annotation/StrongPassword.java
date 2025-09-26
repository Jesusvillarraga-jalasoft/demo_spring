package com.tonolandia.demo.annotation;

import jakarta.validation.Payload;

public @interface StrongPassword {
    String message() default "{usuario.password.strong}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    int minLength() default 12;
    boolean requireUppercase() default true;
    boolean requireLowercase() default true;
    boolean requireDigit() default true;
    boolean requireSpecial() default true;

    // Conjunto de caracteres especiales permitidos
    String specialChars() default "!@#$%^&*()_+-=[]{}|;:'\",.<>/?`~";
}
