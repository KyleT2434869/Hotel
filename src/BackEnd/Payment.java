package BackEnd;

public class Payment {
    private String paymentID;
    private String method;
    private double amount;
    private String date;

    public Payment(String paymentID, String method, double amount, String date) {
        this.paymentID = paymentID;
        this.method = method;
        this.amount = amount;
        this.date = date;
    }
    public String getPaymentID() {
        return paymentID;
    }
    public String getMethod() {
        return method;
    }
    public double getAmount() {
        return amount;
    }
    public String getDate() {
        return date;
    }
}