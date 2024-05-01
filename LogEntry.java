import java.util.Calendar;

/**
 * Store the data from a single line of a
 * web-server log file.
 * Individual fields are made available via
 * accessors such as getHour() and getMinute().
 * 
 * @author Elisha White
 * @version 2024.04.29
 */
public class LogEntry implements Comparable<LogEntry> 
{
    // The timestamp of the log entry.
    private Calendar when;

    // Constants representing the indices of data values in the dataValues array.
    private static final int YEAR = 0, MONTH = 1, DAY = 2, HOUR = 3, MINUTE = 4;

    // Array to store the data values extracted from a log line.
    private int[] dataValues;

    /**
     * Construct a LogEntry object by parsing a log line string.
     * 
     * @param logline The log line string containing date and time information.
     */
    public LogEntry(String logline) 
    {
        dataValues = new int[5];
        // Tokenize the logline and extract the data values.
        LoglineTokenizer tokenizer = new LoglineTokenizer();
        tokenizer.tokenize(logline, dataValues);
        // Set the timestamp based on the extracted data values.
        setWhen();
    }

    /**
     * Construct a LogEntry object with specified date and time values.
     * 
     * @param year   The year of the log entry.
     * @param month  The month of the log entry (1-12).
     * @param day    The day of the log entry (1-31).
     * @param hour   The hour of the log entry (0-23).
     * @param minute The minute of the log entry (0-59).
     */
    public LogEntry(int year, int month, int day, int hour, int minute) 
    {
        dataValues = new int[5];
        // Set the data values for year, month, day, hour, and minute.
        dataValues[YEAR] = year;
        dataValues[MONTH] = month;
        dataValues[DAY] = day;
        dataValues[HOUR] = hour;
        dataValues[MINUTE] = minute;
        // Set the timestamp based on the specified date and time.
        setWhen();
    }

    /**
     * Get the hour of the log entry.
     * 
     * @return The hour of the log entry (0-23).
     */
    public int getHour() 
    {
        return dataValues[HOUR];
    }

    /**
     * Get the minute of the log entry.
     * 
     * @return The minute of the log entry (0-59).
     */
    public int getMinute() 
    {
        return dataValues[MINUTE];
    }

    /**
     * Get a string representation of the LogEntry object.
     * 
     * @return A string containing the data values of the log entry.
     */
    public String toString() 
    {
        StringBuilder buffer = new StringBuilder();
        for (int value : dataValues) 
        {
            // Prefix a leading zero on single digit numbers.
            if (value < 10) 
            {
                buffer.append('0');
            }
            buffer.append(value);
            buffer.append(' ');
        }
        // Remove trailing space and return the string representation.
        return buffer.toString().trim();
    }

    /**
     * Compare this LogEntry object with another LogEntry object based on their timestamps.
     * 
     * @param otherEntry The LogEntry object to compare with.
     * @return A negative value if this LogEntry comes before the other, a positive value
     *         if this LogEntry comes after the other, and zero if the LogEntries are the same.
     */
    public int compareTo(LogEntry otherEntry) 
    {
        // Use the compareTo method of the Calendar class to compare timestamps.
        return when.compareTo(otherEntry.getWhen());
    }

    /**
     * Get the month of the log entry.
     * 
     * @return The month of the log entry (1-12).
     */
    public int getMonth() 
    {
        return dataValues[MONTH];
    }

    /**
     * Get the day of the log entry.
     * 
     * @return The day of the log entry (1-31).
     */
    public int getDay() 
    {
        return dataValues[DAY];
    }

    /**
     * Get the timestamp of the log entry.
     * 
     * @return The timestamp as a Calendar object.
     */
    private Calendar getWhen() 
    {
        return when;
    }

    /**
     * Set the timestamp of the log entry based on the data values.
     */
    private void setWhen() 
    {
        // Create a Calendar object representing the timestamp.
        when = Calendar.getInstance();
        // Set the timestamp using the extracted data values.
        when.set(dataValues[YEAR], dataValues[MONTH] - 1, dataValues[DAY], dataValues[HOUR], dataValues[MINUTE]);
    }
}