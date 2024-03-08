package ui;


import model.Flashcard;
import model.FlashcardDeck;
import model.FlashcardDecks;

import java.util.Scanner;

// Application for Flashcard Program
// References the TellerApp for how to create menu displays and set up and use Scanner
//https://github.students.cs.ubc.ca/CPSC210/TellerApp.git
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class FlashcardApp {

    private Scanner input;
    private FlashcardDecks myFlashcardDecks;
    private FlashcardDeck currentDeck;
    private Flashcard currentCard;


    //EFFECTS: Constructor for class. Creates a new instance of FlashcardApp which starts with runFlashcardApp()
    public FlashcardApp() {
        runFlashcardApp();
    }

    //MODIFIES: this
    //EFFECTS: Launches app, and processes user input displayed on main menu
    private void runFlashcardApp() {
        boolean keepGoing = true;

        init();

        while (keepGoing) {
            displayMenu();
            String command = input.next();
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
    //initializes the scanner
    private void init() {
        myFlashcardDecks = new FlashcardDecks("My Flashcards");
        FlashcardDeck deck = new FlashcardDeck("year"); //for demonstration
        Flashcard flashcard = new Flashcard("What year is it?", "2024"); //for demonstration
        deck.addCard(flashcard); // for demonstration
        myFlashcardDecks.addFlashcardDeck(deck); //for demonstration
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays main menu of options to user
    private void displayMenu() {
        System.out.println("\nWelcome to Flashcard App! Please select one of the following");
        System.out.println("\tc -> Create new Flashcard Deck");
        System.out.println("\ts -> Select an existing Flashcard Deck");
        System.out.println("\td -> Delete a Flashcard Deck");
        System.out.println("\tq -> Quit the application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command for main menu
    private void processCommand(String command) {
        if (command.equals("c")) {
            createNewDeck();
        } else if (command.equals("s")) {
            selectAFlashcardDeck();
        } else if (command.equals("d")) {
            deleteFlashcardDeck();
        } else {
            System.out.println("Sorry, the key you have entered is not valid");
        }
    }

    //MODIFIES: this
    //EFFECTS: Creates a new FlashcardDeck and asks for a title.
    private void createNewDeck() {
        System.out.println("Please enter the name of your new Flashcard Deck, press b to return to previous menu:");
        input.nextLine();
        String title = input.nextLine();
        if (title.equals("b")) {
            System.out.println("Returning to previous menu");
        } else {
            FlashcardDeck newDeck = new FlashcardDeck(title);
            myFlashcardDecks.addFlashcardDeck(newDeck);
            myFlashcardDecks.setCurrentFlashcardDeck(newDeck);
            currentDeck = myFlashcardDecks.getCurrentFlashcardDeck();
            runCurrentDeck();
        }
    }

    //REQUIRES: myFlashcardDecks size > 0
    //MODIFIES: this
    //EFFECTS: Allows user to look through all Flashcard Decks, and select one. Displays a list of all FlashcardDecks,
    // uses user input to determine which FlashcardDeck to select.
    private void selectAFlashcardDeck() {
        if (myFlashcardDecks.getSizeFlashcardDecks() == 0) {
            System.out.println("There are no Flashcard Decks to select from");
        } else {
            boolean keepSelectFlashcardDeckGoing = true;
            while (keepSelectFlashcardDeckGoing) {
                System.out.println("Please select the number of the Flashcard Deck, press 0 to return to main menu:");
                for (FlashcardDeck f : myFlashcardDecks.getFlashcardDecks()) {
                    System.out.println(myFlashcardDecks.getPositionInList(f) + ": " + f.getName());
                }
                int selection = input.nextInt();

                if (selection == 0) {
                    keepSelectFlashcardDeckGoing = false;
                } else if (myFlashcardDecks.checkIfFlashcardDeckAtThisPosition(selection)) {
                    myFlashcardDecks.setCurrentFlashCardDeckBasedOnPositionInList(selection);
                    currentDeck = myFlashcardDecks.getCurrentFlashcardDeck();
                    runCurrentDeck();
                } else {
                    System.out.println("Selection not valid, please select another number");
                }
            }
        }
    }

    //REQUIRES: currentFlashcardDeck is not null
    //MODIFIES: this
    //EFFECTS: Launches options for currentDeck. Takes user input to determine which option user would like to proceed
    //with
    private void runCurrentDeck() {
        boolean keepRunCurrentDeckGoing = true;

        while (keepRunCurrentDeckGoing) {
            displayCurrentDeckMenu();
            String deckCommand = input.next();
            deckCommand = deckCommand.toLowerCase();

            if (deckCommand.equals("q")) {
                keepRunCurrentDeckGoing = false;
            } else {
                processCurrentDeckCommand(deckCommand);
            }
        }
    }

    //EFFECTS: Displays menu of options for user to select their next input.
    private void displayCurrentDeckMenu() {
        System.out.println("\nPlease select what you would like to do with Flashcard Deck: " + currentDeck.getName());
        System.out.println("\n" + currentDeck.getName() + ": currently has " + currentDeck.getSizeTracker() + " cards");
        System.out.println("\td -> Display a list of all Flashcards in this Deck");
        System.out.println("\tc -> Create a new Flashcard to add to this deck");
        System.out.println("\tr -> Remove a Flashcard from this deck");
        System.out.println("\te -> Edit a Flashcard in this deck");
        System.out.println("\ts -> Study this deck");
        System.out.println("\tn -> Change the name of this deck");
        System.out.println("\tq -> Go back to previous menu");
    }

    //REQUIRES: currentDeck has a FlashcardDeck assigned
    //MODIFIES: this
    //EFFECTS: Processes user input String to select which methods to call for currentDeck
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
        } else if (command.equals("n")) {
            changeName(currentDeck);
        } else {
            System.out.println("Sorry, the key you have entered is not valid");
        }
    }

    //REQUIRES: FlashcardDeck size >0
    //MODIFIES: this
    //EFFECTS: starts a quiz for selected flashcard deck. Goes through each card in a FlashcardDeck one at a time.
    // Prints the correctness score to console. Resets all cards at end
    private void quiz(FlashcardDeck deck) {
        if (deck.deckSize() == 0) {
            System.out.println("This flashcard deck is empty, cannot run a quiz");
        } else {
            for (int index = 0; index < deck.deckSize(); index++) {
                currentCard = deck.getCardFromIndex(index);
                deck.setCurrentCard(currentCard);
                askQuestion(currentCard);
            }
            System.out.println("The score for this study session is:" + deck.getPercentCorrect() + "%");
            currentDeck.resetCards();
        }
    }

    //REQUIRES:
    //MODIFIES: this
    //EFFECTS: uses user input during study session to display question of given flashcard, and display answer. User can
    // select if they got particular card correct or incorrect using input.
    private void askQuestion(Flashcard f) {
        System.out.println(f.getQuestion());
        System.out.println("Press y to see answer, press any other key to skip answer");
        String yesNo = input.next();
        yesNo = yesNo.toLowerCase();
        if (yesNo.equals("y")) {
            System.out.println(currentCard.getAnswer());
        } else {
            System.out.println("Answer skipped");
        }
        System.out.println("Did you get the correct answer? Press y for YES, press any other key for NO");
        String markCorrect = input.next();
        markCorrect = markCorrect.toLowerCase();
        if (markCorrect.equals("y")) {
            currentDeck.markCardCorrect();
        }
    }

    //MODIFIES:
    //EFFECTS: Creates new Flashcard using user input for question and answer
    private void createANewFlashcard() {
        Boolean keepGoing = true;
        while (keepGoing) {
            System.out.println("Please type the Flashcard question");
            input.nextLine();
            String question = input.nextLine();
            System.out.println("Please type the Flashcard answer");
            String answer = input.nextLine();
            System.out.println("The following Flashcard will be created:");
            System.out.println("Question: " + question);
            System.out.println("Answer: " + answer);
            System.out.println("Is that information correct? Type y for YES");
            String yesNo = input.next();
            yesNo = yesNo.toLowerCase();
            if (yesNo.equals("y")) {
                Flashcard f = new Flashcard(question, answer);
                currentDeck.addCard(f);
                System.out.println("The Flashcard has been added");
                keepGoing = false;
            }
        }
    }

    //REQUIRES: currentDeck size > 0
    //MODIFIES: this
    //EFFECTS: uses user input to select and remove a Flashcard from myFlashcardDecks
    private void selectAFlashcardToRemove(FlashcardDeck currentDeck) {
        if (currentDeck.getSizeTracker() == 0) {
            System.out.println("There are no cards in this deck");
        } else {
            System.out.println("These are the current Flashcards in " + currentDeck.getName());
            for (Flashcard f : currentDeck.getFlashcards()) {
                System.out.println("Number " + currentDeck.getPositionOfCardInList(f) + ": " + f.getQuestion());
            }
            System.out.println("Please select a card number to remove, press 0 to return to main menu");
            int selection = input.nextInt();
            if (currentDeck.checkIfFlashcardAtThisPosition(selection)) {
                currentCard = currentDeck.getCardFromIndex(selection - 1);
                currentDeck.removeCard(currentCard);
                System.out.println(currentCard.getQuestion() + " has been removed from deck " + currentDeck.getName());
            } else {
                System.out.println(selection + " is not a valid choice, please select again");
            }
        }
    }

    //REQUIRES: currentDeck size >0
    //MODIFIES: this
    //EFFECTS: user input to change the question or answer of a flashcard
    private void editAFlashcard(FlashcardDeck currentDeck) {
        if (currentDeck.getSizeTracker() == 0) {
            System.out.println("There are no Flashcards in this deck to edit");
        } else {
            System.out.println("These are the current Flashcards in " + currentDeck.getName());
            for (Flashcard f : currentDeck.getFlashcards()) {
                System.out.println("Number " + currentDeck.getPositionOfCardInList(f) + ": " + f.getQuestion());
            }
            System.out.println("Please select a card number to edit");
            int selection = input.nextInt();
            if (currentDeck.checkIfFlashcardAtThisPosition(selection)) {
                currentCard = currentDeck.getCardFromIndex(selection - 1);
                selectQuestionOrAnswer(currentCard);
            } else {
                System.out.println("Please enter a valid input");
            }
        }
    }

    //REQUIRES: currentDeck size > 0
    //MODIFIES: this
    //EFFECTS: Processes user command to edit either question of a flashcard, or the answer
    private void selectQuestionOrAnswer(Flashcard f) {
        System.out.println("The card selected to be edited is: " + currentCard.getQuestion());
        System.out.println("Press q to edit the question, Press a to edit the answer");
        input.nextLine();
        String yesNo = input.nextLine();
        yesNo = yesNo.toLowerCase();
        if (yesNo.equals("q")) {
            editQuestion(f);
        } else if (yesNo.equals("a")) {
            editAnswer(f);
        }
    }

    //REQUIRES: currentCard to be selected
    //MODIFIES: this
    //EFFECTS: Prompts user to type a string which replaces the question for given flashcard
    private void editQuestion(Flashcard f) {
        System.out.println("Please enter the new question:");
        String question = input.nextLine();
        f.setQuestion(question);
        System.out.println(f.getQuestion() + " is now the new question for this card");
    }

    //REQUIRES: currentCard to be selected
    //MODIFIES: this
    //EFFECTS: Prompts user to type a string which replaces the answer for given flashcard
    private void editAnswer(Flashcard f) {
        System.out.println("Please enter the new answer:");
        String answer = input.nextLine();
        f.setAnswer(answer);
        System.out.println(f.getAnswer() + " is now the new answer for this card");
    }

    //REQUIRES: myFlashcardDecks size >0
    //MODIFIES: this
    //EFFECTS: user input to select and delete a FlashcardDeck
    private void deleteFlashcardDeck() {
        if (myFlashcardDecks.getSizeFlashcardDecks() == 0) {
            System.out.println("There are currently no Flashcard Decks");
        } else {
            System.out.println("Please select the number of the Flashcard Deck to delete,"
                    + " press 0 to return to main menu:");
            for (FlashcardDeck f : myFlashcardDecks.getFlashcardDecks()) {
                System.out.println(myFlashcardDecks.getPositionInList(f) + ": " + f.getName());
            }
            int selection = input.nextInt();
            if (selection == 0) {
                System.out.println("Returning to main menu");
            } else if (myFlashcardDecks.checkIfFlashcardDeckAtThisPosition(selection)) {
                System.out.println("Deleting " + myFlashcardDecks.getFlashcardDeckFromPosition(selection).getName());
                myFlashcardDecks.removeFlashcardDeck(myFlashcardDecks.getFlashcardDeckFromPosition(selection));
            } else {
                System.out.println("That is not a valid choice. Returning to main menu");
            }
        }
    }


    //MODIFIES: this
    //EFFECTS: Prompts users to type in string to replace the name of a FlashcardDeck
    private void changeName(FlashcardDeck f) {
        System.out.println("Please type in the new name for this deck");
        input.nextLine();
        String name = input.nextLine();
        f.setName(name);
        System.out.println(name + " has been set as the new name for this deck");
    }

}