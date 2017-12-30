package inventorymanagement;

public class Customer {
    private String id;
    private String name;
    private CustomerType type;
    private String phone;
    private String email;
    private String address;

    public Customer(String id, String name, CustomerType type, String phone, String email, String address) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CustomerType getType() {
        return type;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setType(CustomerType type) {
        this.type = type;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return id + name + type + phone + email + address;
    }
    
}
