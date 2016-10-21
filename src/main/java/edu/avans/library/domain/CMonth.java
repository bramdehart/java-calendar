package edu.avans.library.domain;
import javax.swing.*;
import java.awt.*;
import java.time.Month;
import java.text.DateFormatSymbols;
import java.util.Calendar;

public class CMonth {
    private Calendar calendar;
    private static Integer CURRENT_MONTH;
    private Integer activeMonth, prevMonth, nextMonth; // for now private

    public CMonth(Calendar calendar) {
        this.calendar = calendar;
        setMonths();

        System.out.println(getPreviousMonth());
        System.out.println(getCurrentMonth());
        System.out.println(getNextMonth());
        System.out.println(getMonthName(getPreviousMonth()));
        System.out.println(getMonthName(getCurrentMonth()));
        System.out.println(getMonthName(getNextMonth()));
    }

    private void setMonths() {
        setCurrentMonth();
        setActiveMonth(CURRENT_MONTH);
        setPreviousMonth();
        setNextMonth();
    }

    public Integer getPreviousMonth() {
        return prevMonth;
    }

    public Integer getNextMonth() {
        return nextMonth;
    }

    public Integer getCurrentMonth() {
        return CURRENT_MONTH;
    }

    public Integer getActiveMonth() {
        return activeMonth;
    }

    public void setPreviousMonth() {
        prevMonth = activeMonth-1;
    }

    public void setNextMonth() {
        nextMonth = activeMonth+1;
    }

    public void setCurrentMonth() {
        CURRENT_MONTH = this.calendar.get(Calendar.MONTH);
    }

    public void setActiveMonth(Integer month) {
        activeMonth = month;
    }

    public String getMonthName(Integer month) {
        return new DateFormatSymbols().getMonths()[month];
    }

    public Integer getMonthDays(Integer month) {
        return 5;
    }
}
