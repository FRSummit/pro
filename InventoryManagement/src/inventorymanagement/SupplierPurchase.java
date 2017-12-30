package inventorymanagement;

import java.time.LocalDate;

public class SupplierPurchase {
    private String supplierId;
    private String productId;
    private LocalDate date;
    private double quantity;
    private double price;

    public SupplierPurchase(String supplierId, String productId, LocalDate date, double quantity, double price) {
        this.supplierId = supplierId;
        this.productId = productId;
        this.date = date;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSupplierId() {
        return supplierId;
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

    @Override
    public String toString() {
        return supplierId + productId + date + quantity + price;
    }
    
}
