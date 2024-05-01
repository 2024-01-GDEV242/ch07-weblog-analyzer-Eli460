import java.util.Scanner;

/**
 * Break up line from a web server log file into
 * its separate fields.
 * Currently, the log file is assumed to contain simply
 * integer date and time information.
 * 
 * @author Elisha White
 * @version 2024.04.29
 */
    /**
     * Construct a LogLineAnalyzer
     */
public class LoglineTokenizer 
{
    public LoglineTokenizer() 
    {
        
    }
    
    /**
     * Tokenize a log line and extract individual data items.
     * 
     * @param logline   The log line string to be tokenized.
     * @param dataLine  The integer array to store the extracted data items.
     * @throws java.util.NoSuchElementException If there are insufficient data items in the log line.
     */
    public void tokenize(String logline, int[] dataLine) 
    {
        try 
        {
            Scanner tokenizer = new Scanner(logline);
            for (int i = 0; i < dataLine.length; i++) 
            {
                dataLine[i] = tokenizer.nextInt();
            }
        } catch (java.util.NoSuchElementException e) 
        {
            System.out.println("Insufficient data items on log line: " + logline);
            throw e;
        }
    }
}
