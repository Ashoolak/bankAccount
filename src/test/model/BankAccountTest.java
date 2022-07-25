package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {
    private BankCard card1, card2, card3;
    private BankAccount account1;

    @BeforeEach
    public void setup() {
        account1 = new BankAccount();
        card1 = new BankCard("1111333355557777", 999, 12942028, "Alec", 200.0);
        card2 = new BankCard("5649174691847818", 182, 20112030, "Ash", 100.0);
        card3 = new BankCard("3785401749286705", 427, 01012025, "Kaz", 0.0);
    }

    @Test
    public void addCardTest() {
        assertEquals(0, account1.cardCount());
        account1.addCard(card1);
        account1.addCard(card2);
        assertEquals(2, account1.cardCount());
        assertEquals(card1, account1.getCard(0));
        assertEquals(card2, account1.getCard(1));
    }

    @Test
    public void removeCardTest() {
        assertEquals(0, account1.cardCount());
        account1.addCard(card1);
        account1.addCard(card2);
        assertEquals(2, account1.cardCount());
        assertTrue(account1.removeCard("1111333355557777"));
        assertEquals(1, account1.cardCount());
        assertEquals(null, account1.getRemovedCard("1111333355557777"));
        assertFalse(account1.removeCard("1111333355557777"));
        assertTrue(account1.removeCard("5649174691847818"));
        assertEquals(0, account1.cardCount());
        assertEquals(null, account1.getRemovedCard("5649174691847818"));
    }
}
