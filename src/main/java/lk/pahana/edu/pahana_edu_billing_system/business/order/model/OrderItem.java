package lk.pahana.edu.pahana_edu_billing_system.business.order.model;

public class OrderItem {

    private String itemCode;
    private Integer quantity;
    private Double unitPrice;

    public OrderItem() {}

    public OrderItem(String itemCode, Integer quantity, Double unitPrice) {
        this.itemCode = itemCode;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    private OrderItem(Builder builder) {
        this.itemCode = builder.itemCode;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
    }

    public static class Builder {
        private String itemCode;
        private Integer quantity;
        private Double unitPrice;

        public Builder itemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder unitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemCode='" + itemCode + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
