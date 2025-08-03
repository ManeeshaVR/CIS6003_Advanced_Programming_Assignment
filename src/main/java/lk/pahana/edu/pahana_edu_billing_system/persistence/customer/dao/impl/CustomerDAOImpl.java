package lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;
import lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.CustomerDAO;
import lk.pahana.edu.pahana_edu_billing_system.util.db.DBConnection;
import lk.pahana.edu.pahana_edu_billing_system.util.db.SqlQueries;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public void save(Customer customer) {
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement pstm = connection.prepareStatement(SqlQueries.Customer.INSERT)
        ) {
            pstm.setString(1, customer.getCustomerId());
            pstm.setString(2, customer.getName());
            pstm.setString(3, customer.getAddress());
            pstm.setString(4, customer.getMobileNumber());
            pstm.setInt(5, customer.getUnitsConsumed());
            pstm.setDate(6, Date.valueOf(customer.getRegistrationDate()));
            pstm.setString(7, customer.getEmail());

            pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
