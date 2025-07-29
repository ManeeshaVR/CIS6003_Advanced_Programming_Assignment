package lk.pahana.edu.pahana_edu_billing_system.business.customer.dto;

import java.time.LocalDate;

public class CustomerDTO {
    private String customerId;
    private String name;
    private String address;
    private String mobileNumber;
    private int unitsConsumed;
    private LocalDate registrationDate;
    private String email;

    public CustomerDTO() {}

    public CustomerDTO(String customerId, String name, String address, String mobileNumber, int unitsConsumed, LocalDate registrationDate, String email) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.mobileNumber = mobileNumber;
        this.unitsConsumed = unitsConsumed;
        this.registrationDate = registrationDate;
        this.email = email;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getUnitsConsumed() {
        return unitsConsumed;
    }

    public void setUnitsConsumed(int unitsConsumed) {
        this.unitsConsumed = unitsConsumed;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
