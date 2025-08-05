package lk.pahana.edu.pahana_edu_billing_system.util.validation;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;

public class Validation {

    public static String validateCustomerDTO(CustomerDTO customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            return "Name is required.";
        }

        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            return "Address is required.";
        }

        if (customer.getMobileNumber() == null || !customer.getMobileNumber().matches("^\\d{10}$")) {
            return "Valid 10-digit mobile number is required.";
        }

        if (customer.getEmail() == null || !customer.getEmail().matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            return "Valid email is required.";
        }

        return null;
    }

}
