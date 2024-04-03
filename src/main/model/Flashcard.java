package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a flashcard with card number, question, and answer, and if the user has marked they
// got the card correct during study
//references the JsonSerializationDemo project for how to create JsonReader, JsonWriter, and to how to develop
//testing classes and tests for Json
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class Flashcard implements Writable {


    private String question;
    private String answer;
    private Boolean isCorrect;


    //EFFECTS: Constructor for class. Creates flashcard with given question and answer, and isCorrect set to false
    // records Event on EventLog
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.isCorrect = false;
        EventLog.getInstance().logEvent(new Event("Created Flashcard, Question: "
                + question + "\n" + "Answer: " + answer + "\n"));
    }

    //EFFECTS: returns question for card
    public String getQuestion() {
        return question;
    }

    //EFFECTS: returns answer for card
    public String getAnswer() {
        return answer;
    }

    //MODIFIES: this
    //EFFECTS: Sets the parameter value as the question for a card, records Event on EventLog
    public void setQuestion(String newQuestion) {
        EventLog.getInstance().logEvent(new Event("Flashcard Question: "
                + question + "\n" + "has been changed to: " + newQuestion + "\n"));
        this.question = newQuestion;
    }

    //MODIFIES: this
    //EFFECTS: Sets the parameter value as the answer for a card, records Event on EventLog
    public void setAnswer(String newAnswer) {
        EventLog.getInstance().logEvent(new Event("Flashcard Answer: "
                + answer + "\n" + "has been changed to: " + newAnswer + "\n"));
        this.answer = newAnswer;
    }

    //EFFECTS: returns getIsCorrect field for a card
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    //MODIFIES: this
    //EFFECTS: Sets the isCorrect field for a card to the parameter value
    public void setIsCorrect(Boolean b) {
        this.isCorrect = b;
    }

    @Override
    //MODIFIES: this
    //EFFECTS: Coverts Flashcard object to JSONObject, with keys being "Question" and "Answer", and values being
    //this.question, and this,answer
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Question:", this.question);
        json.put("Answer:", this.answer);
        return json;
    }

}
