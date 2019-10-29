package cz.ucl.logic.exceptions;

public class EmailAddressAlreadyUsedException extends Exception {
    public EmailAddressAlreadyUsedException(String s) {
        super(s);
    }
}
