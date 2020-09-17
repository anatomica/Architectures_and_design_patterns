package Homework_5.observer;

public class WithdrawSubject extends Subject {
    private static final String PRODUCT_NAME = "Выдача наличных.";

    public void createOrder() {
        notifyUser(PRODUCT_NAME, "банкомат готов к работе. ");
    }

}