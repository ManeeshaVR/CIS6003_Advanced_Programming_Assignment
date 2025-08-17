package lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;

import java.util.List;

public interface CustomerDAO {

    boolean save(Customer customer);

    List<Customer> findAll();

    Customer findById(String id);

    boolean update(String id, Customer customer);

    boolean delete(String id);

    void addUnitsConsumed(String id, int units);

    int getCount();

    List<Customer> findTopCustomers();

    boolean existsByEmail(String email, String id);

    boolean existsByMobileNumber(String mobileNumber, String id);

}
