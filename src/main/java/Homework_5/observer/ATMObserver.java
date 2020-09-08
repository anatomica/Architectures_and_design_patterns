package Homework_5.observer;

import java.util.ArrayList;
import java.util.List;

public class ATMObserver implements Observer {
    private final List<String> managers;

    public ATMObserver() {
        managers = new ArrayList<>();
        fillManagerNames();
    }

    @Override
    public void update(String productName, String message) {
        for (String manager : managers) {
            System.out.println(manager + message + "Осуществляется: " + productName);
        }
    }

    private void fillManagerNames() {
        managers.add("User 1, ");
        managers.add("User 2, ");
        managers.add("User 3, ");
    }
}