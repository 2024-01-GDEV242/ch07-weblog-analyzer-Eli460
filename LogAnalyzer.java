import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Elisha White
 * @version 2024.04.29
 */
import java.util.ArrayList;

public class LogAnalyzer 
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    // ArrayList to store log entries.
    private ArrayList<LogEntry> entries;

    /**
     * Constructor for LogAnalyzer class.
     * Initializes arrays and reader.
     */
    public LogAnalyzer() 
    {
        hourCounts = new int[24];
        reader = new LogfileReader();
        entries = new ArrayList<>();
        loadEntries();
    }

    /**
     * Loads log entries from the reader.
     */
    private void loadEntries() 
    {
        while (reader.hasNext()) 
        {
            entries.add(reader.next());
        }
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData() 
    {
        for (LogEntry entry : entries) 
        {
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly access counts.
     */
    public void printHourlyCounts() 
    {
        System.out.println("Hour: Count");
        for (int hour = 0; hour < hourCounts.length; hour++) 
        {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }

    /**
     * Calculate the total number of accesses.
     * 
     * @return Total number of accesses.
     */
    public int numberofAccesses() 
    {
        int total = 0;
        for (int hour = 0; hour < hourCounts.length; hour++) 
        {
            total += hourCounts[hour];
        }
        return total;
    }

    /**
     * Find the busiest hour.
     * 
     * @return Busiest hour.
     */
    public int busiestHour() 
    {
        int maxAccesses = 0;
        int busiestHour = 0;
        for (int index = 0; index < hourCounts.length; index++) 
        {
            if (hourCounts[index] > maxAccesses) {
                maxAccesses = hourCounts[index];
                busiestHour = index;
            }
        }
        return busiestHour;
    }

    /**
     * Find the quietest hour.
     * 
     * @return Quietest hour.
     */
    public int quietestHour() 
    {
        int minAccesses = Integer.MAX_VALUE;
        int quietestHour = 0;
        for (int index = 0; index < hourCounts.length; index++) 
        {
            if (hourCounts[index] < minAccesses) 
            {
                minAccesses = hourCounts[index];
                quietestHour = index;
            }
        }
        return quietestHour;
    }

    /**
     * Find the busiest two-hour period.
     * 
     * @return Starting hour of the busiest two-hour period.
     */
    public int twoHourBusiest() 
    {
        int maxAccesses = 0;
        int busiestHour = 0;
        for (int index = 0; index < hourCounts.length - 1; index++) 
        {
            int twoHourAccesses = hourCounts[index] + hourCounts[index + 1];
            if (twoHourAccesses > maxAccesses) 
            {
                maxAccesses = twoHourAccesses;
                busiestHour = index;
            }
        }
        return busiestHour;
    }

    /**
     * Find the quietest day based on the number of accesses.
     * 
     * @return The quietest day.
     */
    public int quietestDay() 
    {
        int[] dayCounts = new int[28];
        for (LogEntry entry : entries) 
        {
            int day = entry.getDay();
            if (day >= 1 && day <= 28) 
            {
                dayCounts[day - 1]++;
            }
        }
        int quietestDay = 1;
        int minAccesses = dayCounts[0];
        for (int i = 1; i < dayCounts.length; i++) 
        {
            if (dayCounts[i] < minAccesses) 
            {
                minAccesses = dayCounts[i];
                quietestDay = i + 1;
            }
        }
        return quietestDay;
    }

    /**
     * Find the busiest day based on the number of accesses.
     * 
     * @return The busiest day.
     */
    public int busiestDay() {
        int[] dayCounts = new int[31];
        for (LogEntry entry : entries) 
        {
            int day = entry.getDay();
            if (day >= 1 && day <= 31) 
            {
                dayCounts[day - 1]++;
            }
        }
        int busiestDay =0;
        int maxAccesses = 0;
        for (int i = 0; i < dayCounts.length; i++) 
        {
            if (dayCounts[i] > maxAccesses) 
            {
                maxAccesses = dayCounts[i];
                busiestDay = i + 1;
            }
        }
        return busiestDay;
    }

    /**
     * Calculate the total accesses per month.
     * 
     * @return An array containing the total accesses for each month.
     */
    public int[] totalAccessesPerMonth() 
    {
        int[] monthCounts = new int[12];
        for (LogEntry entry : entries) 
        {
            int month = entry.getMonth();
            monthCounts[month - 1]++;
        }
        return monthCounts;
    }

    /**
     * Calculate the quietest month based on the number of accesses.
     * 
     * @return The quietest month.
     */
    public int quietestMonth() 
    {
        int[] monthCounts = new int[12];
        for (LogEntry entry : entries) 
        {
            int month = entry.getMonth();
            monthCounts[month - 1]++;
        }
        int quietestMonth = 1;
        int minAccesses = monthCounts[0];
        for (int i = 1; i < monthCounts.length; i++) 
        {
            if (monthCounts[i] < minAccesses) {
                minAccesses = monthCounts[i];
                quietestMonth = i + 1;
            }
        }
        return quietestMonth;
    }

    /**
     * Calculate the busiest month based on the number of accesses.
     * 
     * @return The busiest month.
     */
    public int busiestMonth() 
    {
        int[] monthCounts = new int[12];
        for (LogEntry entry : entries) 
        {
            int month = entry.getMonth();
            monthCounts[month - 1]++;
        }
        int busiestMonth = 1;
        int maxAccesses = monthCounts[0];
        for (int i = 1; i < monthCounts.length; i++) 
        {
            if (monthCounts[i] > maxAccesses) 
            {
                maxAccesses = monthCounts[i];
                busiestMonth = i + 1;
            }
        }
        return busiestMonth;
    }

    /**
     * Calculate the average accesses per month.
     * 
     * @return An array containing the average accesses for each month.
     */
    public double[] averageAccessesPerMonth() 
    {
        int[] monthCounts = new int[12];
        double[] averages = new double[12];

        for (LogEntry entry : entries) 
        {
            int month = entry.getMonth() - 1;
            monthCounts[month]++;
        }

        for (int i = 0; i < 12; i++) 
        {
            averages[i] = (double) monthCounts[i] / 5; // Assuming data spans 5 years
        }
        return averages;
    }
}







