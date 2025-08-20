package lk.pahana.edu.pahana_edu_billing_system.business.item.model;

public class Item {
    private String itemCode;
    private String itemName;
    private String category;
    private String description;
    private Double unitPrice;
    private Integer stockQuantity;
    private String publisher;
    private String author;

    public Item() {}

    public Item(String itemCode, String itemName, String category, String description, Double unitPrice, Integer stockQuantity, String publisher, String author) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.category = category;
        this.description = description;
        this.unitPrice = unitPrice;
        this.stockQuantity = stockQuantity;
        this.publisher = publisher;
        this.author = author;
    }

    private Item(Builder builder) {
        this.itemCode = builder.itemCode;
        this.itemName = builder.itemName;
        this.category = builder.category;
        this.description = builder.description;
        this.unitPrice = builder.unitPrice;
        this.stockQuantity = builder.stockQuantity;
        this.publisher = builder.publisher;
        this.author = builder.author;
    }

    public static class Builder {
        private String itemCode;
        private String itemName;
        private String category;
        private String description;
        private Double unitPrice;
        private Integer stockQuantity;
        private String publisher;
        private String author;

        public Builder setItemCode(String itemCode) {
            this.itemCode = itemCode;
            return this;
        }

        public Builder setItemName(String itemName) {
            this.itemName = itemName;
            return this;
        }

        public Builder setCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUnitPrice(Double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setStockQuantity(Integer stockQuantity) {
            this.stockQuantity = stockQuantity;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Item build() {
            return new Item(this);
        }
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", itemName='" + itemName + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", stockQuantity=" + stockQuantity +
                ", publisher='" + publisher + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
