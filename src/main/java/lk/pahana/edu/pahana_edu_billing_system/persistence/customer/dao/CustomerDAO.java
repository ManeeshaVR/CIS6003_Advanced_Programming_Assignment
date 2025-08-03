package lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;

import java.util.List;

public interface CustomerDAO {

    void save(Customer customer);

    List<Customer> findAll();

    Customer findById(String id);

    void update(String id, Customer customer);

    void delete(String id);

}
