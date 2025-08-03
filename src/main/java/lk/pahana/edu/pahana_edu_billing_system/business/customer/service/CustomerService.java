package lk.pahana.edu.pahana_edu_billing_system.business.customer.service;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDTO customer);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(String id);

}
