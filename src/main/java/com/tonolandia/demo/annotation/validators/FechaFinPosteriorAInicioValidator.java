package com.tonolandia.demo.annotation.validators;

import com.tonolandia.demo.annotation.FechaFinPosteriorAInicio;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class FechaFinPosteriorAInicioValidator implements ConstraintValidator<FechaFinPosteriorAInicio, Object> {

    private String inicioField;
    private String finField;

    @Override
    public void initialize(FechaFinPosteriorAInicio anno) {
        this.inicioField = anno.inicio();
        this.finField = anno.fin();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) return true; // otra capa debería validar null

        BeanWrapperImpl bean = new BeanWrapperImpl(value);
        Object inicio = bean.getPropertyValue(inicioField);
        Object fin = bean.getPropertyValue(finField);

        if (inicio == null || fin == null) return true; // deja que @NotNull lo reporte

        boolean valid = false;
        // Soporta tipos comparables (LocalDate, LocalDateTime, etc.)
        if (inicio instanceof Comparable<?> && fin.getClass().isAssignableFrom(inicio.getClass())) {
            @SuppressWarnings("unchecked")
            Comparable<Object> finComparable = (Comparable<Object>) fin;
            valid = finComparable.compareTo(inicio) > 0;
        }

        if (!valid) {
            // Mueve la violación al campo "fin" con el mensaje de tu messages.properties
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(finField)  // <-- clave: atarlo al campo
                    .addConstraintViolation();
        }

        return valid;
    }
}
