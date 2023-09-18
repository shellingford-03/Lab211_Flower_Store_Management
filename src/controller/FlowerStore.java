
package controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import model.Flower;
import model.Order;


public class FlowerStore {

    private Validation vl;
    private Set<Flower> listFlower;
    private Set<Order> listOrder;
  
    // Create SimpleDateFormat object to parse to Date to compare Date
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // check data changed 
    private boolean dataChanged;

    public FlowerStore() {
        vl = new Validation();
        listFlower = new HashSet<>();
        listOrder = new HashSet<>();
        dataChanged = false;
    }

    public void addFlower() {
        while (true) {
            // input data flower
            String id = vl.inputID(listFlower, "Enter id: ");
            String description = vl.inputDescription("Enter description: ");
            String date = vl.inputDate("Enter date: ");
            String category = vl.inputString("Enter category: ");
            double unitPrice = vl.inputDouble("Enter unit price: ", 0, 1000000);

            Flower newFlower = new Flower(id, description, date, unitPrice, category);

            listFlower.add(newFlower);

            // asking user to want adding a new flower 
            if (vl.inputString("Do you want adding a new flower ( enter `Y` ) or back to the main menu ( enter `N` )").equalsIgnoreCase("y")) {
                continue;
            } else {
                break;
            }
        }

        // set changed value 
        dataChanged = true;
    }

    public Flower findFlower(String msg) {   
        // check field are id or name 
        String findField = vl.inputString(msg);

        Iterator<Flower> itr = listFlower.iterator();

        while (itr.hasNext()) {
            Flower fl = itr.next();
            if (fl.getId().equals(findField) || fl.getCategory().equals(findField)) {
                System.out.println(fl);
                return fl;
            }
        }
        System.out.println("The flower does not exist");
        dataChanged = true;
        return null;
    }
    
//    
//    public Flower findFlowerByCost(String msg){
//        double findField = vl.inputDouble(msg, 0, 10000);
//        
////        int findField = vl.inputInt(msg, 0, 1000000);
//        Iterator<Flower> itr = listFlower.iterator();
//        
//        while (itr.hasNext()){
//            Flower fl = itr.next();
//            if(fl.getUnitPrice() == findField){
//                System.out.println(fl);
//                return fl;
//            } 
//        }
//        System.out.println("The flower does not exist");
//        dataChanged = true;
//        return null;
//    }
    
    
    
    
    
