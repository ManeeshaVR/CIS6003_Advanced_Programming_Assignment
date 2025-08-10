package lk.pahana.edu.pahana_edu_billing_system.persistence.order.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.order.mapper.OrderMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.order.model.Order;
import lk.pahana.edu.pahana_edu_billing_system.business.order.model.OrderItem;
import lk.pahana.edu.pahana_edu_billing_system.persistence.order.dao.OrderDAO;
import lk.pahana.edu.pahana_edu_billing_system.util.db.DBConnection;
import lk.pahana.edu.pahana_edu_billing_system.util.db.SqlQueries;

import java.sql.*;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public boolean save(Order order) {
        Connection connection = null;
        PreparedStatement orderPstm = null;
        PreparedStatement orderItemPstm = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false); // Start transaction

            // Insert order into Order
            orderPstm = connection.prepareStatement(SqlQueries.Order.INSERT);
            orderPstm.setString(1, order.getOrderId());
            orderPstm.setDate(2, Date.valueOf(order.getDate()));
            orderPstm.setString(3, order.getCustomerId());
            orderPstm.setDouble(4, order.getTotalAmount());
            orderPstm.executeUpdate();

            // Insert order items into `Order Item
            orderItemPstm = connection.prepareStatement(SqlQueries.OrderItem.INSERT);
            for (OrderItem item : order.getOrderItems()) {
                orderItemPstm.setString(1, order.getOrderId());
                orderItemPstm.setString(2, item.getItemCode());
                orderItemPstm.setInt(3, item.getQuantity());
                orderItemPstm.setDouble(4, item.getUnitPrice());
                orderItemPstm.addBatch();
            }

            orderItemPstm.executeBatch();
            connection.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            try {
                if (orderPstm != null) orderPstm.close();
                if (orderItemPstm != null) orderItemPstm.close();
                if (connection != null) connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Order findLast() {
        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            pstm = connection.prepareStatement(SqlQueries.Order.FIND_LAST);
            ResultSet resultSet = pstm.executeQuery();

            Order order = null;
            if (resultSet.isBeforeFirst()) {
                order = OrderMapper.mapToOrder(resultSet);
            }
            return order;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
