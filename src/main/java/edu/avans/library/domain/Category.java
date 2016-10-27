package edu.avans.library.domain;

import java.awt.*;

/**
 * The class <code>Category</code> represents a category on which appointments can be attached.
 * They are served via the <code>>CalendarManager</code>.
 * @author Bram de Hart
 * @version 1.0
 * @see Appointment
 * @see edu.avans.library.businesslogic.CalendarManager
 */
public class Category {
    private String title;
    private Color color;

    public Category() {
    }
}
