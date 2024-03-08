package persistence;

import model.FlashcardDecks;
import model.FlashcardDeck;
import model.Flashcard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;


// Represents a reader that reads FlashcardDecks from JSON data stored in a file
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads FlashcardDecks from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FlashcardDecks read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFlashcardDecks(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses FlashcardDecks from JSON object and returns it
    private FlashcardDecks parseFlashcardDecks(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        FlashcardDecks fd = new FlashcardDecks(name);
        addFlashcardDecks(fd, jsonObject);
        return fd;
    }

    // MODIFIES: fd
    // EFFECTS: Goes through array of JSON object (FlashcardDeck)
    // parses each element in array and adds them to FlashcardDecks
    private void addFlashcardDecks(FlashcardDecks fd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("FlashcardDeck:");
        for (Object json : jsonArray) {
            JSONObject nextFlashcardDeck = (JSONObject) json;
            addFlashcardDeck(fd, nextFlashcardDeck);
        }
    }

    //MODIFIES: fd
    //EFFECTS: Creates a new FlashcardDeck that is added to FlashcardDecks,
    // parses JSON object for Flashcards to add to FlashcardDeck
    private void addFlashcardDeck(FlashcardDecks fd, JSONObject jsonObject) {
        String name = jsonObject.getString("Name:");
        FlashcardDeck flashcardDeck = new FlashcardDeck(name);
        addFlashcards(flashcardDeck, jsonObject);
        fd.addFlashcardDeck(flashcardDeck);
    }

    // MODIFIES: fd
    // EFFECTS: Looks in a FlashcardDeck (JSON object), goes through list and parses Flashcards from JSON
    // object and adds it to a single FlashcardDeck
    private void addFlashcards(FlashcardDeck fd, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Flashcards:");
        for (Object json : jsonArray) {
            JSONObject nextFlashcard = (JSONObject) json;
            addFlashcard(fd, nextFlashcard);
        }
    }

    // MODIFIES: fd
    // EFFECTS: parses single Flashcard from JSON object and adds it to FlashcardDeck
    private void addFlashcard(FlashcardDeck fd, JSONObject jsonObject) {
        String question = jsonObject.getString("Question:");
        String answer = jsonObject.getString("Answer:");
        Flashcard flashcard = new Flashcard(question, answer);
        fd.addCard(flashcard);
    }
}
