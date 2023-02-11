package Session;
import android.graphics.Bitmap;
public class Product {
    private String Name;
    private String Price;
    private String[] prodName;
    private String[] prodPrice;
    private String[] Quantity;
    private Bitmap[] bmp;
    public String[] getQuantity() {
        return Quantity;
    }

    public void setQuantity(String[] quantity) {
        Quantity = quantity;
    }

    public String[] getProdName() {
        return prodName;
    }

    public void setProdName(String[] prodName) {
        this.prodName = prodName;
    }

    public Bitmap[] getBmp() {
        return bmp;
    }

    public void setBmp(Bitmap[] bmp) {
        this.bmp = bmp;
    }

    public String[] getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String[] prodPrice) {
        this.prodPrice = prodPrice;
    }

    public Product(String[] prodName, String[] prodPrice,Bitmap[] bmp,String[] Quantity) {
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.bmp=bmp;
        this.Quantity=Quantity;
    }

    public Product(String[] prodName, String[] quantity) {
        this.prodName = prodName;
        Quantity = quantity;
    }

    public Product(String name, String price) {
        Name = name;
        Price = price;
    }

    public Product() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}