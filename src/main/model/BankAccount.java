package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.smartcardio.Card;
import java.util.ArrayList;

public class BankAccount implements Writable {
    private ArrayList<BankCard> bankCards;

    // EFFECTS: Constructs an empty BankAccount object
    public BankAccount() {
        bankCards = new ArrayList<BankCard>();
    }

    // MODIFIES: this
    // EFFECTS: if the card is not already in the ArrayList
    //              add the card to the ArrayList
    //              return true
    //          else
    //              return false
    public boolean addCard(BankCard card) {
        if (bankCards.contains(card)) {
            return false;
        } else {
            bankCards.add(card);
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
            bankCards.remove(getCard(cardNum));
            return true;
        }
    }

    // EFFECTS: returns the card with the given index
    public BankCard getCard(Integer index) {
        return bankCards.get(index);
    }

    // EFFECTS: returns the card with the given card number
    public BankCard getCard(String cardNum) {
        for (BankCard card : bankCards) {
            if (card.getCardNum().equals(cardNum)) {
                return card;
            }
        }
        return null;
    }

    // EFFECTS: returns the number of the cards in the account
    public int cardCount() {
        return bankCards.size();
    }

    // Used code from the provided example
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("bankCards", bankAccountToJson());
        return json;
    }

    // Used code from the provided example
    // EFFECTS: returns things in this account as a JSON array
    private JSONArray bankAccountToJson() {
        JSONArray jsonArray = new JSONArray();

        for (BankCard t : bankCards) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: getter gor bank cards
    public ArrayList<BankCard> getBankCards() {
        return bankCards;
    }
}
