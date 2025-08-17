package lk.pahana.edu.pahana_edu_billing_system.business.bill.dto;

import java.time.LocalDateTime;
import java.util.List;

public class InvoiceDTO {

    private String billId;
    private List<InvoiceItemDTO> billItems;
    private Double totalAmount;
    private String customerId;
    private LocalDateTime date;

    private InvoiceDTO(Builder builder) {
        this.billId = builder.billId;
        this.billItems = builder.billItems;
        this.totalAmount = builder.totalAmount;
        this.customerId = builder.customerId;
        this.date = builder.date;
    }

    public static class Builder {
        private String billId;
        private List<InvoiceItemDTO> billItems;
        private Double totalAmount;
        private String customerId;
        private LocalDateTime date;

        public Builder setBillId(String billId) {
            this.billId = billId;
            return this;
        }

        public Builder setBillItems(List<InvoiceItemDTO> billItems) {
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

        public InvoiceDTO build() {
            return new InvoiceDTO(this);
        }
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public List<InvoiceItemDTO> getBillItems() {
        return billItems;
    }

    public void setBillItems(List<InvoiceItemDTO> billItems) {
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
        return "InvoiceDTO{" +
                "billId='" + billId + '\'' +
                ", billItems=" + billItems +
                ", totalAmount=" + totalAmount +
                ", customerId='" + customerId + '\'' +
                ", date=" + date +
                '}';
    }

}
