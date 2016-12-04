package week;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Bram on 04/12/2016.
 */
public class WeekTest {
    /**
     * Gets the weekday name.
     * @return the weekday as a string
     */
    public String getWeekDayName() {
        Date date = new Date();
        String weekDayName = new SimpleDateFormat("EEEE").format(date);
        System.out.println(weekDayName);
        return weekDayName;
    }

    /**
     * Returns the weeknumber based on a given date.
     * @return
     */
    public Integer getWeekNumber() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.WEEK_OF_YEAR);
        System.out.println(week);
        return week;
    }
}
