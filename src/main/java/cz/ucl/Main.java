package cz.ucl;

import cz.ucl.logic.Program;
import cz.ucl.logic.IAppLogic;
import cz.ucl.logic.exceptions.EmailAddressAlreadyUsedException;
import cz.ucl.logic.exceptions.InvalidColorException;
import cz.ucl.logic.exceptions.InvalidPropertyException;
import cz.ucl.ui.definition.IUserInterface;
import cz.ucl.ui.cli.CLI;

import java.util.Arrays;
//import cz.ucl.ui.gui.GUI; // This is for BONUS task only

public class Main {
    public static void main(String[] args) {
        IAppLogic program = new Program();

        IUserInterface ui = new CLI();
        // IUserInterface ui = new GUI(); // This is for BONUS task only

        if (Arrays.asList(args).contains("--mock")) {
            try {
                program.generateMockData();
            } catch (InvalidColorException | EmailAddressAlreadyUsedException | InvalidPropertyException e) {
                e.printStackTrace();
            }
        }

        ui.run(program);
    }
}
