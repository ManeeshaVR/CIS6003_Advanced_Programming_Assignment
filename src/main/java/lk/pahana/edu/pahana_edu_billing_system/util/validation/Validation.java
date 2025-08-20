package lk.pahana.edu.pahana_edu_billing_system.util.validation;

import lk.pahana.edu.pahana_edu_billing_system.business.customer.dto.CustomerDTO;
import lk.pahana.edu.pahana_edu_billing_system.business.item.dto.ItemDTO;

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

    public static String validateItemDTO(ItemDTO item) {
        if (item.getItemName() == null || item.getItemName().trim().isEmpty()) {
            return "Item name is required.";
        }

        if (item.getCategory() == null || item.getCategory().trim().isEmpty()) {
            return "Category is required.";
        }

        if (item.getDescription() == null || item.getDescription().trim().isEmpty()) {
            return "Description is required.";
        }

        if (item.getUnitPrice() == null || item.getUnitPrice() <= 0) {
            return "Unit price must be greater than 0.";
        }

        if (item.getStockQuantity() == null || item.getStockQuantity() < 0) {
            return "Stock quantity cannot be negative.";
        }

        if (item.getPublisher() == null || item.getPublisher().trim().isEmpty()) {
            return "Publisher is required.";
        }

        if (item.getAuthor() == null || item.getAuthor().trim().isEmpty()) {
            return "Author is required.";
        }

        return null;
    }

}
