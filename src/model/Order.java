package model;

import java.io.Serializable;


public class Order implements Serializable {
    private String orderID;
    private String orderDate;
    private String customerName;
    private String orderDetailID;
    private String flowerID;
    private int quantity;
    private double flowerCost;

    public Order() {
    }

    public Order(String orderID, String orderDate, String customerName, String orderDetailID, String flowerID, int quantity, double flowerCost) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.orderDetailID = orderDetailID;
        this.flowerID = flowerID;
        this.quantity = quantity;
        this.flowerCost = flowerCost;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getFlowerID() {
        return flowerID;
    }

    public void setFlowerID(String flowerID) {
        this.flowerID = flowerID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getFlowerCost() {
        return flowerCost;
    }

    public void setFlowerCost(double flowerCost) {
        this.flowerCost = flowerCost;
    }

    @Override
    public String toString() {
        return "Order{" + "orderID=" + orderID + ", orderDate=" + orderDate + ", customerName=" + customerName + ", orderDetailID=" + orderDetailID + ", flowerID=" + flowerID + ", quantity=" + quantity + ", flowerCost=" + flowerCost + '}';
    }
    
        
}
