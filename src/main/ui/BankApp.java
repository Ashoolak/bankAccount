package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Represents the bank account application
public class BankApp extends JFrame implements ActionListener {
    private static final String JSON_STORE = "./data/bankAccount.json";
    private Scanner input;
    private BankAccount bankAccount;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private String cardNum;
    private String name;
    private int cvv;
    private int expiryDate;
    private double balance;
    private double amount;
    private BankCard card;
    private int repeat;
    private int menuChoice;

    private JLabel label = new JLabel();
    private JPanel panel = new JPanel();
    private JTextArea textArea = new JTextArea();
    private ImageIcon image;

    // EFFECTS: action listener for adding a card
    private ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            addCard();
            textArea.setText(allCardNums());
        }
    };

    // EFFECTS: action listener for removing a card
    private ActionListener removeListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeCard();
            textArea.setText(allCardNums());
        }
    };

    // EFFECTS: action listener for saving a card
    private ActionListener saveListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveBankAccount();
        }
    };

    // EFFECTS: action listener for choosing a card
    private ActionListener chooseCardListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            cardMenu();
        }
    };

    // EFFECTS: action listener for loading an account
    private ActionListener loadAccount = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            loadBankAccount();
            textArea.setText(allCardNums());
        }
    };

    // EFFECTS: action listener for quiting
    private ActionListener quitListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("GoodBye!");
        }
    };

    // EFFECTS: constructs bankAccount and runs application
    public BankApp() throws FileNotFoundException, InterruptedException {
        super("Bank Account");
        input = new Scanner(System.in);
        bankAccount = new BankAccount();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runBankAccount();
    }

    // MODIFIES: GUI
    // EFFECTS: add an image to the GUI
    public void addImage() {
        try {
            image = new ImageIcon(Main.class.getResource("bankAccount.png"));
            label.setText("Welcome to your banking!");
            label.setIcon(image);
            label.setVerticalTextPosition(JLabel.TOP);
            label.setHorizontalTextPosition(JLabel.CENTER);
        } catch (Exception e) {
            System.out.println("Image not found");
        }
    }

    // EFFECTS: Sets up textArea where the cards are being displayed
    private void setupCardDisplay() {
        this.add(textArea);
        this.setLocation(600, 100);
    }

    // EFFECTS: Return all the card numbers as a String
    private String allCardNums() {
        String cardNums = "";
        for (BankCard card : bankAccount.getBankCards()) {
            cardNums += card.getCardNum() + "\n";
        }
        return cardNums;
    }

    // MODIFIES: GUI
    // EFFECTS: Sets up everything for the GUI
    public void setupGui() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(800, 400));

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        addImage();
        add(panel);
        add(label);
        addCardButton();
        removeCardButton();
        chooseCardListener();
        saveAccountListener();
        loadAccountListener();
        quitListener();
        setupCardDisplay();

        pack();
        setVisible(true);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds an addCard button
    public void addCardButton() {
        JButton btnAdd = new JButton("Add a bank card");
        btnAdd.addActionListener(addListener);
        panel.add(btnAdd);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds an removeCard button
    public void removeCardButton() {
        JButton btnRemove = new JButton("Remove a bank card");
        btnRemove.addActionListener(removeListener);
        panel.add(btnRemove);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds a chooseCard button
    public void chooseCardListener() {
        JButton btnChoose = new JButton("Choose a card");
        btnChoose.addActionListener(chooseCardListener);
        panel.add(btnChoose);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds an saveAccount button
    public void saveAccountListener() {
        JButton btnSave = new JButton("Save bank account");
        btnSave.addActionListener(saveListener);
        panel.add(btnSave);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds a loadAccount button
    public void loadAccountListener() {
        JButton btnLoad = new JButton("Load bank account");
        btnLoad.addActionListener(loadAccount);
        panel.add(btnLoad);
    }

    // MODIFIES: GUI
    // EFFECTS: Adds a quit button
    public void quitListener() {
        JButton btnQuit = new JButton("Quit");
        btnQuit.addActionListener(quitListener);
        panel.add(btnQuit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    // MODIFIES: this
    // EFFECTS: processes user input
    public void runBankAccount() throws InterruptedException {
        repeat = 1;
        starter();

        Thread.sleep(1000);

        menu();

        pack();
        setLocationRelativeTo(null);
    }

    // MODIFIES: this
    // EFFECTS: Prompts the user to enter their first card
    public void starter() {
        input = new Scanner(System.in);
        System.out.println("Welcome to myBanking");
        addFirstCard();
    }

    // MODIFIES: this
    // EFFECTS: the user add their first bank card to the bank account
    private void addFirstCard() {
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
        bankAccount = new BankAccount();
        bankAccount.addCard(card);

        System.out.println("You are all set up!");
        textArea.setText(allCardNums());
    }

    // MODIFIES: this
    // EFFECTS: menu of the functions that the user can choose from including adding a new card,
    //          removing an existing card, or choosing a card for card-specific functionalities
    public void menu() {
        System.out.println("Choose an option from the pop-up menu");
        setupGui();

//        System.out.println("Choose of the following options");
//        System.out.println("===============================");
//        System.out.println(" ");
//        System.out.println("1- Add card");
//        System.out.println("2- Remove card");
//        System.out.println("3- Choose a card");
//        System.out.println("4- save bank account to file");
//        System.out.println("5- Quit");
//        menuChoice = Integer.parseInt(input.next());
//
//        if (menuChoice == 1) {
//            addCard();
//        } else if (menuChoice == 2) {
//            removeCard();
//        } else if (menuChoice == 3) {
//            cardMenu();
//        } else if (menuChoice == 4) {
//            saveBankAccount();
//        } else {
//            repeat = 2;
//        }

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
        bankAccount.addCard(card);
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

        if (bankAccount.removeCard(cardNum)) {
            bankAccount.removeCard(cardNum);
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
            deposit(bankAccount.getCard(cardNum), amount);
        } else if (menuChoice == 2) {
            System.out.println("Enter the amount:");
            amount = Double.parseDouble(input.next());
            withdraw(bankAccount.getCard(cardNum), amount);
        } else if (menuChoice == 3) {
            System.out.println("Enter the amount:");
            amount = Double.parseDouble(input.next());
            purchase(bankAccount.getCard(cardNum), amount);
        } else {
            information(bankAccount.getCard(cardNum));
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

    // EFFECTS: saves the bankAccount to file
    private void saveBankAccount() {
        try {
            jsonWriter.open();
            jsonWriter.write(bankAccount);
            jsonWriter.close();
            System.out.println("Saved your account to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads bankAccount from file
    private void loadBankAccount() {
        try {
            bankAccount = jsonReader.read();
            System.out.println("Loaded your account from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}


