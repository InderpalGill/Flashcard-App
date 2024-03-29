package ui;

import model.Flashcard;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// JPanel class that displays a list of Flashcards in a Flashcard deck as buttons and lets users click on a Flashcard
// they would like to edit.
public class EditFlashcardsMenu extends JPanel {

    private final FlashcardProgramUI controller;

    //EFFECTS: Constructor for class. Sets dimensions and places components on main JPanel
    public EditFlashcardsMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());

        placeMessage();
        placeButtons();
    }

    //EFFECTS: Places message at top of JPanel, and also sets the BorderLayout
    private void placeMessage() {
        JPanel title = new JPanel();
        title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        JLabel message = new JLabel("Please select a Flashcard from below to delete or edit");
        JLabel number = new JLabel("There are currently " + controller.currentDeck.getSizeTracker()
                + " cards in this deck");
        message.setFont(new Font("Comic Sans", Font.BOLD, 18));
        message.setBorder(new EmptyBorder(10, 0, 10, 0));
        number.setFont(new Font("Comic Sans", Font.BOLD, 18));
        number.setBorder(new EmptyBorder(10, 0, 10, 0));
        title.add(message);
        title.add(number);
        message.setAlignmentX(Component.CENTER_ALIGNMENT);
        number.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title, BorderLayout.NORTH);
        JPanel leftSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        JPanel rightSpace = new JPanel();
        rightSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        this.add(leftSpace, BorderLayout.EAST);
        this.add(rightSpace, BorderLayout.WEST);
    }

    // EFFECTS: Creates and places buttons representing Flashcards from FlashcardProgramUI.currentDeck
    // onto a BoxLayout Panel that is added to main JPanel of this class
    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new BoxLayout(mainButtons, BoxLayout.Y_AXIS));
        for (Flashcard f : controller.currentDeck.getFlashcards()) {
            JButton newButton = (JButton) makeButton(controller.currentDeck.getPositionOfCardInList(f),
                    "Card #" + controller.currentDeck.getPositionOfCardInList(f) + " : " + f.getQuestion());
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

    //EFFECTS: Method to create a button that selects a Flashcard on click. Button click will call
    // selectFlashcard method
    private Component makeButton(int position, String name) {
        JButton button = new JButton(name);
        button.setMaximumSize(new Dimension((FlashcardProgramUI.WIDTH) / 2, 50));
        button.setFocusable(false);
        button.addActionListener(e -> selectFlashcard(position));
        return button;
    }

    //MODIFIES: FlashcardProgramUI.currentCard
    //EFFECTS: sets FlashcardProgramUI.currentCard based on input; its position in FlashcardProgramUI.currentDeck
    // calls editFlashcard
    private void selectFlashcard(int position) {
        controller.setCurrentCard(controller.currentDeck.getCardFromIndex(position - 1));
        controller.editFlashcard();
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
