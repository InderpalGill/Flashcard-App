package ui;


import model.Flashcard;
import model.FlashcardDeck;
import model.FlashcardDecks;

import java.util.Scanner;

// Application for Flashcard Program references the TellerApp
public class FlashcardApp {
    private FlashcardDeck deck;  //for demonstration
    private Flashcard flashcard; //for demonstration
    private Scanner input;
    private FlashcardDecks myFlashcardDecks;
    private FlashcardDeck currentDeck;
    private Flashcard currentCard;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    public FlashcardApp() {
        runFlashcardApp();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    // MODIFIES: this
    // EFFECTS: initializes the program and creates a sample Flashcard deck for demonstration
    private void init() {
        myFlashcardDecks = new FlashcardDecks();
        deck = new FlashcardDeck("year");
        flashcard = new Flashcard("What year is it?", "2024");
        deck.addCard(flashcard);
        //myFlashcardDecks.addFlashcardDeck(deck);
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void quiz(FlashcardDeck deck) {
        for (int index = 0; index < deck.deckSize(); index++) {
            currentCard = deck.getCardFromIndex(index);
            deck.setCurrentCard(currentCard);
            askQuestion(currentCard);
        }
        System.out.println("The score for this study session is:" + deck.getPercentCorrect() + "%");
        currentDeck.resetCards();
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void selectAFlashcardToRemove(FlashcardDeck currentDeck) {
        if (currentDeck.getSizeTracker() == 0) {
            System.out.println("There are no cards in this deck");
        } else {
            System.out.println("These are the current Flashcards in " + currentDeck.getName());
            for (Flashcard f : currentDeck.getFlashcards()) {
                System.out.println("Number " + currentDeck.getPositionOfCardInList(f) + ": " + f.getQuestion());
            }
            System.out.println("Please select a card number to remove");
            int selection = input.nextInt();
            if (currentDeck.checkIfFlashcardAtThisPosition(selection)) {
                currentCard = currentDeck.getCardFromIndex(selection - 1);
                currentDeck.removeCard(currentCard);
                System.out.println(currentCard + "has been removed from deck " + currentDeck.getName());
            } else {
                System.out.println(selection + " is not a valid choice, please select again");
            }
        }
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void editQuestion(Flashcard f) {
        System.out.println("Please enter the new question:");
        String question = input.nextLine();
        f.setQuestion(question);
        System.out.println(f.getQuestion() + " is now the new question for this card");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void editAnswer(Flashcard f) {
        System.out.println("Please enter the new answer:");
        String answer = input.nextLine();
        f.setAnswer(answer);
        System.out.println(f.getAnswer() + " is now the new answer for this card");
    }

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
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

    //REQUIRES:
    //MODIFIES:
    //EFFECTS:
    private void changeName(FlashcardDeck f) {
        System.out.println("Please type in the new name for this deck");
        input.nextLine();
        String name = input.nextLine();
        f.setName(name);
        System.out.println(name + " has been set as the new name for this deck");
    }

}