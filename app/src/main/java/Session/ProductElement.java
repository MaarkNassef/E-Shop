package Session;

public class ProductElement {
    private String ProdName;
    private String ProdPrice;
    private String ProdQuantity;
    private String ProdDescription;

    public ProductElement(String prodName, String prodPrice, String prodQuantity, String prodDescription) {
        ProdName = prodName;
        ProdPrice = prodPrice;
        ProdQuantity = prodQuantity;
        ProdDescription = prodDescription;
    }

    public ProductElement() {
    }

    public String getProdName() {
        return ProdName;
    }

    public void setProdName(String prodName) {
        ProdName = prodName;
    }

    public String getProdPrice() {
        return ProdPrice;
    }

    public void setProdPrice(String prodPrice) {
        ProdPrice = prodPrice;
    }

    public String getProdQuantity() {
        return ProdQuantity;
    }

    public void setProdQuantity(String prodQuantity) {
        ProdQuantity = prodQuantity;
    }

    public String getProdDescription() {
        return ProdDescription;
    }

    public void setProdDescription(String prodDescription) {
        ProdDescription = prodDescription;
    }
}
