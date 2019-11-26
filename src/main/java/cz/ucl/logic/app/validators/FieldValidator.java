package cz.ucl.logic.app.validators;

import cz.ucl.logic.app.validators.definition.IValidator;
import cz.ucl.logic.exceptions.InvalidPropertyException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashSet;
import java.util.Set;

public class FieldValidator implements IValidator {

    private final Validator validator;

    public FieldValidator(Validator validator) {
        this.validator = validator;
    }

    @Override
    public <T> void validateAll(T object) throws InvalidPropertyException {
        Set<ConstraintViolation<T>> errors = validator.validate(object);
        throwExceptionIfErrors(errors);
    }

    @Override
    public <T> void validateFields(T object, String... fieldNames) throws InvalidPropertyException {
        Set<ConstraintViolation<T>> errors = new HashSet<>();
        for (String fieldName : fieldNames) {
            errors.addAll(validator.validateProperty(object, fieldName));
        }
        throwExceptionIfErrors(errors);
    }

    private <T> void throwExceptionIfErrors(Set<ConstraintViolation<T>> errors) throws InvalidPropertyException {
        if (!errors.isEmpty())
            throw new InvalidPropertyException(
                    errors.stream()
                            .map(e -> e.getPropertyPath() + " " + e.getMessage())
                            .toArray(String[]::new)
            );
    }
}
