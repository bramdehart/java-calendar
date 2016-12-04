package calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Bram on 04/12/2016.
 */
public class CalendarTest {
    /**
     * Returns the weeknumber based on a given date.
     * @return
     */
    public Date getDate() {
        Integer month = 0; // zero based
        Integer day = 1;
        Integer year = 2016;

        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
        String dateString = (month+1)+"/"+day+"/"+year;
        Date date = new Date();

        try {
            date = formatter.parse(dateString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(date);
        return date;
    }
}
