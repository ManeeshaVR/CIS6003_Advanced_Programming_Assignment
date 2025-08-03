package lk.pahana.edu.pahana_edu_billing_system.business.customer.service.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.mapper.CustomerMapper;
import lk.pahana.edu.pahana_edu_billing_system.business.customer.service.CustomerService;
import lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.CustomerDAO;
import lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.impl.CustomerDAOImpl;

import java.time.LocalDate;
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

}
