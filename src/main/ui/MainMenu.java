package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Class that creates and represents a JPanel of main menu options to select from when the application is started.
public class MainMenu extends JPanel {

    private final FlashcardProgramUI controller;

    //EFFECTS: Constructor for class, sets panel dimensions and places components on panel.
    public MainMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setMinimumSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());
        placeGreeting();
        placeButtons();
    }

    //EFFECTS: Places greeting at top of JPanel, and also sets the BorderLayout
    private void placeGreeting() {
        JLabel greeting = new JLabel("Welcome to Flashcard App", JLabel.CENTER);
        greeting.setFont(new Font("Comic Sans", Font.BOLD, 18));
        greeting.setBorder(new EmptyBorder(10, 0, 10, 0));
        this.add(greeting, BorderLayout.NORTH);
        JPanel leftSpace = new JPanel();
        leftSpace.setMinimumSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        JPanel rightSpace = new JPanel();
        rightSpace.setMinimumSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        this.add(leftSpace, BorderLayout.EAST);
        this.add(rightSpace, BorderLayout.WEST);
    }

    //EFFECTS: Places buttons of main menu on to panel.
    private void placeButtons() {
        JPanel mainButtons = new JPanel();
        mainButtons.setLayout(new GridLayout(6,1,10,10));
        mainButtons.add(makeCreateButton());
        mainButtons.add(makeSelectButton());
        mainButtons.add(makeSaveButton());
        mainButtons.add(makeLoadButton());
        mainButtons.add(makeDeleteButton());
        mainButtons.add(makeQuitButton());
        this.add(mainButtons, BorderLayout.CENTER);
    }

    //EFFECTS: Creates a createNewDeck button in main menu. Begins createNewDeck method when button is clicked
    private JButton makeCreateButton() {
        JButton create = new JButton(ButtonNames.CREATE.getValue());
        create.setFocusable(false);
        create.addActionListener(e -> controller.createNewDeck());
        return create;
    }

    //EFFECTS: Creates a select button in main menu. Begins selectADeck method when button is clicked
    private JButton makeSelectButton() {
        JButton select = new JButton(ButtonNames.SELECT.getValue());
        select.setFocusable(false);
        select.addActionListener(e -> controller.selectADeck());
        return select;
    }

    //EFFECTS: Creates a save button in main menu. Begins save method when button is clicked
    private JButton makeSaveButton() {
        JButton save = new JButton(ButtonNames.SAVE.getValue());
        save.setFocusable(false);
        save.addActionListener(e -> controller.saveFlashcardDeck());
        return save;
    }

    //EFFECTS: Creates a load button in main menu. Begins load method when button is clicked
    private JButton makeLoadButton() {
        JButton load = new JButton(ButtonNames.LOAD.getValue());
        load.setFocusable(false);
        load.addActionListener(e -> controller.loadFlashcardDeck());
        return load;
    }

    //EFFECTS: Creates a delete button in main menu. Begins deleteADeck method when button is clicked
    private JButton makeDeleteButton() {
        JButton delete = new JButton(ButtonNames.DELETE.getValue());
        delete.setFocusable(false);
        delete.addActionListener(e -> controller.deleteADeck());
        return delete;
    }

    //EFFECTS: Creates a quit button in main menu. Begins quit method when button is clicked
    private JButton makeQuitButton() {
        JButton quit = new JButton(ButtonNames.QUIT.getValue());
        quit.setFocusable(false);
        quit.setBackground(new Color(253,113,113));
        quit.addActionListener(e -> controller.quit());
        return quit;
    }

}
