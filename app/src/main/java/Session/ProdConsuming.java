package Session;

public class ProdConsuming {
    private String ProdID;
    private String Quantity;

    public ProdConsuming(String prodID, String quantity) {
        ProdID = prodID;
        Quantity = quantity;
    }

    public ProdConsuming() {
    }

    public String getProdID() {
        return ProdID;
    }

    public void setProdID(String prodID) {
        ProdID = prodID;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }
}
