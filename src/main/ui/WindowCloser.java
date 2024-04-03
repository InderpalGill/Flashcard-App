package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//class that overrides default Windowclosing behavior. Prints Eventlog to console when window is closed
public class WindowCloser extends WindowAdapter {

    FlashcardProgramUI window;
    EventLog eventLog = EventLog.getInstance();

    //EFFECTS: Constructor for class
    WindowCloser(FlashcardProgramUI window) {
        this.window = window;
    }

    //EFFECTS: Changes the windowclosing behavior of JFrame. Prints EventLog when JFrame is closed
    @Override
    public void windowClosing(WindowEvent e) {
        for (Event l : eventLog) {
            System.out.println(l);
        }
        System.exit(0);
    }
}
