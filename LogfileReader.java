import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

/**
 * A class to read information from a file of web server accesses.
 * Currently, the log file is assumed to contain simply
 * date and time information in the format:
 *
 *    year month day hour minute
 * Log entries are sorted into ascending order of date.
 * 
 * @author Elisha White
 * @version 2024.04.29
 */
public class LogfileReader implements Iterator<LogEntry> 
{
    private String format;
    private ArrayList<LogEntry> entries;
    private Iterator<LogEntry> dataIterator;

    /**
     * Constructs a LogfileReader object with a default file name "weblog.txt".
     */
    public LogfileReader() 
    {
        this("weblog.txt");
    }

    /**
     * Constructs a LogfileReader object with the specified file name.
     * Reads data from the log file, sorts the entries, and initializes the iterator.
     * 
     * @param filename The name of the log file to read.
     */
    public LogfileReader(String filename) 
    {
        format = "Year Month(1-12) Day Hour Minute";
        entries = new ArrayList<>();
        
        boolean dataRead;
        try {
            URL fileURL = getClass().getClassLoader().getResource(filename);
            if (fileURL == null) {
                throw new FileNotFoundException(filename);
            }
            Scanner logfile = new Scanner(new File(fileURL.toURI()));
            while (logfile.hasNextLine()) {
                String logline = logfile.nextLine();
                LogEntry entry = new LogEntry(logline);
                entries.add(entry);
            }
            logfile.close();
            dataRead = true;
        } catch (FileNotFoundException | URISyntaxException e) {
            System.out.println("Problem encountered: " + e);
            dataRead = false;
        }
        if (!dataRead) {
            System.out.println("Failed to read the data file: " + filename);
            System.out.println("Using simulated data instead.");
            createSimulatedData(entries, 100);
        }
        Collections.sort(entries);
        reset();
    }

    /**
     * Checks if there are more log entries available.
     * 
     * @return true if there are more entries, false otherwise.
     */
    public boolean hasNext() 
    {
        return dataIterator.hasNext();
    }

    /**
     * Retrieves the next log entry.
     * 
     * @return The next log entry.
     */
    public LogEntry next() 
    {
        return dataIterator.next();
    }

    /**
     * Removes the current log entry. This operation is not permitted.
     */
    public void remove() 
    {
        System.err.println("It is not permitted to remove entries.");
    }

    /**
     * Retrieves the format of the log data.
     * 
     * @return The format of the log data.
     */
    public String getFormat() 
    {
        return format;
    }

    /**
     * Resets the iterator to the beginning of the log entries.
     */
    public void reset() 
    {
        dataIterator = entries.iterator();
    }

    /**
     * Prints all log data.
     */
    public void printData() 
    {
        for (LogEntry entry : entries) 
        {
            System.out.println(entry);
        }
    }

    /**
     * Creates simulated log data and adds it to the provided list.
     * 
     * @param data       The list to which the simulated log data will be added.
     * @param numEntries The number of simulated entries to generate.
     */
    private void createSimulatedData(ArrayList<LogEntry> data, int numEntries) 
    {
        LogfileCreator creator = new LogfileCreator();
        for (int i = 0; i < numEntries; i++) 
        {
            data.add(creator.createEntry(2022));
        }
    }
}
