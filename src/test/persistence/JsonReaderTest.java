package persistence;

import model.BankAccount;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Used code from the provided example
class JsonReaderTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            BankAccount ba = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBankAccount() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBankAccount.json");
        try {
            BankAccount ba = reader.read();
            assertEquals(0, ba.getBankCards().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralBankAccount() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralBankAccount.json");
        try {
            BankAccount ba = reader.read();
            assertEquals(2, ba.getBankCards().size());
            checkCard("7755", 123, 11223333, "Ash",100.0, ba.getCard(0));
            checkCard("1234", 321, 14082002, "Kaz",50.0, ba.getCard(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
