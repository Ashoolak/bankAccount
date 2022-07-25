package ui;

import model.*;

import java.util.Scanner;

import static javafx.application.Platform.exit;

public class BankApp {
    private Scanner input;
    private String cardNum;
    private String name;
    private int cvv;
    private int expiryDate;
    private double balance;
    private BankCard card;
    private BankAccount account;
    private int repeat;
    private int menuChoice;
    //String cardNum, Integer cvv, Integer expiryDate, String name, double balance

    // EFFECTS: Runs the bank application
    public BankApp() throws InterruptedException {
        repeat = 1;
        starter();

        Thread.sleep(1000);

        while (repeat == 1) {
            menu();
        }

        System.out.println("GoodBye!");
    }

    public void starter() {
        input = new Scanner(System.in);

        System.out.println("Welcome to myBanking");
        System.out.println("It's time to add your first card!");

        System.out.println("Enter the card number:");
        cardNum = input.next();

        System.out.println("Enter the card holder's name:");
        name = input.next();

        System.out.println("Enter the CVV:");
        cvv = Integer.parseInt(input.next());

        System.out.println("Enter the expiry date in DDMMYYYY format:");
        expiryDate = Integer.parseInt(input.next());

        System.out.println("Enter the balance that you wish to add to your card:");
        balance = Double.parseDouble(input.next());

        card = new BankCard(cardNum, cvv, expiryDate, name, balance);
        account = new BankAccount();
        account.addCard(card);

        System.out.println("You are all set up!");
    }

    public void menu() {
        System.out.println("Choose of the following options");
        System.out.println("===============================");
        System.out.println("");
        System.out.println("1- Add card");
        System.out.println("2- Remove card");
        System.out.println("3- Choose a card");
        System.out.println("4 - Quit");
        menuChoice = Integer.parseInt(input.next());

        if (menuChoice == 1) {
            addCard();
        } else if (menuChoice == 2) {
            removeCard();
        } else if (menuChoice == 3) {
            cardMenu();
        } else {
            System.out.println("Goodbye!");
            exit();
        }

    }

    public void addCard() {
        System.out.println("Enter the card number:");
        cardNum = input.next();

        System.out.println("Enter the card holder's name:");
        name = input.next();

        System.out.println("Enter the CVV:");
        cvv = Integer.parseInt(input.next());

        System.out.println("Enter the expiry date in DDMMYYYY format:");
        expiryDate = Integer.parseInt(input.next());

        System.out.println("Enter the balance that you wish to add to your card:");
        balance = Double.parseDouble(input.next());

        card = new BankCard(cardNum, cvv, expiryDate, name, balance);
        account.addCard(card);
    }

    public void removeCard() {
        System.out.println("Enter the card number of the card that you wish to remove");
        cardNum = input.next();

        if (account.removeCard(cardNum) == true) {
            account.removeCard(cardNum);
            System.out.println("card with card number " + cardNum + " has been removed");
        } else {
            System.out.println("Card not found");
        }
    }

    public void cardMenu() {

    }

}
