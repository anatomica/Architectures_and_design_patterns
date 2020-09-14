package Homework_7.controller;

import Homework_7.model.Order;
import Homework_7.util.DBManager;
import Homework_7.util.mapper.OrderMapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class Controller {
    private OrderMapper orderMapper;
    private ObservableList<Order> usersData = FXCollections.observableArrayList();

    @FXML
    private TableView<Order> orderTable;
    @FXML
    private TableColumn<Object, Object> idColumn;
    @FXML
    private TableColumn<Object, Object> typeColumn;
    @FXML
    private TableColumn<Object, Object> startDateColumn;
    @FXML
    private TableColumn<Object, Object> statusColumn;

    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        try {
            orderMapper = new OrderMapper(DBManager.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        refreshData();
    }

    public void refreshData() {
        try {
            ArrayList<Order> orders = orderMapper.findAllOrders();

            usersData.clear();
            usersData.addAll(orders);
            orderTable.setItems(usersData);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData() {
        try {
            Random random = new Random();
            int day = random.nextInt(29) + 1;
            int month = random.nextInt(9) + 1;
            int year = random.nextInt(9) + 1;

            orderMapper.insert(new Order("Test Order", day + ".0" + month + ".200" + year, "NEW"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshData();
    }
}
