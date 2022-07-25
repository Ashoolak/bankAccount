package ui;

import model.*;

import java.util.Scanner;

public class BankApp {
    private Scanner input;
    private String cardNum;
    private String name;
    private int cvv;
    private int expiryDate;
    private double balance;
    private double amount;
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

    // MODIFIES: this
    // EFFECTS: Prompts the user to enter their first card
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

    // MODIFIES: this
    // EFFECTS: menu of the functions that the user can choose from including adding a new card,
    //          removing an existing card, or choosing a card for card-specific functionalities
    public void menu() {
        System.out.println("Choose of the following options");
        System.out.println("===============================");
        System.out.println(" ");
        System.out.println("1- Add card");
        System.out.println("2- Remove card");
        System.out.println("3- Choose a card");
        System.out.println("4- Quit");
        menuChoice = Integer.parseInt(input.next());

        if (menuChoice == 1) {
            addCard();
        } else if (menuChoice == 2) {
            removeCard();
        } else if (menuChoice == 3) {
            cardMenu();
        } else {
            repeat = 2;
        }

    }

    // MODIFIES: this
    // EFFECTS: prompts the user to enter their new card's information and creates a card with that
    // information and adds to their account.
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

    // MODIFIES: this
    // EFFECTS: prompts to user to enter the card number of the card they wish to remove
    //          if a card with the given card number exists
    //              removes the card from the account
    //          else
    //              displays "Card not found"
    public void removeCard() {
        System.out.println("Enter the card number of the card that you wish to remove");
        cardNum = input.next();

        if (account.removeCard(cardNum)) {
            account.removeCard(cardNum);
            System.out.println("card with card number " + cardNum + " has been removed");
        } else {
            System.out.println("Card not found");
        }
    }

    // REQUIRES: the cardNum must be an existing cardNum in the account
    // MODIFIES: this
    // EFFECTS: the user enters their card number and then is given a bunch of options
    //          if menuChoice = 1
    //              program goes to deposit() method
    //          if menuChoice = 1
    //              program goes to withdraw() method
    //          if menuChoice = 1
    //              program goes to purchase() method
    //          else
    //              program goes to information() method
    public void cardMenu() {
        System.out.println("Enter the card number:");
        cardNum = input.next();

        System.out.println("Choose from the following options");
        System.out.println("1- deposit money");
        System.out.println("2- withdraw money");
        System.out.println("3- make a purchase");
        System.out.println("4- get card information");
        menuChoice = Integer.parseInt(input.next());

        if (menuChoice == 1) {
            System.out.println("Enter the amount:");
            amount = Double.parseDouble(input.next());
            deposit(account.getCard(cardNum), amount);
        } else if (menuChoice == 2) {
            System.out.println("Enter the amount:");
            amount = Double.parseDouble(input.next());
            withdraw(account.getCard(cardNum), amount);
        } else if (menuChoice == 3) {
            System.out.println("Enter the amount:");
            amount = Double.parseDouble(input.next());
            purchase(account.getCard(cardNum), amount);
        } else {
            information(account.getCard(cardNum));
        }
    }

    // MODIFIES: this
    // EFFECTS: increases card's balance by amount
    public void deposit(BankCard card, Double amount) {
        card.deposit(amount);
        System.out.println("Card balance: $" + card.getBalance());
    }

    // MODIFIES: this
    // EFFECTS: if card.withdraw(amount) is true
    //              prints Insufficient balance because amount > balance
    //          else
    //              prints the new card balance after balance - amount
    public void withdraw(BankCard card, Double amount) {
        if (!card.withdraw(amount)) {
            System.out.println("Insufficient balance");
        }
        System.out.println("Card balance: $" + card.getBalance());
    }

    // MODIFIES: this
    // EFFECTS: if card.purchase(amount) is true
    //              prints Insufficient balance because amount > balance
    //          else
    //              prints the new card balance after balance - amount
    public void purchase(BankCard card, Double amount) {
        if (!card.purchase(amount)) {
            System.out.println("Insufficient balance");
        }
        System.out.println("Card balance: $" + card.getBalance());
    }

    // EFFECTS: prints out all the information associated with that card including cardNum, name,
    //          cvv, expiryDate, and balance
    public void information(BankCard card) {
        System.out.println("Card number: " + card.getCardNum());
        System.out.println("Holder's name: " + card.getName());
        System.out.println("cvv: " + card.getCVV());
        System.out.print("Expiry date: " + String.valueOf(card.getExpiryDate()).substring(0,2));
        System.out.print("/" + String.valueOf(card.getExpiryDate()).substring(2,4));
        System.out.println("/" + String.valueOf(card.getExpiryDate()).substring(4));
        System.out.println("Balance: " + card.getBalance());
    }

}
