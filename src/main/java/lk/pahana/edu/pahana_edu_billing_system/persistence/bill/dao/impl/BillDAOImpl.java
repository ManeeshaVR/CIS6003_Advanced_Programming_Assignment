package lk.pahana.edu.pahana_edu_billing_system.persistence.bill.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper.BillMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.mapper.InvoiceMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Bill;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.BillItem;
import lk.pahana.edu.pahana_edu_billing_system.business.bill.model.Invoice;
import lk.pahana.edu.pahana_edu_billing_system.persistence.bill.dao.BillDAO;
import lk.pahana.edu.pahana_edu_billing_system.util.db.DBConnection;
import lk.pahana.edu.pahana_edu_billing_system.util.db.SqlQueries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    @Override
    public boolean save(Bill bill) {
        Connection connection = null;
        PreparedStatement orderPstm = null;
        PreparedStatement orderItemPstm = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            // Insert order into Order
            orderPstm = connection.prepareStatement(SqlQueries.Bill.INSERT);
            orderPstm.setString(1, bill.getBillId());
            orderPstm.setTimestamp(2, Timestamp.valueOf(bill.getDate()));
            orderPstm.setString(3, bill.getCustomerId());
            orderPstm.setDouble(4, bill.getTotalAmount());
            orderPstm.executeUpdate();

            // Insert order items into `Order Item
            orderItemPstm = connection.prepareStatement(SqlQueries.BillItem.INSERT);
            for (BillItem item : bill.getBillItems()) {
                orderItemPstm.setString(1, bill.getBillId());
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
    public Invoice findLast() {
        Connection connection = null;
        PreparedStatement pstm = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            pstm = connection.prepareStatement(SqlQueries.Bill.FIND_LAST);
            ResultSet resultSet = pstm.executeQuery();

            Invoice invoice = null;
            if (resultSet.isBeforeFirst()) {
                invoice = InvoiceMapper.mapToInvoice(resultSet);
            }
            return invoice;

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

    @Override
    public int getCount() {
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Bill.COUNT)
        ) {
            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        try (
                Connection connection = DBConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Bill.FIND_ALL);
                ResultSet rs = pstm.executeQuery()
        ) {
            while (rs.next()) {
                bills.add(new Bill.Builder()
                        .setBillId(rs.getString("bill_id"))
                        .setDate(rs.getTimestamp("bill_date").toLocalDateTime())
                        .setCustomerId(rs.getString("customer_id"))
                        .setTotalAmount(rs.getDouble("total_amount"))
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
}
