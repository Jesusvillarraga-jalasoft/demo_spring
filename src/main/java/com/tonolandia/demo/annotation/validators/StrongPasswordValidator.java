package com.tonolandia.demo.annotation.validators;

import com.tonolandia.demo.annotation.StrongPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StrongPasswordValidator implements ConstraintValidator<StrongPassword, CharSequence> {

    private int min;
    private boolean up, low, dig, spec;
    private String specials;

    @Override
    public void initialize(StrongPassword cfg) {
        this.min = cfg.minLength();
        this.up = cfg.requireUppercase();
        this.low = cfg.requireLowercase();
        this.dig = cfg.requireDigit();
        this.spec = cfg.requireSpecial();
        this.specials = cfg.specialChars();
    }

    @Override
    public boolean isValid(CharSequence raw, ConstraintValidatorContext ctx) {
        if (raw == null) return true; // deja a @NotNull decidir
        String s = raw.toString();
        if (s.length() < min) return false;
        if (s.chars().anyMatch(Character::isWhitespace)) return false;

        boolean hasU = s.chars().anyMatch(Character::isUpperCase);
        boolean hasL = s.chars().anyMatch(Character::isLowerCase);
        boolean hasD = s.chars().anyMatch(Character::isDigit);
        boolean hasS = s.chars().anyMatch(c -> specials.indexOf(c) >= 0);

        if (up && !hasU) return false;
        if (low && !hasL) return false;
        if (dig && !hasD) return false;
        if (spec && !hasS) return false;

        return true;
    }
}
