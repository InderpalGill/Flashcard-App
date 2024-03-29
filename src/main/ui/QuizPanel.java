package ui;

import model.FlashcardDeck;
import model.Flashcard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

//Class that represents a JPanel with options to quiz currentDeck. Contains the quiz method
public class QuizPanel extends JPanel {

    private final FlashcardProgramUI controller;
    int incorrect;
    private final String[] questionPane = {"Show Answer"};
    private final String[] answerPane = {"I got answer correct", "I got the answer incorrect"};
    private final FlashcardDeck currentDeck;

    //EFFECTS: Constructor for class. Sets dimensions and places Components on main JPanel
    public QuizPanel(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());
        incorrect = 0;
        currentDeck = controller.currentDeck;

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JLabel message = new JLabel("Please select a Quiz option below" + "\n");
        message.setFont(new Font("Comic Sans", Font.BOLD, 18));
        message.setBorder(new EmptyBorder(10, 0, 10, 0));
        this.add(message, BorderLayout.NORTH);
        message.setHorizontalAlignment(JLabel.CENTER);
        JPanel leftSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        JPanel rightSpace = new JPanel();
        rightSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        this.add(leftSpace, BorderLayout.EAST);
        this.add(rightSpace, BorderLayout.WEST);
    }

    //EFFECTS: adds buttons to center panel
    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new GridLayout(7, 1, 10, 10));
        mainButtons.add(makeQuizButton());

        mainButtons.add(makeBackButton());
        this.add(mainButtons, BorderLayout.CENTER);
    }

    //EFFECTS: Creates a new JButton to start a quiz of all Flashcards in currentDeck. Assigns the button the action
    // of calling quiz() when clicked
    private JButton makeQuizButton() {
        JButton button = new JButton(ButtonNames.QUIZ.getValue());
        button.setFocusable(false);
        button.addActionListener(e -> checkNumberCards());
        return button;
    }

    // EFFECTS: Constructs a button to return to previous menu
    private JButton makeBackButton() {
        JButton back = new JButton(ButtonNames.BACK.getValue());
        back.setFocusable(false);
        back.setBackground(new Color(247, 183, 230));
        back.addActionListener(e -> controller.returnToPreviousMenu());
        return back;
    }

    //EFFECTS: Checks if there are enough Flashcards to start a quiz

    private void checkNumberCards() {
        if (currentDeck.getSizeTracker() == 0) {
            JOptionPane.showMessageDialog(controller, "There are no cards in current deck to start a quiz from",
                    "Current Deck has no Flashcards", JOptionPane.ERROR_MESSAGE);
        } else {
            quiz();
        }
    }

    //MODIFIES: FlashcardProgramUI.currentDeck.percentCorrect
    //EFFECTS: uses JOptionPane to perform a quiz of all Flashcards in currentDeck. Displays results of quiz at the end
    private void quiz() {
        for (Flashcard f : controller.currentDeck.getFlashcards()) {
            controller.currentDeck.setCurrentCard(f);
            int selection = JOptionPane.showOptionDialog(controller, f.getQuestion(), "Question",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, questionPane, 0);
            if (selection == 0) {
                int result = JOptionPane.showOptionDialog(controller, f.getAnswer(), "Answer",
                        JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, answerPane, 1);
                switch (result) {
                    case 0:
                        markCorrect();
                    case 1:
                        markIncorrect();
                }
            }
        }
        JOptionPane.showMessageDialog(controller, "You got " + displayStats() + "% on this quiz",
                "Results of Quiz", JOptionPane.PLAIN_MESSAGE);
        controller.currentDeck.resetCards();
        incorrect = 0;
    }

    //MODIFIES: FlashcardProgramUI.currentDeck.percentCorrect
    //EFFECTS: increases correctTracker in FlashcardDeck by one
    private void markCorrect() {
        controller.currentDeck.markCardCorrect();
    }

    //MODIFIES: this
    //EFFECTS: increases incorrect field by one.
    private void markIncorrect() {
        incorrect += 1;
    }

    //EFFECTS: returns percentCorrect field from FlashcardDeck
    private double displayStats() {
        return currentDeck.getPercentCorrect();
    }
}
