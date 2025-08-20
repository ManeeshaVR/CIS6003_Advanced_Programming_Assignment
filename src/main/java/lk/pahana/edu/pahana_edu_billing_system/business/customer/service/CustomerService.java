package lk.pahana.edu.pahana_edu_billing_system.business.customer.service;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {

    boolean saveCustomer(CustomerDTO customer);

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(String id);

    boolean updateCustomer(String id, CustomerDTO updatedCustomer);

    boolean deleteCustomer(String id);

    void addUnitsConsumed(String id, int units);

    int getCustomerCount();

    List<CustomerDTO> getTopCustomers();

    boolean existsCustomerByEmail(String email, String id);

    boolean existsCustomerByMobileNumber(String mobileNumber, String id);

}
