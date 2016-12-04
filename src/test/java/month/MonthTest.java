package month;

import java.text.DateFormatSymbols;

/**
 * Created by Bram on 05/12/2016.
 */
public class MonthTest {
    /**
     * Gets to month name.
     * @return the month name as a string
     */
    public String getMonthName() {
        Integer month = 11;
        String monthName = new DateFormatSymbols().getMonths()[month];
        System.out.println(monthName);
        return monthName;
    }
}
