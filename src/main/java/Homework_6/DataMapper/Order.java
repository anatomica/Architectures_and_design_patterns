package Homework_6.DataMapper;

public class Order {
    private int orderId;
    private String orderType;
    private String orderStartDate;
    private String orderStatus;

    public Order(String orderType, String orderStartDate, String orderStatus) {
        this.orderType = orderType;
        this.orderStartDate = orderStartDate;
        this.orderStatus = orderStatus;
    }

    public Order(int orderId, String orderType, String orderStartDate, String orderStatus) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.orderStartDate = orderStartDate;
        this.orderStatus = orderStatus;
    }

    public void put(int orderId, String orderType, String orderStartDate, String orderStatus) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.orderStartDate = orderStartDate;
        this.orderStatus = orderStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setOrderStartDate(String orderStartDate) {
        this.orderStartDate = orderStartDate;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getOrderStartDate() {
        return orderStartDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}