package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a bank card with a card number, cvv, expiry date, the owner's name, and balance
public class BankCard implements Writable {
    private final String cardNum;
    private final int cvv;
    private final int expiryDate;
    private final String name;
    private double balance;

    // EFFECTS: Creates a bank card object with a card number, cvv, expiry date
    //          in the format of DayMonthYear(14082003 for instance) and balance
    public BankCard(String cardNum, Integer cvv, Integer expiryDate, String name, double balance) {
        this.cardNum = cardNum;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.name = name;
        this.balance = balance;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: increases balance by amount
    public void deposit(Double amount) {
        balance += amount;
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: if amount <= balance
    //              decreases balance by amount
    //              return true
    //          else
    //              return false
    public boolean withdraw(Double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: if amount <= balance
    //              decreases balance by amount
    //              return true
    //          else
    //              return false
    public boolean purchase(Double amount) {
        if (amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: getter method for cardNum
    public String getCardNum() {
        return cardNum;
    }

    // EFFECTS: getter method for balance
    public double getBalance() {
        return balance;
    }

    // EFFECTS: getter method for name
    public String getName() {
        return name;
    }

    // EFFECTS: getter method for cvv
    public int getCVV() {
        return cvv;
    }

    // EFFECTS: getter method for expiryDate
    public int getExpiryDate() {
        return expiryDate;
    }

    // Used code from the provided example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("cardNum", cardNum);
        json.put("cvv", cvv);
        json.put("expiryDate", expiryDate);
        json.put("balance", balance);
        json.put("name", name);
        return json;
    }
}
