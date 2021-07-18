package main;

public class Day implements Cloneable, Comparable<Day> {
    private int year;
    private int month;
    private int day;
    private static final String monthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";

    // Constructor
    public Day(int y, int m, int d) {
        this.year = y;
        this.month = m;
        this.day = d;
    }

    public void set(String sDay) {
        String[] sDayParts = sDay.split("-");
        this.day = Integer.parseInt(sDayParts[0]);
        this.year = Integer.parseInt(sDayParts[2]);
        this.month = monthNames.indexOf(sDayParts[1]) / 3 + 1;
    }

    public Day(String sDay) {
        set(sDay);
    }

    // check if a given year is a leap year
    static public boolean isLeapYear(int y) {
        if (y % 400 == 0)
            return true;
        else if (y % 100 == 0)
            return false;
        else if (y % 4 == 0)
            return true;
        else
            return false;
    }

    // Check if y, m and d are valid
    static public boolean valid(int y, int m, int d) {
        if (m < 1 || m > 12 || d < 1)
            return false;
        switch (m) {
            case 1:
                return d <= 31;
            case 3:
                return d <= 31;
            case 5:
                return d <= 31;
            case 7:
                return d <= 31;
            case 8:
                return d <= 31;
            case 10:
                return d <= 31;
            case 12:
                return d <= 31;
            case 4:
                return d <= 30;
            case 6:
                return d <= 30;
            case 9:
                return d <= 30;
            case 11:
                return d <= 30;
            case 2:
                if (isLeapYear(y))
                    return d <= 29;
                else
                    return d <= 28;
        }
        return false;
    }

    public static boolean isValid(String string) {
        String[] dateParts = string.split("-");
        if (dateParts.length != 3) {
            return false;
        }
        if (!monthNames.contains(dateParts[1])) {
            return false;
        }
        int d = Integer.parseInt(dateParts[0]), m = monthNames.indexOf(dateParts[1]) / 3 + 1,
                y = Integer.parseInt(dateParts[2]);
        if (!valid(y, m, d)) {
            return false;
        } else {
            return true;
        }
    }

    // Return a string for the day like dd MMM yyyy
    public String toString() {
        return day + "-" + monthNames.substring((month - 1) * 3, (month) * 3) + "-" + year;
    }

    // Check whether current date is last day of month
    public boolean isEndOfAMonth() {
        if (valid(year, month, day + 1))
            return false;
        else
            return true;
    }

    // Go to next day
    public Day next() {
        if (isEndOfAMonth())
            if (month == 12)
                return new Day(year + 1, 1, 1); // Since the current day is the end of the year, the next day should be
                                                // Jan 01
            else
                return new Day(year, month + 1, 1); // your task: first day of next month
        else
            return new Day(year, month, day + 1); // your task: next day of current month
    }

    // Comparing two days
    @Override
    public int compareTo(Day another) {
        if (this.year == another.year) {
            if (this.month == another.month) {
                if (this.day == another.day)
                    return 0;
                else {
                    if (this.day > another.day)
                        return 1;
                    else
                        return -1;
                }
            } else {
                if (this.month > another.month)
                    return 1;
                else
                    return -1;
            }
        } else {
            if (this.year > another.year)
                return 1;
            else
                return -1;
        }
    }

    // Moving from today to given number of days
    public Day plusNumOfDays(Integer numOfDays){
        Day d = new Day(year, month, day);
        for (int i = 0; i < numOfDays; i++) {
            d = d.next();
        }
        return d;
    }
}
