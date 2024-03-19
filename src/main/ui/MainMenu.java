package ui;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JPanel {
    private static final String MAIN_GREETING = "Welcome to Flashcard App";
    private JLabel greeting;
    private FlashcardProgramUI controller;

    public MainMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());
        placeGreeting();
        placeButtons();
    }

    private void placeGreeting() {
        greeting = new JLabel(MAIN_GREETING, JLabel.CENTER);
        this.add(greeting, BorderLayout.NORTH);
        JPanel leftSpace = new JPanel();
        leftSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        JPanel rightSpace = new JPanel();
        rightSpace.setPreferredSize(new Dimension((FlashcardProgramUI.WIDTH) / 4, FlashcardProgramUI.HEIGHT));
        this.add(leftSpace, BorderLayout.EAST);
        this.add(rightSpace, BorderLayout.WEST);
    }

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

    private JButton makeCreateButton() {
        JButton create = new JButton(ButtonNames.CREATE.getValue());
       // create.addActionListener(e -> {
       //     createNewDeck();  });
        return create;
    }

    private JButton makeSelectButton() {
        JButton select = new JButton(ButtonNames.SELECT.getValue());
        //select.addActionListener(e -> {
        //    FlashcardProgramUI.selectADeck(); });
        return select;
    }

    private JButton makeSaveButton() {
        JButton save = new JButton(ButtonNames.SAVE.getValue());
        save.addActionListener(e -> {
            controller.saveFlashcardDeck(); });
        return save;
    }

    private JButton makeLoadButton() {
        JButton load = new JButton(ButtonNames.LOAD.getValue());
        load.addActionListener(e -> {
            controller.loadFlashcardDeck(); });
        return load;
    }

    private JButton makeDeleteButton() {
        JButton delete = new JButton(ButtonNames.DELETE.getValue());
        //delete.addActionListener(e -> {
        //    FlashcardProgramUI.deleteADeck(); });
        return delete;
    }

    private JButton makeQuitButton() {
        JButton quit = new JButton(ButtonNames.QUIT.getValue());
        //quit.addActionListener(e -> {
        //    FlashcardProgramUI.quit(); });
        return quit;
    }


}
