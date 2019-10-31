package cz.ucl.ui.definition.forms;

import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.exceptions.InvalidFieldValueException;
import cz.ucl.ui.exceptions.UnsupportedInputTypeException;

import java.util.Map;

public interface IFormManager {
    // Public Methods
    IForm getForm();

    IUserInterface getUserInterface();

    Map<String, String> processForm();

    // "Private" Methods
    void processField(IFormField formField);

    int processNumericalInput(int userInput, IFormField formField) throws UnsupportedInputTypeException, InvalidFieldValueException;
    String processTextualInput(String userInput, IFormField formField) throws UnsupportedInputTypeException, InvalidFieldValueException;
    String processSecureInput(String userInput, IFormField formField) throws UnsupportedInputTypeException, InvalidFieldValueException;
}
