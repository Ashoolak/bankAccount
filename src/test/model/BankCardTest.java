package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BankCardTest {
    private BankCard card1;

    @BeforeEach
    public void setup() {
        card1 = new BankCard("1234567887654321", 398, 14082003, "Ali", 1000.0);
    }

    @Test
    public void bankCardTest() {
        assertEquals("1234567887654321", card1.getCardNum());
        assertEquals(398, card1.getCVV());
        assertEquals(14082003, card1.getExpiryDate());
        assertEquals("Ali", card1.getName());
        assertEquals(1000.0, card1.getBalance());
    }

    @Test
    public void depositTest() {
        assertEquals(1000.0, card1.getBalance());
        card1.deposit(100);
        assertEquals(1100.0, card1.getBalance());
        card1.deposit(0);
        assertEquals(1100.0, card1.getBalance());

    }

    @Test
    public void withdrawTest() {
        assertEquals(1000.0, card1.getBalance());
        assertTrue(card1.withdraw(100));
        assertTrue(card1.withdraw(899));
        assertTrue(card1.withdraw(1));
        assertFalse(card1.withdraw(1));
    }

    @Test
    public void purchaseTest() {
        assertEquals(1000.0, card1.getBalance());
        assertTrue(card1.purchase(100));
        assertTrue(card1.purchase(899));
        assertTrue(card1.purchase(1));
        assertFalse(card1.purchase(1));
    }

    @Test
    public void getCardNum() {
        assertEquals("1234567887654321", card1.getCardNum());
    }

    @Test
    public void getBalanceTest() {
        assertEquals(1000.0, card1.getBalance());
    }

    @Test
    public void getNameTest() {
        assertEquals("Ali", card1.getName());
    }

    @Test
    public void getCVVTest() {
        assertEquals(398, card1.getCVV());
    }

    @Test
    public void getExpiryDateTest() {
        assertEquals(14082003, card1.getExpiryDate());
    }
}