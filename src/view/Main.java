
package view;

import controller.Validation;
import java.text.ParseException;

public class Main {
    
    
    private static final Validation vl = new Validation();
    public static void main(String[] args) throws ParseException {
        UI ui = new UI();
        ui.displayMenu();
        int choice = vl.inputInt("Enter your choice", 0, 3);
        ui.handleMenuChoice(choice);
    }
}
