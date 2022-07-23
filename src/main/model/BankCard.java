package model;

// Represents a bank card with a card number, cvv, expiry date and the owner's name
public class BankCard {
    private int cardNum;
    private int cvv;
    private int expiryDate;
    private int balance;
    private String name;

    public BankCard() {

    }

    public void deposit() {

    }

    public void withdraw() {

    }

    public void purchase() {

    }

    public double getBalance() {
        return 0.0;
    }

    public String getName(int cardNum) {
        return "";
    }

    public int getCVV(int cardNum) {
        return 0;
    }

    public int getExpiryDate(int cardNum) {
        return 0;
    }
}
