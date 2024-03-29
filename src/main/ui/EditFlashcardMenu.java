package ui;

import model.Flashcard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class that creates and represents a JPanel of menu options to edit currentCard
public class EditFlashcardMenu extends JPanel {

    private final FlashcardProgramUI controller;


    public EditFlashcardMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JLabel message = new JLabel("Please select one of the options below" + "\n");
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

    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new GridLayout(7,1,10,10));
        JLabel question = new JLabel("<html>" + "Current question of card: " + "<br/>"
                + controller.currentCard.getQuestion() + "<html>");
        question.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        mainButtons.add(question);
        mainButtons.add(makeEditQuestionButton());
        JLabel answer = new JLabel("<html>" + "Current answer of card: " + "<br/>"
                + controller.currentCard.getAnswer() + "<html>");
        answer.setFont(new Font("Comic Sans", Font.PLAIN, 14));
        mainButtons.add(answer);
        mainButtons.add(makeEditAnswerButton());
        mainButtons.add(makeRemoveButton());
        mainButtons.add(makeBackButton());
        this.add(mainButtons, BorderLayout.CENTER);

    }

    //EFFECTS: Creates a new JButton to change the question of a card. Assigns it the action editQuestion() on click
    private JButton makeEditQuestionButton() {
        JButton button = new JButton(ButtonNames.EDIT_QUESTION.getValue());
        button.setFocusable(false);
        button.addActionListener(e -> editQuestion());
        return button;
    }

    //EFFECTS: Creates a new JButton to change the answer of a card. Assigns it the action editAnswer() on click
    private JButton makeEditAnswerButton() {
        JButton button = new JButton(ButtonNames.EDIT_ANSWER.getValue());
        button.setFocusable(false);
        button.addActionListener(e -> editAnswer());
        return button;
    }

    //EFFECTS: Creates a new JButton to remove this card from currentDeck. Assigns it the action removeCard() on click
    private JButton makeRemoveButton() {
        JButton button = new JButton(ButtonNames.REMOVE.getValue());
        button.setFocusable(false);
        button.addActionListener(e -> removeCard());
        return button;
    }

    // EFFECTS: Constructs a button to return to previous menu
    private JButton makeBackButton() {
        JButton back = new JButton(ButtonNames.BACK.getValue());
        back.setFocusable(false);
        back.setBackground(new Color(247,183,230));
        back.addActionListener(e -> controller.returnToPreviousMenu());
        return back;
    }

    //MODIFIES: FlashcardProgramUI.currentCard
    //EFFECTS: lets user change the question of currentCard
    private void editQuestion() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the new question you wish to change this Flashcard question to.");
        JTextField question = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(question);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Provide new question for Flashcard",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                String previousName = controller.currentCard.getQuestion();
                controller.currentCard.setQuestion(question.getText());
                JOptionPane.showMessageDialog(this,"<html>" + previousName + "<br/>"
                        + " has now been changed to: " + "<br/>"  + controller.currentCard.getQuestion() + "<html>");
                controller.returnToPreviousMenu();
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
    }

    private void editAnswer() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the new question you wish to change this Flashcard question to.");
        JTextField answer = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(answer);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Provide new question for Flashcard",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                String previousAnswer = controller.currentCard.getAnswer();
                controller.currentCard.setAnswer(answer.getText());
                JOptionPane.showMessageDialog(this,"<html>" + previousAnswer + "<br/>"
                        + " has now been changed to: " + "<br/>"  + controller.currentCard.getAnswer() + "<html>");
                controller.returnToPreviousMenu();
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
    }

    //MODIFIES: FlashcardProgramUI.currentCard, FlashcardProgramUI.currentDeck
    //EFFECTS:  removes currentCard from currentDeck. Confirms choice with user.
    private void removeCard() {
        Flashcard selectedCard = controller.currentCard;
        JPanel mainPanel = new JPanel(new GridLayout(1, 1));
        JLabel label = new JLabel("Please confirm that you wish to remove this Flashcard from this Deck");
        mainPanel.add(label);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Remove Flashcard",
                JOptionPane.YES_NO_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                JOptionPane.showMessageDialog(controller, "<html>" + "Flashcard: " + selectedCard.getQuestion()
                        + "<br/>" + " has been deleted from" + controller.currentDeck.getName() + "<html>",
                        "Flashcard has been deleted", JOptionPane.PLAIN_MESSAGE);
                controller.currentDeck.removeCard(controller.currentCard);
                controller.returnToPreviousMenu();
            case JOptionPane.NO_OPTION:
                JOptionPane.getRootFrame().dispose();
        }

    }
}
