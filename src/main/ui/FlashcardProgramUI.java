package ui;

import model.FlashcardDeck;
import model.FlashcardDecks;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;


public class FlashcardProgramUI extends JFrame {

    private static final String JSON_STORE = "./data/flashcardDecks.json";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private JPanel menu;
    FlashcardDecks myFlashcardDecks;
    FlashcardDeck currentDeck;
    JsonReader jsonReader;
    JsonWriter jsonWriter;

    public FlashcardProgramUI() {
        super("Flashcard Program");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            init();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

        this.add(displayMainMenu());

        this.setVisible(true);


    }

    private void init() throws FileNotFoundException {
        myFlashcardDecks = new FlashcardDecks("My Flashcards");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
    }

    private Component displayMainMenu() {
        menu = new MainMenu(this);
        return menu;
    }

    protected void loadFlashcardDeck() {
        try {
            myFlashcardDecks = jsonReader.read();
            System.out.println("Loaded " + myFlashcardDecks.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    protected void saveFlashcardDeck() {
        try {
            jsonWriter.open();
            jsonWriter.write(myFlashcardDecks);
            jsonWriter.close();
            System.out.println("Saved " + myFlashcardDecks.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


}
