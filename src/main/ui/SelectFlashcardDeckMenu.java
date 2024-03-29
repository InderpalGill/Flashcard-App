package ui;

import model.FlashcardDeck;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class that creates and represents a JPanel of menu options when Select a Flashcard Deck is selected from main menu
public class SelectFlashcardDeckMenu extends JPanel {

    private final FlashcardProgramUI controller;

    //EFFECTS: Constructor for class, sets panel dimensions and places components on panel.
    public SelectFlashcardDeckMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JLabel message = new JLabel("Please select a Flashcard Deck from below");
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

    // EFFECTS: Creates and places buttons representing Flashcard Decks from FlashcardProgramUI.myFlashcardDecks
    // onto a BoxLayout Panel that is added to main JPanel of this class
    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new BoxLayout(mainButtons, BoxLayout.Y_AXIS));
        for (FlashcardDeck f : controller.myFlashcardDecks.getFlashcardDecks()) {
            JButton newButton = (JButton) makeButton(controller.myFlashcardDecks.getPositionInList(f), f.getName());
            mainButtons.add(Box.createRigidArea(new Dimension(0, 5)));
            mainButtons.add(newButton);
            newButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            mainButtons.add(Box.createRigidArea(new Dimension(0, 5)));
        }
        JButton backButton = makeBackButton();
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setMaximumSize(new Dimension((FlashcardProgramUI.WIDTH) / 2, 50));
        mainButtons.add(backButton);
        mainButtons.add(Box.createVerticalGlue());
        mainButtons.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(mainButtons, BorderLayout.CENTER);
    }

    //EFFECTS: Method to create a button that selects a Flashcard Deck. Button click will call assignDeck method
    private Component makeButton(int position, String name) {
        JButton button = new JButton(name);
        button.setMaximumSize(new Dimension((FlashcardProgramUI.WIDTH) / 2, 50));
        button.setFocusable(false);
        button.addActionListener(e -> assignDeck(position));
        return button;
    }

    //MODIFIES: FlashcardDeckUI.currentDeck
    //EFFECTS: sets currentDeck to Flashcard Deck selected by position. Calls runCurrentDeck on selected deck
    private void assignDeck(int position) {
        controller.setCurrentDeck(controller.myFlashcardDecks.getFlashcardDeckFromPosition(position));
        JOptionPane.showMessageDialog(controller, controller.currentDeck.getName()
                        + " Flashcard Deck selected","Deck has been selected Saved", JOptionPane.PLAIN_MESSAGE);
        controller.runCurrentDeck();
    }

    // EFFECTS: Constructs a button to return to previous menu
    private JButton makeBackButton() {
        JButton back = new JButton(ButtonNames.BACK.getValue());
        back.setFocusable(false);
        back.setBackground(new Color(247,183,230));
        back.addActionListener(e -> controller.returnToPreviousMenu());
        return back;
    }
}

