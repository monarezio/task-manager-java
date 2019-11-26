package cz.ucl.logic.app.validators.definition;

import cz.ucl.logic.exceptions.InvalidPropertyException;

public interface IValidator {

    <T> void validateAll(T object) throws InvalidPropertyException;

    <T> void validateFields(T object, String... fieldName) throws InvalidPropertyException;

}
