package ui;

import model.EventLog;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        try {
            new BankApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
