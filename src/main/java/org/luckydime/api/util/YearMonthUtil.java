package org.luckydime.api.util;

import java.time.LocalDate;
import java.time.YearMonth;

public class YearMonthUtil {
    public static final String YEAR_MONTH_SEPARATOR = "-";

    public static LocalDate getFirstDayFromYearMonth(String yearMonth) {
        return YearMonth.of(getYearFromYearMonth(yearMonth), getMonthFromYearMonth(yearMonth)).atDay(1);
    }

    public static LocalDate getLastDayFromYearMonth(String yearMonth) {
        return YearMonth.of(getYearFromYearMonth(yearMonth), getMonthFromYearMonth(yearMonth)).atEndOfMonth();
    }

    private static int getYearFromYearMonth(String yearMonth) {
        return Integer.parseInt(yearMonth.split(YEAR_MONTH_SEPARATOR)[0]);
    }

    private static int getMonthFromYearMonth(String yearMonth) {
        return Integer.parseInt(yearMonth.split(YEAR_MONTH_SEPARATOR)[1]);
    }
}
