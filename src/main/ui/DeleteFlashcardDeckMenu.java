package ui;

import model.FlashcardDeck;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// JPanel class that represents a menu of options to choose from when the Delete button in main menu is pressed
// Also contains method to delete a Flashcard Deck
// references the AlarmSystemProject for how to create JPanels, and JButtons
//https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class DeleteFlashcardDeckMenu extends JPanel {

    private final FlashcardProgramUI controller;

    //EFFECTS: Constructor for class. Sets dimensions and places Components on main JPanel
    public DeleteFlashcardDeckMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JLabel message = new JLabel("Please select a Flashcard Deck from below to delete" + "\n");
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

    //EFFECTS: Method to create a button that selects a Flashcard Deck to delete. Button click will call
    // deleteDeck method
    private Component makeButton(int position, String name) {
        JButton button = new JButton(name);
        button.setMaximumSize(new Dimension((FlashcardProgramUI.WIDTH) / 2, 50));
        button.setFocusable(false);
        button.addActionListener(e -> deleteDeck(position));
        return button;
    }

    //MODIFIES: FlashcardProgramUI.myFlashcardDecks
    //EFFECTS: Confirms user's decision to delete selected Flashcard Deck
    // Deletes input Flashcard Deck from FlashcardProgramUI.myFlashcardDecks
    private void deleteDeck(int position) {
        FlashcardDeck selectedDeck = controller.myFlashcardDecks.getFlashcardDeckFromPosition(position);
        JPanel mainPanel = new JPanel(new GridLayout(1, 1));
        JLabel label = new JLabel("Please confirm that you wish to delete the following Flashcard Deck: \n"
                + selectedDeck.getName());
        mainPanel.add(label);
        int result = JOptionPane.showConfirmDialog(controller, mainPanel, "Delete Flashcard Deck",
                JOptionPane.YES_NO_OPTION);
        switch (result) {
            case JOptionPane.YES_OPTION:
                JOptionPane.showMessageDialog(controller, selectedDeck.getName()
                        + " Flashcard Deck has been deleted","Deck has been deleted", JOptionPane.PLAIN_MESSAGE);
                controller.myFlashcardDecks.removeFlashcardDeck(
                        controller.myFlashcardDecks.getFlashcardDeckFromPosition(position));
                controller.returnToMainMenu();
            case JOptionPane.NO_OPTION:
                JOptionPane.getRootFrame().dispose();
        }
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
