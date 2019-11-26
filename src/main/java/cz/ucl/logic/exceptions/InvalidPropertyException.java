package cz.ucl.logic.exceptions;

public class InvalidPropertyException extends Exception {
    public InvalidPropertyException(String[] messages) {
        super("[" + String.join(", ", messages) + "]");
    }
}
