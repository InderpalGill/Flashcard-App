package ui;

import model.Flashcard;
import model.FlashcardDeck;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class that creates and represents a JPanel of menu of options to perform once a Flashcard Deck has been selected
// references the AlarmSystemProject for how to create JPanels, and JButtons
//https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class FlashcardDeckMenu extends JPanel {
    private final FlashcardProgramUI controller;
    private final FlashcardDeck currentDeck;

    //EFFECTS: Constructor for class, sets panel dimensions and places components on panel.
    public FlashcardDeckMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());
        this.currentDeck = controller.currentDeck;

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JLabel message = new JLabel("Please select what you would like to do with Flashcard Deck: "
                + controller.currentDeck.getName());
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

    //EFFECTS: Creates a GridLayout Panel and adds the menu option buttons to the panel. The panel is then added to the
    // JPanel made by the constructor
    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new GridLayout(7,1,10,10));
        mainButtons.add(makeDisplayButton());
        mainButtons.add(makeCreateCardButton());
        mainButtons.add(makeStudyButton());
        mainButtons.add(makeChangeNameButton());
        mainButtons.add(makeBackButton());
        this.add(mainButtons, BorderLayout.CENTER);
    }

    //EFFECTS: Creates a new JButton for the display all card option in the Flashcard Deck menu. Assigns it the action
    // of calling displayCards() when clicked
    private JButton makeDisplayButton() {
        JButton display = new JButton(ButtonNames.DISPLAY.getValue());
        display.setFocusable(false);
        display.addActionListener(e -> controller.editCards());
        return display;
    }

    //EFFECTS: Creates a new JButton for the option create a new card in the Flashcard Deck menu. Assigns it the action
    // of calling createNewDeck() when clicked
    private JButton makeCreateCardButton() {
        JButton create = new JButton(ButtonNames.CREATE_CARD.getValue());
        create.setFocusable(false);
        create.addActionListener(e -> this.createCard());
        return create;
    }

    //EFFECTS: Creates a new JButton for the option quiz a deck in the Flashcard Deck menu. Assigns it the action
    // of calling quizDeck() when clicked
    private JButton makeStudyButton() {
        JButton study = new JButton(ButtonNames.STUDY.getValue());
        study.setFocusable(false);
        study.addActionListener(e -> controller.quizDeck());
        return study;
    }

    //EFFECTS: Creates a new JButton for the option to change name of deck in the Flashcard Deck menu. Assigns it the
    // action of calling changeName() when clicked
    private JButton makeChangeNameButton() {
        JButton change = new JButton(ButtonNames.CHANGE_NAME.getValue());
        change.setFocusable(false);
        change.addActionListener(e -> this.changeName());
        return change;
    }

    //EFFECTS: Creates a new JButton for the option to go back to main menu. Assigns it the action
    // of calling returnToPreviousMenu() when clicked
    private JButton makeBackButton() {
        JButton back = new JButton(ButtonNames.BACK.getValue());
        back.setFocusable(false);
        back.setBackground(new Color(247,183,230));
        back.addActionListener(e -> controller.returnToPreviousMenu());
        return back;
    }

    //MODIFIES: FlashcardProgramUI.currentDeck
    //EFFECTS: Allows the user to change the name of the current Flashcard Deck
    private void changeName() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Enter the new name you wish to change your Flashcard Deck too.");
        JTextField title = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(title);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Provide the new name of Flashcard Deck",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                String previousName = currentDeck.getName();
                currentDeck.setName(title.getText());
                JOptionPane.showMessageDialog(this,"<html>" + previousName + "<br/>"
                        + " Has now been changed to: " + currentDeck.getName() + "<html>");
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
    }

    //MODIFIES: FlashcardProgramUI.currentDeck
    //EFFECTS: Allows user to create a Flashcard and adds it to currentDeck. Asks user to type in question of new
    // Flashcard they want to create, calls method to for user to enter the answer for the card.
    private void createCard() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Please enter the question of the new card you wish to create.");
        JTextField question = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(question);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Enter new card question",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                String quest = question.getText();
                String answer = createCardAnswer();
                if (confirmCreateCard(quest, answer)) {
                    Flashcard f = new Flashcard(quest, answer);
                    controller.currentDeck.addCard(f);
                } else {
                    JOptionPane.getRootFrame().dispose();
                }
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
    }

    //EFFECTS: Allows users to enter the answer for a new card currently being created
    private String createCardAnswer() {
        JPanel mainPanel = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Please enter the answer of the new card you wish to create.");
        JTextField answer = new JTextField(20);
        mainPanel.add(label);
        mainPanel.add(answer);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Enter new card answer",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                return answer.getText();
            case JOptionPane.CANCEL_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
        return null;
    }

    //EFFECTS: JOptionPane popup that asks users to confirm if question and answer text is correct for new card
    // they wish to create. Returns true or false depending on users input of button click.
    private boolean confirmCreateCard(String question, String answer) {
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        JLabel label = new JLabel("Please confirm that you wish to create the following Flashcard:");
        JLabel questionLabel = new JLabel("Question: " + question);
        JLabel answerLabel = new JLabel("Answer: " + answer);
        mainPanel.add(label);
        mainPanel.add(questionLabel);
        mainPanel.add(answerLabel);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Confirm Flashcard",
                JOptionPane.OK_CANCEL_OPTION);
        switch (result) {
            case JOptionPane.OK_OPTION:
                return true;
            case JOptionPane.CANCEL_OPTION:
                return false;
        }
        return false;
    }

}
