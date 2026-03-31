package entity;

public class Product {
    private int productId;
    private String productName;
    private String storage;
    private String color;
    private Double price;
    private int stock;

    public Product() {
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Product(int productId, String productName, String storage, String color, Double price, int stock) {
        this.productId = productId;
        this.productName = productName;
        this.storage = storage;
        this.color = color;
        this.price = price;
        this.stock = stock;
    }
}
