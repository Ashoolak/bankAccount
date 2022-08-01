package persistence;

import model.BankAccount;
import model.BankCard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Used code from the provided example
// Represents a reader that reads bank account from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads bank account from file and returns it;
    // throws IOException if an error occurs reading data from file
    public BankAccount read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBankAccount(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses bank account from JSON object and returns it
    private BankAccount parseBankAccount(JSONObject jsonObject) {
        BankAccount ba = new BankAccount();;
        addCards(ba, jsonObject);
        return ba;
    }

    // MODIFIES: wr
    // EFFECTS: parses thingies from JSON object and adds them to bank account
    private void addCards(BankAccount ba, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("bankCards");
        for (Object json : jsonArray) {
            JSONObject nextCard = (JSONObject) json;
            addBankCard(ba, nextCard);
        }
    }

    // MODIFIES: ba
    // EFFECTS: parses thingy from JSON object and adds it to bank account
    private void addBankCard(BankAccount ba, JSONObject jsonObject) {
        int cvv = jsonObject.getInt("cvv");
        String cardNum = jsonObject.getString("cardNum");
        int expiryDate = jsonObject.getInt("expiryDate");
        double balance = jsonObject.getDouble("balance");
        String name = jsonObject.getString("name");
        BankCard bankCard = new BankCard(cardNum, cvv, expiryDate, name, balance);
        ba.addCard(bankCard);
    }
}
