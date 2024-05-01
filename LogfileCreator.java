import java.io.*;
import java.util.*;

/**
 * A class for creating log files of random data.
 * 
 * @author Elisha White
 * @version 2024.04.29
 */


public class LogfileCreator 
{
    private Random rand;

    /**
     * Construct a LogfileCreator object.
     */
    public LogfileCreator() 
    {
        rand = new Random();
    }

    /**
     * Create a log file with random data entries.
     * 
     * @param filename    The name of the file to create.
     * @param numEntries  The number of entries to generate.
     * @return            True if the file creation was successful, false otherwise.
     */
    public boolean createFile(String filename, int numEntries) 
    {
        boolean success = false;
        // Check if the number of entries is valid.
        if (numEntries > 0) 
        {
            try (FileWriter writer = new FileWriter(filename)) 
            {
                // Generate entries for each year up to the current year.
                for (int year = 2018; year <= Calendar.getInstance().get(Calendar.YEAR); year++) 
                {
                    // Create entries for the specified number of times.
                    for (int i = 0; i < numEntries; i++) 
                    {
                        // Create a LogEntry object for the current year and write it to the file.
                        LogEntry entry = createEntry(year);
                        writer.write(entry.toString());
                        writer.write('\n');
                    }
                }
                success = true;
            } catch (IOException e) 
            {
                // Handle IOException if there's a problem writing to the file.
                System.err.println("There was a problem writing to " + filename);
            }
        }
        return success;
    }

    /**
     * Create a LogEntry object with random date, hour, and minute values.
     * 
     * @param year  The year for the log entry.
     * @return      A LogEntry object with random date, hour, and minute values.
     */
    public LogEntry createEntry(int year) 
    {
        // Generate random month (1-12), day (1-28), hour (0-23), and minute (0-59) values.
        int month = 1 + rand.nextInt(12);
        int day = 1 + rand.nextInt(28);
        int hour = rand.nextInt(24);
        int minute = rand.nextInt(60);
        // Return a new LogEntry object with the generated values.
        return new LogEntry(year, month, day, hour, minute);
    }
}

