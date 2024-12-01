package my.edu.utem.firstapp.model;

public class Expense {
    String expDate, expName;
    Integer expQty;
    float expPrice;

    public Expense() {

    }

    public Expense(String expDate, String expName, Integer expQty, float expPrice) {
        this.expDate = expDate;
        this.expName = expName;
        this.expQty = expQty;
        this.expPrice = expPrice;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public Integer getExpQty() {
        return expQty;
    }

    public void setExpQty(Integer expQty) {
        this.expQty = expQty;
    }

    public float getExpPrise() {
        return expPrice;
    }

    public void setExpPrise(float expPrice) {
        this.expPrice = expPrice;
    }
}
