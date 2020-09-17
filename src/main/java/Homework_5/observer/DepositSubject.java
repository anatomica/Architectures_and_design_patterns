package Homework_5.observer;

public class DepositSubject extends Subject {
    private static final String PRODUCT_NAME = "Внесение наличных.";

    public void createOrder() {
        notifyUser(PRODUCT_NAME, "банкомат готов к работе. ");
    }
}