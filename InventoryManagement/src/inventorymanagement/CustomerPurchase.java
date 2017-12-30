package inventorymanagement;

import java.time.LocalDate;

public class CustomerPurchase {
    private String customerId;
    private String productId;
    private LocalDate date;
    private double quantity;
    private double price;
    private CustomerPurchaseType type;

    public CustomerPurchase(String customerId, String productId, LocalDate date, double quantity, double price, CustomerPurchaseType type) {
        this.customerId = customerId;
        this.productId = productId;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public CustomerPurchaseType getType() {
        return type;
    }

    public void setType(CustomerPurchaseType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return customerId + productId + date + quantity + price + type;
    }
    
}
