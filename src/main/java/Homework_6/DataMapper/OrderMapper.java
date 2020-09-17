package Homework_6.DataMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper {
    private final Connection connection;
    private PreparedStatement preparedStatement;
    private Map<Integer, Order> orderMap = new HashMap<>();

    public OrderMapper(Connection connection) {
        this.connection = connection;
    }

    public Order findOrderById(int orderId) throws SQLException {
        if (orderMap.get(orderId) != null) {
            System.out.println(">> Получение значения из кэша");
            return orderMap.get(orderId);
        } else {
            System.out.println(">> Получение значения из БД");
            preparedStatement = connection.prepareStatement("SELECT * FROM [order] WHERE id = ?");
            preparedStatement.setInt(1, orderId);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    Order order = new Order(rs.getInt("id"), rs.getString("type"), rs.getString("start_date"), rs.getString("status"));
                    System.out.println(">> Запись в кэш");
                    orderMap.put(orderId, order);
                    return order;
                }
            }
        }

        throw new SQLException("Не удалось найти запись с указанным идентификатором");
    }

    public void insert(Order order) throws SQLException {
        preparedStatement = connection.prepareStatement("INSERT INTO [order] (type, start_date, status) VALUES (?, ?, ?)");
        preparedStatement.setString(1, order.getOrderType());
        preparedStatement.setString(2, order.getOrderStartDate());
        preparedStatement.setString(3, order.getOrderStatus());
        preparedStatement.execute();
    }

    public void update(Order order) throws SQLException {
        preparedStatement = connection.prepareStatement("UPDATE [order] " +
                "SET type = ?, start_date = ?, status = ?" +
                "WHERE id = ?");

        preparedStatement.setString(1, order.getOrderType());
        preparedStatement.setString(2, order.getOrderStartDate());
        preparedStatement.setString(3, order.getOrderStatus());
        preparedStatement.setInt(4, order.getOrderId());
        preparedStatement.executeUpdate();
    }

    public void delete(Order order) throws SQLException {
        preparedStatement = connection.prepareStatement("DELETE FROM [order]" +
                "WHERE id = ?");
        preparedStatement.setInt(1, order.getOrderId());
        preparedStatement.executeUpdate();
    }

    public void dropTable() throws SQLException {
        preparedStatement = connection.prepareStatement("DROP TABLE IF EXISTS 'order'");
        preparedStatement.execute();
    }

    public void createTable() throws SQLException {
        preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS [order] (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL\n" +
                "                       UNIQUE,\n" +
                "    type       TEXT    NOT NULL,\n" +
                "    start_date TEXT    NOT NULL,\n" +
                "    status     TEXT    NOT NULL\n" +
                ");");
        preparedStatement.execute();
    }
}