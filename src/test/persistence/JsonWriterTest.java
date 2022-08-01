package persistence;

import model.BankAccount;
import model.BankCard;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            BankAccount ba = new BankAccount();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyBankAccount() {
        try {
            BankAccount ba = new BankAccount();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBankAccount.json");
            writer.open();
            writer.write(ba);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBankAccount.json");
            ba = reader.read();
            assertEquals(0, ba.getBankCards().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralBankAccount() {
        try {
            BankAccount ba = new BankAccount();
            ba.addCard(new BankCard("1234", 385, 14082003, "Ash", 100.0));
            ba.addCard(new BankCard("5678", 138, 11223333, "Kaz", 50.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralBankAccount.json");
            writer.open();
            writer.write(ba);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralBankAccount.json");
            ba = reader.read();
            List<BankCard> cards = ba.getBankCards();
            assertEquals(2, cards.size());
            checkCard("1234", 385, 14082003, "Ash",100.0, ba.getCard(0));
            checkCard("5678", 138, 11223333, "Kaz",50.0, ba.getCard(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}