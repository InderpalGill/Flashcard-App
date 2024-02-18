package ui;


import model.Flashcard;
import model.FlashcardDeck;
import model.FlashcardDecks;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;

// Application for Flashcard Program references the TellerApp
public class FlashcardApp {
    private FlashcardDeck deck;  //for demonstration
    private Flashcard flashcard; //for demonstration
    private Scanner input;
    private FlashcardDecks myFlashcardDecks;
    private FlashcardDeck currentDeck;
    private Flashcard currentCard;

    public FlashcardApp() {
        runFlashcardApp();
    }

    private void runFlashcardApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: initializes the program and creates a sample Flashcard deck for demonstration
    private void init() {
        myFlashcardDecks = new FlashcardDecks();
        deck = new FlashcardDeck("year");
        flashcard = new Flashcard("What year is it?", "2024");
        deck.addCard(flashcard);
        myFlashcardDecks.addFlashcardDeck(deck);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to Flashcard App! Please select one of the following");
        System.out.println("\tc -> create new Flashcard Deck");
        System.out.println("\ts -> Select an existing Flashcard Deck");
        System.out.println("\tf -> Create new Flashcard");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for main menu
    private void processCommand(String command) {
        if (command.equals("c")) {
            createNewDeck();
        } else if (command.equals("s")) {
            selectAFlashcardDeck();
        } else if (command.equals("t")) {
            createANewFlashcard();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void createNewDeck() {
        boolean keepCreateNewDeckGoing = true;
        int goBack = 1;

        while (keepCreateNewDeckGoing) {
            System.out.println("Please enter the name of your new Flashcard Deck, press b to return to previous menu:");
            if (input.nextInt() == 0) {
                keepCreateNewDeckGoing = false;
            } else {
                String title = input.nextLine();
                FlashcardDeck newDeck = new FlashcardDeck(title);
                myFlashcardDecks.addFlashcardDeck(newDeck);
                myFlashcardDecks.setCurrentFlashcardDeck(newDeck);
                currentDeck = myFlashcardDecks.getCurrentFlashcardDeck();
                runCurrentDeck(currentDeck);
            }
        }
    }

    private void selectAFlashcardDeck() {
        boolean keepSelectFlashcardDeckGoing = true;
        int selection = -1;

        while (keepSelectFlashcardDeckGoing) {
            System.out.println("Please select the number of the Flashcard Deck, press 0 to return to main menu:");
            for (FlashcardDeck f : myFlashcardDecks.getFlashcardDecks()) {
                System.out.println(myFlashcardDecks.getPositionInList(f) + ": " + f.getName());
            }
            selection = input.nextInt();

            if (selection == 0) {
                keepSelectFlashcardDeckGoing = false;
            } else if (myFlashcardDecks.checkIfFlashcardDeckAtThisPosition(selection)) {
                myFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(selection);
                currentDeck = myFlashcardDecks.getCurrentFlashcardDeck();
                runCurrentDeck(currentDeck);
            } else {
                System.out.println("Selection not valid, please select another number");
            }
        }
    }

    private void runCurrentDeck(FlashcardDeck deck) {
        boolean keepRunCurrentDeckGoing = true;
        String deckCommand = null;
        //init();
        while (keepRunCurrentDeckGoing) {
            displayCurrentDeckMenu();
            deckCommand = input.next();
            deckCommand = deckCommand.toLowerCase();

            if (deckCommand.equals("q")) {
                keepRunCurrentDeckGoing = false;
            } else {
                processCurrentDeckCommand(deckCommand);
            }
        }
    }

    private void displayCurrentDeckMenu() {
        System.out.println("\nPlease select what you would like to do with Flashcard Deck: " + currentDeck.getName());
        System.out.println("\n" + currentDeck.getName() + " currently has " + currentDeck.getSizeTracker() + " cards");
        System.out.println("\td -> Display a list of all Flashcards in this Deck");
        System.out.println("\tc -> Create a new Flashcard to add to this deck");
        System.out.println("\tr -> Remove a Flashcard from this deck");
        System.out.println("\te -> Edit a Flashcard in this deck");
        System.out.println("\ts -> Study this deck");
        System.out.println("\tx -> Delete this Flashcard Deck");
        System.out.println("\tq -> Go back to previous menu");
    }

    private void processCurrentDeckCommand(String command) {
        if (command.equals("d")) {
            System.out.println("The current Flashcards in this deck are:");
            for (Flashcard f : currentDeck.getFlashcards()) {
                System.out.println("Question: " + f.getQuestion());
                System.out.println("Answer: " + f.getAnswer());
            }
        } else if (command.equals("c")) {
            createANewFlashcard();
        } else if (command.equals("r")) {
            selectAFlashcardToRemove(currentDeck);
        } else if (command.equals("e")) {
            editAFlashcard(currentDeck);
        } else if (command.equals("s")) {
            quiz(currentDeck);
        }  else if (command.equals("x")) {
            removeFlashcardDeck(currentDeck);
        }  else {
            System.out.println("Selection not valid...");
        }
    }

    private void quiz(FlashcardDeck deck) {
        String yesNo = null;
        for (int index = 0; index < deck.deckSize(); index++) {
            currentCard = deck.getCardFromIndex(index);
            System.out.println(currentCard.getQuestion());
            System.out.println("Press y to see answer");
            yesNo = input.next();
            yesNo = yesNo.toLowerCase();
            if (yesNo.equals("y")) {
                System.out.println(currentCard.getAnswer());
            }
            System.out.println("Did you get the correct answer? Press y for YES");
            if (yesNo.equals("y")) {
                currentCard.setIsCorrect(true);
            }
            System.out.println("The score for this study session is:" + deck.getPercentCorrect() + "%");
            currentDeck.resetCards();
        }
    }

    private void createANewFlashcard() {
        String question = null;
        String answer = null;
        String yesNo = null;
        Boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Please type the Flashcard question");
            question = input.nextLine();
            input.nextLine();
            System.out.println("Please type the Flashcard answer");
            answer = input.nextLine();
            System.out.println("The following Flashcard will be created:");
            System.out.println("Question: " + question);
            System.out.println("Answer: " + answer);
            System.out.println("Is that information correct? Type y for YES");
            yesNo = input.next();
            if (yesNo.equals("y")) {
                Flashcard f = new Flashcard(question, answer);
                currentDeck.addCard(f);
                System.out.println("The Flashcard has been added");
                keepGoing = false;
            }
        }
    }

    private void selectAFlashcardToRemove(FlashcardDeck currentDeck) {
        //int selection = -1;
        System.out.println("These are the current Flashcards in " + currentDeck.getName());
        for (Flashcard f : currentDeck.getFlashcards()) {
            System.out.println("Number " + currentDeck.getPositionOfCardInList(f) + ": " + f.getQuestion());
        }
        System.out.println("Please select a card number to remove");
        int selection = input.nextInt();
        if (currentDeck.checkIfFlashcardAtThisPosition(selection)) {
            currentCard = currentDeck.getCardFromIndex(selection + 1);
            currentDeck.removeCard(currentCard);
        }
    }

    private void editAFlashcard(FlashcardDeck currentDeck) {
        int selection = -1;
        System.out.println("These are the current Flashcards in " + currentDeck.getName());
        for (Flashcard f : currentDeck.getFlashcards()) {
            System.out.println("Number " + currentDeck.getPositionOfCardInList(f) + ": " + f.getQuestion());
        }
        System.out.println("Please select a card number to edit");
        selection = input.nextInt();
        if (currentDeck.checkIfFlashcardAtThisPosition(selection)) {
            currentCard = currentDeck.getCardFromIndex(selection + 1);
            System.out.println("The card selected to be edited is: " + currentCard.getQuestion());
            System.out.println("Press q to edit the question, Press a to edit the answer");
            String yesNo = input.nextLine();
            if (yesNo.equals("q")) {
                editQuestion(currentCard);
            } else if (yesNo.equals("a")) {
                editAnswer(currentCard);
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    private void editQuestion(Flashcard f) {
        System.out.println("Please enter the new question:");
        String question = input.nextLine();
        f.setQuestion(question);
        System.out.println(f.getQuestion() + " is now the new question for this card");
    }

    private void editAnswer(Flashcard f) {
        System.out.println("Please enter the new answer:");
        String answer = input.nextLine();
        f.setAnswer(answer);
        System.out.println(f.getAnswer() + " is now the new answer for this card");
    }

    private void removeFlashcardDeck(FlashcardDeck f) {
        myFlashcardDecks.removeFlashcardDeck(f);
        currentDeck = null;
    }



}
