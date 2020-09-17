package Homework_7.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Order {
    private int id;
    private String type;
    private String startDate;
    private String status;

    public Order(String type, String startDate, String status) {
        this.type = type;
        this.startDate = startDate;
        this.status = status;
    }

    public Order(int id, String type, String startDate, String status) {
        this.id = id;
        this.type = type;
        this.startDate = startDate;
        this.status = status;
    }

}
