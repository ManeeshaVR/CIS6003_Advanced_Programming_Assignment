package lk.pahana.edu.pahana_edu_billing_system.business.bill.model;

import java.time.LocalDateTime;
import java.util.List;

public class Bill {

    private String billId;
    private List<BillItem> billItems;
    private Double totalAmount;
    private String customerId;
    private LocalDateTime date;

    public Bill() {
    }

    public Bill(String billId, List<BillItem> billItems, Double totalAmount, String customerId, LocalDateTime date) {
        this.billId = billId;
        this.billItems = billItems;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.date = date;
    }

    private Bill(Builder builder) {
        this.billId = builder.billId;
        this.billItems = builder.billItems;
        this.totalAmount = builder.totalAmount;
        this.customerId = builder.customerId;
        this.date = builder.date;
    }

    public static class Builder {
        private String billId;
        private List<BillItem> billItems;
        private Double totalAmount;
        private String customerId;
        private LocalDateTime date;

        public Builder setBillId(String billId) {
            this.billId = billId;
            return this;
        }

        public Builder setBillItems(List<BillItem> billItems) {
            this.billItems = billItems;
            return this;
        }

        public Builder setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setCustomerId(String customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public Bill build() {
            return new Bill(this);
        }
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<BillItem> billItems) {
        this.billItems = billItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", billItems=" + billItems +
                ", totalAmount=" + totalAmount +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                '}';
    }
}
