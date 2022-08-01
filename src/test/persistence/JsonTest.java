package persistence;

import model.BankCard;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCard(String cardNum, Integer cvv, Integer expiryDate,
                             String name, double balance, BankCard card) {
        assertEquals(cardNum, card.getCardNum());
        assertEquals(cvv, card.getCVV());
        assertEquals(expiryDate, card.getExpiryDate());
        assertEquals(name, card.getName());
        assertEquals(balance, card.getBalance());
    }
}
