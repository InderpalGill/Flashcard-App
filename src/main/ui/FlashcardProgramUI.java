package ui;

import model.Flashcard;
import model.FlashcardDeck;
import model.FlashcardDecks;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

//Main UI class for displaying Flashcard App.
public class FlashcardProgramUI extends JFrame {

    private static final String JSON_STORE = "./data/flashcardDecks.json";

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JPanel menu;
    private JPanel mainMenu;
    private int windowTracker;
    FlashcardDecks myFlashcardDecks;
    FlashcardDeck currentDeck;
    Flashcard currentCard;
    JsonReader jsonReader;
    JsonWriter jsonWriter;
    ImageIcon imageIcon;

    //EFFECTS: Constructor for class. Initializes all fields, loads from JSON_STORE, displays SplashScreen, sets
    //JFrame height and width, displays MainMenu
    public FlashcardProgramUI() {
        super("Flashcard Program");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            init();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        imageIcon = new ImageIcon("./data/FlashcardSplashPage.png");
        new SplashScreen(imageIcon);
        this.add(displayMainMenu());
        this.setMainMenu();
        this.windowTracker = 0;
        this.setVisible(true);
    }


    //MODIFIES: this
    //EFFECTS: instantiates fields of myFlashcardDecks, and sets jsonWriter and jsonReader to JSON_STORE
    private void init() throws FileNotFoundException {
        myFlashcardDecks = new FlashcardDecks("My Flashcards");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    //EFFECTS: returns MainMenu JPanel as a Component
    private Component displayMainMenu() {
        menu = new MainMenu(this);
        return menu;
    }

    private void setMainMenu() {
        mainMenu = menu;
    }

    protected void returnToPreviousMenu() {
        if (windowTracker < 4) {
            returnToMainMenu();
        } else if (windowTracker == 5) {
            editCards();
        } else {
            runCurrentDeck();
        }
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets mainMenu panel to be currently displayed panel
    protected void returnToMainMenu() {
        this.setVisible(false);
        this.remove(menu);
        windowTracker = 0;
        menu = mainMenu;
        this.add(menu);
        this.setVisible(true);
    }

    //MODIFIES this.myFlashcardDecks
    //EFFECTS: Sets myFlashcardDecks to file stored in JSON_STORE
    protected void loadFlashcardDeck() {
        try {
            myFlashcardDecks = jsonReader.read();
            JOptionPane.showMessageDialog(this, "Loaded " + myFlashcardDecks.getName()
                            + " from " + JSON_STORE, "Successfully Loaded", JOptionPane.PLAIN_MESSAGE);
            System.out.println("Loaded " + myFlashcardDecks.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Unsuccessfully Loaded", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //MODIFIES: JSON_STORE
    //EFFECTS: Saves myFlashCardDecks as files stored in JSON_STORE
    protected void saveFlashcardDeck() {
        try {
            jsonWriter.open();
            jsonWriter.write(myFlashcardDecks);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Saved " + myFlashcardDecks.getName()
                            + " to " + JSON_STORE, "Successfully Saved", JOptionPane.PLAIN_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE,
                    "Unsuccessfully Saved", JOptionPane.PLAIN_MESSAGE);
        }
    }

    //MODIFIES: this.myFlashcardDecks, this.currentDeck
    //EFFECTS: Creates a JOptionPane that lets user create a new FlashcardDeck with input as name of FlashcardDeck.
    //Adds newly created FlashcardDeck to myFlashcardDecks. Sets newly created deck to currentDeck. Goes to
    //FlashcardDeckMenu after creating new FlashcardDeck
    protected void createNewDeck() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the title of your new Flashcard Deck");
        JTextField title = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(title);
        int result = JOptionPane.showConfirmDialog(this, mainPanel, "Create a new Flashcard Deck",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                FlashcardDeck newDeck = new FlashcardDeck(title.getText());
                this.myFlashcardDecks.addFlashcardDeck(newDeck);
                this.myFlashcardDecks.setCurrentFlashcardDeck(newDeck);
                this.currentDeck = this.myFlashcardDecks.getCurrentFlashcardDeck();
                this.runCurrentDeck();
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
    }

    //EFFECTS: Displays a JOptionPane window indicating if there are no FlashcardDecks in myFlashcardDecks
    protected void selectADeck() {
        if (myFlashcardDecks.getSizeFlashcardDecks() == 0) {
            JOptionPane.showMessageDialog(this, "There are no Flashcard Decks to select from."
                    + " Please load a saved Flashcard Deck, or create a new Flashcard Deck",
                    "No current Flashcard Deck", JOptionPane.PLAIN_MESSAGE);
        } else {
            selectDeck();
        }
    }

    //EFFECTS: returns FlashcardDeckMenu JPanel as a Component
    private Component displaySelectFlashcardDeckMenu() {
        menu = new SelectFlashcardDeckMenu(this);
        return menu;
    }

    //MODIFIES: this.currentDeck
    //EFFECTS: sets currentDeck field to input FlashcardDeck
    protected void setCurrentDeck(FlashcardDeck f) {
        currentDeck = f;
    }

    //MODIFIES: this.currentCard
    //EFFECTS: sets currentDeck field to input Flashcard
    protected void setCurrentCard(Flashcard f) {
        currentCard = f;
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets SelectFlashcardDeckMenu panel to be currently
    // displayed panel
    protected void selectDeck() {
        this.setVisible(false);
        this.windowTracker = 1;
        this.remove(menu);
        this.add(displaySelectFlashcardDeckMenu());
        this.setVisible(true);
    }

    //EFFECTS: Returns FlashcardDeckMenu JPanel as a Component
    private Component displayFlashcardDeckMenu() {
        menu = new FlashcardDeckMenu(this);
        return menu;
    }

    //MODIFIES: this.menu
    //EFFECTS: Removes current menu from JFrame and sets FlashcardDeckMenu Panel as currently displayed panel
    protected void runCurrentDeck() {
        this.setVisible(false);
        this.windowTracker = 2;
        this.remove(menu);
        this.add(displayFlashcardDeckMenu());
        this.setVisible(true);
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets DeleteFlashcardDeckMenu panel to be currently
    // displayed panel
    protected void deleteADeck() {
        this.setVisible(false);
        this.windowTracker = 3;
        this.remove(menu);
        this.add(displayDeleteFlashcardDeckMenu());
        this.setVisible(true);
    }

    //EFFECTS: Returns DeleteFlashcardDeckMenu JPanel as a Component
    private Component displayDeleteFlashcardDeckMenu() {
        menu = new DeleteFlashcardDeckMenu(this);
        return menu;
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets EditFlashcardsMenu panel to be currently
    // displayed panel
    protected void editCards() {
        this.setVisible(false);
        this.windowTracker = 4;
        this.remove(menu);
        this.add(displayEditFlashcardsMenu());
        this.setVisible(true);
    }

    //EFFECTS: returns EditFlashcardsMenu panel as component
    private Component displayEditFlashcardsMenu() {
        menu = new EditFlashcardsMenu(this);
        return menu;
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets EditFlashcardMenu panel to be currently
    // displayed panel
    protected void editFlashcard() {
        this.setVisible(false);
        this.windowTracker = 5;
        this.remove(menu);
        this.add(displayEditFlashcardMenu());
        this.setVisible(true);
    }

    //EFFECTS: returns EditFlashcardMenu panel as component
    private Component displayEditFlashcardMenu() {
        menu = new EditFlashcardMenu(this);
        return menu;
    }

    //MODIFIES: this.menu
    //EFFECTS: removes currently displayed JPanel from frame. Sets QuizPanel panel to be currently displayed panel
    protected void quizDeck() {
        this.setVisible(false);
        this.windowTracker = 6;
        this.remove(menu);
        this.add(displayQuizPanel());
        this.setVisible(true);
    }

    //EFFECTS: returns QuizPanel panel as component
    private Component displayQuizPanel() {
        menu = new QuizPanel(this);
        return menu;
    }

    //EFFECTS: Displays message prompting user to save before exiting, if user selects YES, data will be saved before
    //exiting, if user selects NO, the program will close, if user selects CANCEL, return to main menu.
    protected void quit() {
        int a = JOptionPane.showConfirmDialog(this, "Any unsaved data will be deleted, "
                + "would you like to save?");
        if (a == JOptionPane.YES_OPTION) {
            this.saveFlashcardDeck();
            System.exit(0);
        } else if (a == JOptionPane.CANCEL_OPTION) {
            this.returnToMainMenu();
        } else {
            System.exit(0);
        }
    }
}
