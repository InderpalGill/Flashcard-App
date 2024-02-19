package model;

// Represents a flashcard with card number, question, and answer, and if the user has marked they
// got the card correct during study
public class Flashcard {


    private String question;
    private String answer;
    private Boolean isCorrect;


    //EFFECTS: Constructor for class. Creates flashcard with given question and answer, and isCorrect set to false
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.isCorrect = false;
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
    //EFFECTS: Sets the parameter value as the question for a card
    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    //MODIFIES: this
    //EFFECTS: Sets the parameter value as the answer for a card
    public void setAnswer(String newAnswer) {
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

}
