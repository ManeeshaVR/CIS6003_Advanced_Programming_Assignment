package lk.pahana.edu.pahana_edu_billing_system.persistence.customer.dao.impl;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.model.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOImplTest {

    private static CustomerDAOImpl customerDAO;

    @BeforeAll
    static void setup() {
        customerDAO = new CustomerDAOImpl();
    }

    @BeforeEach
    void cleanUpBeforeEach() {
        // delete test IDs to avoid duplicates
        customerDAO.delete("C001");
        customerDAO.delete("C002");
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        boolean result = customerDAO.save(customer);
        assertTrue(result);

        Customer saved = customerDAO.findById("C001");
        assertNotNull(saved);
        assertEquals("Vimukthi", saved.getName());
    }

    @Test
    void testFindByIdNotFound() {
        Customer customer = customerDAO.findById("INVALID_ID");
        assertNull(customer);
    }

    @Test
    void testFindAll() {
        Customer c1 = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        Customer c2 = new Customer.Builder()
                .customerId("C002")
                .name("Adikari")
                .address("Kandy")
                .mobileNumber("0777479285")
                .unitsConsumed(20)
                .registrationDate(LocalDate.now())
                .email("adi@example.com")
                .build();

        customerDAO.save(c1);
        customerDAO.save(c2);

        List<Customer> customers = customerDAO.findAll();
        assertTrue(customers.size() >= 2);
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(30)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        customerDAO.save(customer);

        Customer updated = new Customer.Builder()
                .customerId("C001")
                .name("Updated Name")
                .address("Kandy")
                .mobileNumber("0751111111")
                .unitsConsumed(15)
                .registrationDate(LocalDate.now())
                .email("updated@example.com")
                .build();

        boolean result = customerDAO.update("C001", updated);
        assertTrue(result);

        Customer check = customerDAO.findById("C001");
        assertEquals("Updated Name", check.getName());
        assertEquals("updated@example.com", check.getEmail());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        customerDAO.save(customer);

        boolean result = customerDAO.delete("C001");
        assertTrue(result);

        Customer deleted = customerDAO.findById("C001");
        assertNull(deleted);
    }

    @Test
    void testGetCount() {
        int initialCount = customerDAO.getCount();

        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        customerDAO.save(customer);

        int newCount = customerDAO.getCount();
        assertEquals(initialCount + 1, newCount);
    }

    @Test
    void testCheckEmailExists() {
        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        customerDAO.save(customer);

        boolean available = customerDAO.existsByEmail("adi@example.com", null);
        assertFalse(available);

        boolean notAvailable = customerDAO.existsByEmail("vimu@example.com", null);
        assertTrue(notAvailable);
    }

    @Test
    void testCheckMobileNumberExists() {
        Customer customer = new Customer.Builder()
                .customerId("C001")
                .name("Vimukthi")
                .address("Colombo")
                .mobileNumber("0713618601")
                .unitsConsumed(10)
                .registrationDate(LocalDate.now())
                .email("vimu@example.com")
                .build();

        customerDAO.save(customer);

        boolean available = customerDAO.existsByMobileNumber("0723618601", null);
        assertFalse(available);

        boolean notAvailable = customerDAO.existsByMobileNumber("0713618601", null);
        assertTrue(notAvailable);
    }
}
