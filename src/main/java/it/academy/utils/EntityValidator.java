package it.academy.utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class EntityValidator {

    public static String validateEntity(Object... forSave) {
        Validator validator;
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
        List<ConstraintViolation<Object>> violations = new ArrayList<>();
        Arrays.stream(forSave).forEach(o -> violations.addAll(validator.validate(o)));
        StringBuilder out = new StringBuilder();
        for (ConstraintViolation<Object> violation : violations) {
            out.append(violation.getMessage());
        }
        return out.toString();
    }

}
