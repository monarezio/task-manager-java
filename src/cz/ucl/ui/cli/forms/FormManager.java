package cz.ucl.ui.cli.forms;

import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.definition.forms.FormFieldType;
import cz.ucl.ui.definition.forms.IForm;
import cz.ucl.ui.definition.forms.IFormField;
import cz.ucl.ui.definition.forms.IFormManager;
import cz.ucl.ui.exceptions.InvalidFieldValueException;
import cz.ucl.ui.exceptions.UnsupportedInputTypeException;

import java.util.HashMap;
import java.util.Map;

public class FormManager implements IFormManager {
    private IForm form;
    private IUserInterface ui;
    private Map<String, String> data;

    public FormManager(IForm form, IUserInterface ui) {
        this.form = form;
        this.ui = ui;
        this.data = new HashMap<>();
    }

    // TODO

    @Override
    public Map<String, String> processForm() {
        // TODO

        return new HashMap<>(data);
    }

    @Override
    public void processField(IFormField formField) {
        boolean isValid;
        String value;

        do {
            isValid = true;
            System.out.print(form.renderFormField(formField));

            try {
                if (formField.getType() == FormFieldType.TEXTUAL) {
                    value = ui.promptString();
                    value = processTextualInput(value, formField);
                } else if (formField.getType() == FormFieldType.NUMERICAL) {
                    int i = ui.promptNumber();
                    value = String.valueOf(processNumericalInput(i, formField));
                } else if (formField.getType() == FormFieldType.SECURE) {
                    value = ui.promptSecureString();
                    value = processSecureInput(value, formField);
                } else {
                    throw new RuntimeException("Form field type " + formField.getType() + " is not supported");
                }

                data.put(formField.getIdentifier(), value);
            } catch (UnsupportedInputTypeException | InvalidFieldValueException e) {
                ui.drawError(e.getMessage());
                isValid = false;
            }
        } while (!isValid);
    }
}
