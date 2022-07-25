package model;

import java.util.ArrayList;

public class BankAccount {
    private ArrayList<BankCard> bankAccount;

    // EFFECTS: Constructs an empty BankAccount object
    public BankAccount() {
        bankAccount = new ArrayList<BankCard>();
    }

    // MODIFIES: this
    // EFFECTS: if the card is not already in the ArrayList
    //              add the card to the ArrayList
    //              return true
    //          else
    //              return false
    public boolean addCard(BankCard card) {
        if (bankAccount.contains(card)) {
            return false;
        } else {
            bankAccount.add(card);
            return true;
        }
    }

    // Requires: The list must not be empty (there has to be at least one card)
    // MODIFIES: this
    // EFFECTS: searches for a card in the list using its card number
    //          if found
    //              removes the card from the arraylist and return the bank card
    //          else
    //              returns null
    public Boolean removeCard(String cardNum) {
        if (getCard(cardNum) == null) {
            return false;
        } else {
            bankAccount.remove(getCard(cardNum));
            return true;
        }
    }

    // EFFECTS: returns the card with the given index
    public BankCard getCard(Integer index) {
        return bankAccount.get(index);
    }

    // EFFECTS: returns the card with the given card number
    public BankCard getCard(String cardNum) {
        for (BankCard card : bankAccount) {
            if (card.getCardNum().equals(cardNum)) {
                return card;
            }
        }
        return null;
    }

    // EFFECTS: returns the number of the cards in the account
    public int cardCount() {
        return bankAccount.size();
    }
}
