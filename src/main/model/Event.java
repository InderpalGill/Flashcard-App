package model;

import java.util.Calendar;
import java.util.Date;

//class that creates Events which are recorded as a date, time and description string.
//references AlarmSystemProgram in how to set up Events class
//https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    /**EFFECTS//
     * Creates an event with the given description
     * and the current date/time stamp.
     * @param description  a description of the event
     */
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    /**EFFECTS//
     * Gets the date of this event (includes time).
     * @return  the date of the event
     */
    public Date getDate() {
        return dateLogged;
    }

    /**EFFECTS//
     * Gets the description of this event.
     * @return  the description of the event
     */
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        } else if (other.getClass() != this.getClass()) {
            return false;
        }
        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}
