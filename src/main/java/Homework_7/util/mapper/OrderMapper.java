package Homework_7.util.mapper;

import Homework_7.model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderMapper {
    private final Connection connection;

    private PreparedStatement findByIdStatement;
    private PreparedStatement findAllStatement;
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;

    private void prepareStatements() throws SQLException {
        findByIdStatement = connection.prepareStatement("SELECT * FROM [order] WHERE id = ?");
        findAllStatement = connection.prepareStatement("SELECT * FROM [order]");
        insertStatement = connection.prepareStatement("INSERT INTO [order] (type, start_date, status) VALUES (?, ?, ?)");
        updateStatement = connection.prepareStatement("UPDATE [order] SET type = ?, start_date = ?, status = ? WHERE id = ?");
        deleteStatement = connection.prepareStatement("DELETE FROM [order] WHERE id = ?");
    }

    public OrderMapper(Connection connection) throws SQLException {
        this.connection = connection;
        prepareStatements();
    }

    public Order findOrderById(int orderId) throws SQLException {
        findByIdStatement.setInt(1, orderId);

        try (ResultSet rs = findByIdStatement.executeQuery()) {
            if (rs.next()) {
                Order order = new Order(rs.getInt("id"), rs.getString("type"), rs.getString("start_date"), rs.getString("status"));
                return order;
            }
        }

        throw new SQLException("Не удалось найти запись с указанным идентификатором");
    }

    public ArrayList<Order> findAllOrders() throws SQLException {
        ArrayList<Order> orders = new ArrayList<>();
        try (ResultSet rs = findAllStatement.executeQuery()) {
            while (rs.next()) {
                orders.add(new Order(rs.getInt("id"), rs.getString("type"), rs.getString("start_date"), rs.getString("status")));
            }
            return orders;
        }
    }

    public void insert(Order order) throws SQLException {
        insertStatement.setString(1, order.getType());
        insertStatement.setString(2, order.getStartDate());
        insertStatement.setString(3, order.getStatus());
        insertStatement.execute();
    }

    public void update(Order order) throws SQLException {
        updateStatement.setString(1, order.getType());
        updateStatement.setString(2, order.getStartDate());
        updateStatement.setString(3, order.getStatus());
        updateStatement.setInt(4, order.getId());
        updateStatement.executeUpdate();
    }

    public void delete(Order order) throws SQLException {
        deleteStatement.setInt(1, order.getId());
        deleteStatement.executeUpdate();
    }
}
