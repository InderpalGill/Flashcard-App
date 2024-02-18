package model;

// Represents a flashcard with card number, question, and answer, and if the user has marked they
// got the card correct during study
public class Flashcard {


    private String question;
    private String answer;
    private int cardNumber;
    private Boolean isCorrect;

    //REQUIRES:
    //MODIFIES:
    //EFFECTS: Constructor for class
    public Flashcard(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.isCorrect = false;
    }

    //EFFECTS: gets question for card
    public String getQuestion() {
        return question;
    }

    //EFFECTS: gets answer for card
    public String getAnswer() {
        return answer;
    }


    //EFFECTS: Sets question for card
    public void setQuestion(String newQuestion) {
        this.question = newQuestion;
    }

    //EFFECTS: sets answer for card
    public void setAnswer(String newAnswer) {
        this.answer = newAnswer;
    }


    //MODIFIES:
    //EFFECTS: gets if card is returned correct
    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean b) {
        this.isCorrect = b;
    }


}
