package Homework_5;

import Homework_5.observer.*;

public class StartATM {
    public static void main(String[] args) {
        WithdrawSubject withdraw = new WithdrawSubject();
        DepositSubject deposit = new DepositSubject();
        Observer atmObserver = new ATMObserver();

        withdraw.attach(atmObserver);
        deposit.attach(atmObserver);

        System.out.println("Снятие наличных: ");
        withdraw.createOrder();
        System.out.println("Внесение наличных: ");
        deposit.createOrder();
    }
}
