package Homework_6;

import Homework_6.DataMapper.DBManager;
import Homework_6.DataMapper.Order;
import Homework_6.DataMapper.OrderMapper;
import java.sql.SQLException;

public class DataMain {
    public static void main(String[] args) {
        try {
            OrderMapper orderMapper = new OrderMapper(DBManager.connection());
            orderMapper.dropTable();
            orderMapper.createTable();

            // insert
            Order insertOrder = new Order("Order #1", "01.01.2020", "OPEN");
            orderMapper.insert(insertOrder);
            Order firstOrder = orderMapper.findOrderById(1);
            System.out.println("ID: " + firstOrder.getOrderId() + ", TYPE: " + firstOrder.getOrderType() + ", START DATE: "
                    + firstOrder.getOrderStartDate() + ", STATUS: " + firstOrder.getOrderStatus() + "\n");

            Order updateOrder = new Order("Order #2", "02.02.2020", "OPEN");
            orderMapper.insert(updateOrder);
//            Order secondOrder = orderMapper.findOrderById(2);
//            System.out.println("ID: " + secondOrder.getOrderId() + ", TYPE: " + secondOrder.getOrderType() + ", START DATE: "
//                    + secondOrder.getOrderStartDate() + ", STATUS: " + secondOrder.getOrderStatus() + "\n");

            Order deleteOrder = new Order("Order #3", "03.03.2020", "ERROR");
            orderMapper.insert(deleteOrder);
//            Order threeOrder = orderMapper.findOrderById(3);
//            System.out.println("ID: " + threeOrder.getOrderId() + ", TYPE: " + threeOrder.getOrderType() + ", START DATE: "
//                    + threeOrder.getOrderStartDate() + ", STATUS: " + threeOrder.getOrderStatus() + "\n");

            // update
            updateOrder = orderMapper.findOrderById(2);
            updateOrder.setOrderStatus("CLOSE");
            orderMapper.update(updateOrder);

            // delete
            deleteOrder = orderMapper.findOrderById(3);
            orderMapper.delete(deleteOrder);

            // Тест Identity Map
            orderMapper.findOrderById(1); // из кэша
            orderMapper.findOrderById(2); // из БД
            orderMapper.findOrderById(2); // из кэша
            orderMapper.findOrderById(3); // из БД
            orderMapper.findOrderById(3); // из кэша

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                DBManager.disconnect();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}