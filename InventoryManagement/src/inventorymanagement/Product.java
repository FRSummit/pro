package inventorymanagement;

public class Product {
    private String id;
    private String name;
    private ProductType type;
    private ProductCategory category;
    private String menufacture;
    private String description;
    private double price;

    public Product(String id, String name, ProductType type, ProductCategory category, String menufacture, String description, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.category = category;
        this.menufacture = menufacture;
        this.description = description;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public String getMenufacture() {
        return menufacture;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return id + name + type + category + menufacture + description + price;
    }
    
}
