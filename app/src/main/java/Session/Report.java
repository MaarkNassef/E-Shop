package Session;

public class Report {
    private String orderDate;
    private String customerName;
    private String ProductName;
    private String ProductQuantity;
    private String ProductPrice;

    public Report(String orderDate, String customerName, String productName, String productQuantity, String productPrice) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        ProductName = productName;
        ProductQuantity = productQuantity;
        ProductPrice = productPrice;
    }

    public Report() {
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getProductQuantity() {
        return ProductQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        ProductQuantity = productQuantity;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }
}
