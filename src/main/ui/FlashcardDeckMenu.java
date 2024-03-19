package ui;

import javax.swing.*;
import java.awt.*;

public class FlashcardDeckMenu extends JPanel {
    private static final String FLASHCARDDECKMENU_GREETING =
            "Please select what you would like to do with Flashcard Deck: ";
    private JLabel message;
    private FlashcardProgramUI controller;

    public FlashcardDeckMenu(FlashcardProgramUI controller) {
        this.controller = controller;
        this.setPreferredSize(new Dimension(FlashcardProgramUI.WIDTH, FlashcardProgramUI.HEIGHT));
        this.setLayout(new BorderLayout());

        placeMessage();
        placeButtons();
    }

    private void placeMessage() {
        message = new JLabel(FLASHCARDDECKMENU_GREETING + controller.currentDeck.getName());
        this.add(message, BorderLayout.NORTH);
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
        mainButtons.add(makeDisplayButton());
        mainButtons.add(makeCreateCardButton());
        mainButtons.add(makeRemoveButton());
        mainButtons.add(makeEditButton());
        mainButtons.add(makeStudyButton());
        mainButtons.add(makeChangeNameButton());
        mainButtons.add(makeBackButton());
        this.add(mainButtons, BorderLayout.CENTER);
    }

    private JButton makeDisplayButton() {
        JButton display = new JButton(ButtonNames.DISPLAY.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return display;
    }

    private JButton makeCreateCardButton() {
        JButton create = new JButton(ButtonNames.CREATE_CARD.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return create;
    }

    private JButton makeRemoveButton() {
        JButton remove = new JButton(ButtonNames.REMOVE.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return remove;
    }

    private JButton makeEditButton() {
        JButton edit = new JButton(ButtonNames.EDIT.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return edit;
    }

    private JButton makeStudyButton() {
        JButton study = new JButton(ButtonNames.STUDY.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return study;
    }

    private JButton makeChangeNameButton() {
        JButton change = new JButton(ButtonNames.CHANGE_NAME.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return change;
    }

    private JButton makeBackButton() {
        JButton back = new JButton(ButtonNames.BACK.getValue());
        // create.addActionListener(e -> {
        //     controller.createNewDeck();  });
        return back;
    }

}
