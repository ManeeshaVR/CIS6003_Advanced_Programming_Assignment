package lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.mapper.CustomerMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.CustomerDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.impl.CustomerDAOImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CustomerServiceImpl implements CustomerService {

    CustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(UUID.randomUUID().toString());
        customerDTO.setRegistrationDate(LocalDate.now());
        customerDTO.setUnitsConsumed(0);
        customerDAO.save(CustomerMapper.toEntity(customerDTO));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        List<Customer> customerList = customerDAO.findAll();

        for (Customer customer : customerList) {
            customerDTOList.add(CustomerMapper.toDTO(customer));
        }
        return customerDTOList;
    }

    @Override
    public CustomerDTO getCustomerById(String id) {
        return CustomerMapper.toDTO(customerDAO.findById(id));
    }

    @Override
    public void updateCustomer(String id, CustomerDTO customerDTO) {
        customerDAO.update(id, CustomerMapper.toEntity(customerDTO));
    }

    @Override
    public void deleteCustomer(String id) {
        customerDAO.delete(id);
    }

    @Override
    public void addUnitsConsumed(String id, int units) {
        customerDAO.addUnitsConsumed(id, units);
    }

    @Override
    public int getCustomerCount() {
        return customerDAO.getCount();
    }

}
