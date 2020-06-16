package Model;

public class Order {
    private String ProductId;
    private String ProductName;

    public Order() {
    }

    public Order(String productId, String productName) {
        ProductId = productId;
        ProductName = productName;
    }

    public String getProductId() {
        return ProductId;
    }

    public void setProductId(String productId) {
        ProductId = productId;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }
}
