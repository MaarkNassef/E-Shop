package Session;

public class Rating {
    private String customerName;
    private float rate;
    private String comment;

    public Rating(String customerName, float rate, String comment) {
        this.customerName = customerName;
        this.rate = rate;
        this.comment = comment;
    }

    public Rating() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