    public List<Flower> findFlowerByCost(String msg) {
        double findField =vl.inputDouble(msg, 0, 10000);
        List<Flower> foundFlowers = new ArrayList<>();

        for (Flower fl : listFlower) {
            if (fl.getUnitPrice() == findField) {
                foundFlowers.add(fl);
            }
        }

        if (foundFlowers.isEmpty()) {
            System.out.println("The flower does not exist");
        } else {
            System.out.println("LIST FLOWER:");
            for (Flower flower : foundFlowers) {
                System.out.println(flower);
            }
        }

        return foundFlowers;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public List<Flower> findFlowers(String msg) {
        String findField = vl.inputString(msg);
        List<Flower> foundFlowers = new ArrayList<>();

        for (Flower fl : listFlower) {
            if (fl.getId().equals(findField) || fl.getCategory().equals(findField)) {
                foundFlowers.add(fl);
            }
        }

        if (foundFlowers.isEmpty()) {
            System.out.println("The flower does not exist");
        } else {
            System.out.println("LIST FLOWER:");
            for (Flower flower : foundFlowers) {
                System.out.println(flower);
            }
        }

        return foundFlowers;
    }


    
    /*
    Function 3. Update a flower – 50 LOC
− User is required to input the flower name.
− If the flower does not exist, the message “The flower does not exist” is displayed. Otherwise, the user can edit the flower.
− Show the result of the update: success or failure.
     */
    public void updateFlower() {
        Flower fl = findFlower("Enter id flower to update: ");
        if (fl == null) {
            return;
        }
        // input new data flower
        if(fl != null){
            String description = vl.inputDescription("Enter new description: ");
            String date = vl.inputDate("Enter new date: ");
            String category = vl.inputString("Enter new category: ");
            double unitPrice = vl.inputDouble("Enter new unit price: ", 0, 1000000);

            fl.setCategory(category);
            fl.setDate(date);
            fl.setDescription(description);
            fl.setUnitPrice(unitPrice);

            System.out.println("Success");
            dataChanged = true;
        }
    
    }

    /*
    Function 4. Delete a flower – 50 LOC
− User is required to input the flower id.
− If the flower does not exist, the message “The flower does not exist” is displayed. Otherwise, the user can delete the flower.
− The screen must show the confirmation message before deleting.
− The flower cannot be deleted if it has already existed in an order detail.
− Show the result of the deletion: success or failure
     */
    public void deleteFlower() {
    Flower fl = findFlower("Enter the flower ID to delete: ");
    if (fl == null) {
        return;
    }

    // Check if the flower exists in an order detail
    for (Order order : listOrder) {
        if (order.getFlowerID().equals(fl.getId())) {
            System.out.println("The flower cannot be deleted as it exists in an order detail.");
            return;
        }
    }

    // Confirmation message before deleting
    System.out.println("Are you sure you want to delete the flower with ID " + fl.getId() + "? (Y/N)");
    String confirmation = vl.inputString("");

    if (confirmation.equalsIgnoreCase("Y")) {
        listFlower.remove(fl);
        dataChanged = true;
        System.out.println("The flower has been successfully deleted.");
    } else {
        System.out.println("Deletion canceled.");
    }
}


    
    public void displayFlowers() {
        if (listFlower.isEmpty()) {
            System.out.println("Empty list.");
        } else {
            System.out.println("LIST FLOWER:");
            for (Flower flower : listFlower) {
                System.out.println(flower);
            }
        }
    }

    
    
    /*
    Function 5. Add an order – 50 LOC
− An order includes an order header and several order details. The order header includes the order id, the order date, and the customer’s name. The order detail includes the order detail id, the flower id, the quantity, and the flower cost.
− The system should check the valid data with the following conditions: ▪ All fields are not allowed null.
▪ The id fields must be unique.
▪ The order date field must be a valid date format.
▪ The quantity field must be a positive integer.
▪ The flower cost field is the field calculated as follows: flower cost = quantity x unit price.
− Add the order to the collection of orders.
− Ask to continue adding a new order or go back to the main menu
     */
    public void addOrder() {

        while (true) {
            // input data flower
            String orderID = vl.inputID(listFlower, "Enter id: ");
            String orderDate = vl.inputDate("Enter date: ");
            String customerName = vl.inputString("Enter custormer name: ");
            String orderDetailID = vl.inputString("Enter order detail id: ");
            int quantity = vl.inputInt("Enter unit price: ", 0, 1000000);
            Flower fl = findFlower("Enter flower id: ");
            if (fl != null) {
                String flowerID = fl.getId();
                double flowerCost = quantity * fl.getUnitPrice();

                Order newOrder = new Order(orderID, orderDate, customerName, orderDetailID, flowerID, quantity, flowerCost);

                listOrder.add(newOrder);
                
                dataChanged = true;
                

                // asking user to want adding a new flower 
                
            }
            if (vl.inputString("Do you want adding a new order ( enter `Y` ) or back to the main menu ( enter `N` )").equalsIgnoreCase("y")) {
                    continue;
                } else {
                    break;
                }
                

        }
        

    }

    /*Function 6. Display orders – 50 LOC
− User is required to input a start and end date.
− The screen should show orders base on inputted date range as below if applicable.
     */
    public void displayOrders() throws ParseException {
        int noCount = 1;
        String startDate = vl.inputDate("Enter start date");
        String endDate = vl.inputDate("Enter end date");

        Date start = sdf.parse(startDate);
        Date end = sdf.parse(endDate);

        System.out.println("LIST ORDERS FROM " + startDate + " TO " + endDate);
        System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", "No.", "Order Id", "Order Date", "Customer name", "Flower Count", "Order total");

        Iterator<Order> itr = listOrder.iterator();

        int flowerCount = 0;
        double orderTotal = 0;
        while (itr.hasNext()) {
            Order od = itr.next();

            Date checkDate = sdf.parse(od.getOrderDate());

            if (checkDate.compareTo(start) >= 0 && checkDate.compareTo(end) <= 0) {
                System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", noCount, od.getOrderID(), od.getOrderDate(), od.getCustomerName(), od.getQuantity(), "$ " + od.getFlowerCost());
                noCount++;
                flowerCount += od.getQuantity();
                orderTotal += od.getFlowerCost();
            }

        }
        System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", "", "Total", "", "", flowerCount, "$ " + orderTotal);

    }

    /*
    Function 7. Sort orders – 50 LOC
− User is required to input a sorted field (order id or order date or customer name or order total) and the
sort order (ASC, DESC).
− Sort and display the collection of orders as below.
     */
    public void sortOrder() {
        String sortedField = vl.inputString("Enter the sorted field (order id / order date / customer name / order total): ");
        String sortOrder = vl.inputString("Enter the sort order (ASC / DESC): ");

        List<Order> sortedOrders = new ArrayList<>(listOrder);

        switch (sortedField.toLowerCase()) {
            case "order id":
                sortedOrders.sort(Comparator.comparing(Order::getOrderID));
                break;
            case "id":
                sortedOrders.sort(Comparator.comparing(Order::getOrderID));
                break;
                
            case "order date":
                sortedOrders.sort(Comparator.comparing(Order::getOrderDate));
                break;
            case "date":
                sortedOrders.sort(Comparator.comparing(Order::getOrderDate));
                break;
                
            case "customer name":
                sortedOrders.sort(Comparator.comparing(Order::getCustomerName));
                break;
            case "name":
                sortedOrders.sort(Comparator.comparing(Order::getCustomerName));
                break;
                
            case "order total":
                sortedOrders.sort(Comparator.comparing(Order::getFlowerCost));
                break;
            case "total":
                sortedOrders.sort(Comparator.comparing(Order::getFlowerCost));
                break;
            default:
                System.out.println("Invalid sorted field.");
                return;
        }

        if (sortOrder.equalsIgnoreCase("desc")) {
            Collections.reverse(sortedOrders);
        }

        System.out.println("LIST OF ORDERS :");

        System.out.println("Sorted by: " + sortedField);
        System.out.println("Sort order : " + sortOrder);
        System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", "No.", "Order Id", "Order Date", "Customer name", "Flower Count", "Order total");
        int noCount = 1;
        int flowerCount = 0;
        double orderTotal = 0;
        for (Order od : sortedOrders) {
            System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", noCount, od.getOrderID(), od.getOrderDate(), od.getCustomerName(), od.getQuantity(), "$ " + od.getFlowerCost());
            flowerCount += od.getQuantity();
            orderTotal += od.getFlowerCost();
            noCount++;
        }
        System.out.printf("%-4s%-12s%-16s%-12s%-15s%-1s\n", "" ,"Total", "", "", flowerCount, "$ " + orderTotal);
    }

    /*
    Function 8. Save data – 50 LOC
John Smith Bill Jamie John Smith
3 5 2
− The system saves the collection of flowers to the binary file that named with flowers.dat.
− The system saves the collection of orders to the binary file that named with orders.dat.
     */
    public void saveData() {
        if (dataChanged) {
            try {
                ObjectOutputStream flowerOutputStream = new ObjectOutputStream(new FileOutputStream("flowers.dat"));
                flowerOutputStream.writeObject(listFlower);
                flowerOutputStream.close();

                ObjectOutputStream orderOutputStream = new ObjectOutputStream(new FileOutputStream("orders.dat"));
                orderOutputStream.writeObject(listOrder);
                orderOutputStream.close();

                System.out.println("Data saved successfully.");
                dataChanged = false;
            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        } else {
            System.out.println("No changes to save.");
        }
    }

    /*
Function 9. Load data – 50 LOC
− The system loads the collection of pets from the flowers.dat file.
− The system loads the collection of orders to orders.dat file.
     */
    @SuppressWarnings("unchecked")
    public void loadData() {
        try {
            ObjectInputStream flowerInputStream = new ObjectInputStream(new FileInputStream("flowers.dat"));

            listFlower = (Set<Flower>) flowerInputStream.readObject();
            flowerInputStream.close();

            ObjectInputStream orderInputStream = new ObjectInputStream(new FileInputStream("orders.dat"));
            listOrder = (Set<Order>) orderInputStream.readObject();
            orderInputStream.close();

            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Data files not found. Starting with empty collections.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    /*
Function 10. Quit – 50 LOC
− Exit the program.
− The application must show the confirmation message before exiting.
− The application must save data to files if data has changed.
     */
    public void quit() {
        if (dataChanged) {
            System.out.println("Changes have been made. Saving data before quitting...");
            saveData();
        }
        System.out.println("Exiting the program. Goodbye!");
        System.exit(0);
    }
}
