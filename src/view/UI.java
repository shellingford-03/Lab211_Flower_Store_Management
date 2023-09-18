
package view;

import controller.FlowerStore;
import controller.Validation;
import java.text.ParseException;

public class UI {
 
    Validation vl;
    FlowerStore fs; 
    
    
    
    
    public UI(){
        vl = new Validation();
        fs = new FlowerStore();
        fs.loadData();
    }
    public void displayMenu() {
        System.out.println("Manage Flower");
        System.out.println("---------------------------");
        System.out.println("1. Manage flower");
        System.out.println("\t1.1 Add a flower");
        System.out.println("\t1.2 Find a flower");
        System.out.println("\t1.3 Find by cost");
        System.out.println("\t1.4 Display flower");
        System.out.println("\t1.5 Load data");
        System.out.println("\t1.6 Update a flower");
        System.out.println("\t1.7 Delete a flower");
        System.out.println("2. Manage Order");
        System.out.println("\t2.1 Add a order");
        System.out.println("\t2.2 Display orders");
        System.out.println("\t2.3 Sort orders");
        System.out.println("\t2.4 Save data");
        System.out.println("\t2.5 Load data");
        System.out.println("3. Quit");
    }
    public void handleMenuChoice(int choice) throws ParseException {
        switch (choice) {
            case 1:
                handleManageFlowers();
                break;
            case 2:
                handleOrderFlowers();
                break;
            case 3:
                handleQuit();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
        private void handleManageFlowers() throws ParseException {
        
        while (true) {
            System.out.println("\nFlower's management");
            System.out.println("------------------");
            System.out.println("1. Add a flower");
            System.out.println("2. Find a flower");
            System.out.println("3. Find by cost");
            System.out.println("4. Display flower");
            System.out.println("5. Load data");
            System.out.println("6. Update a flower");
            System.out.println("7. Delete a flower");
            System.out.println("8. Back to main menu");
            int choice = vl.inputInt("Enter your choice: ", 1, 8);
            switch (choice) {
                case 1:
                    fs.addFlower();
                    break;
                case 2:
                    fs.findFlowers("Enter id or category flower to find: ");
                    break;
                case 3:
                    fs.findFlowerByCost("Enter cost");
                    break;
                case 4:
                    fs.displayFlowers();
                    break;
                case 5:
                    fs.loadData();
                    break;
                case 6:
                    fs.updateFlower();
                    break;
                case 7:
                    fs.deleteFlower();
                    break;
                case 8:
                    displayMenu();
                    int choice1 = vl.inputInt("Enter your choice", 0, 3);
                    handleMenuChoice(choice1);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    public void handleOrderFlowers() throws ParseException {
        
        while (true) {
            System.out.println("\nOrder's management");
            System.out.println("--------------------");
            System.out.println("1. Add a order");
            System.out.println("2. Display orders");
            System.out.println("3. Sort the order list");
            System.out.println("4. Save data");
            System.out.println("5. Load data");
            System.out.println("6. Back to main menu");
            int choice = vl.inputInt("Enter your choice: ", 1, 6);
            switch (choice) {
                case 1:                    
                    fs.addOrder();
                    break ;
                case 2:
                    fs.displayOrders();
                    break;
                case 3:
                    fs.sortOrder();
                    break;
                case 4:
                    fs.saveData();
                    break;
                case 5:
                    fs.loadData();
                    break;
                case 6:
                     displayMenu();
                    int choice1 = vl.inputInt("Enter your choice", 0, 3);
                    handleMenuChoice(choice1);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public void handleQuit(){
        fs.quit();
    }
}

