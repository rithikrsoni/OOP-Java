import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * The class describes the function of the word counter program.
 * 
 **/
public class WordCounter {
    /**
     * The main method is designed to accept an arguement from the command line.
     * 
     * @param args is accepting user input from terminal
     **/
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java WordCounter <filename> <searchTerm>");
            return;
        }

        String filename = args[0];
        String[] searchTerm = new String[args.length - 1];
        System.arraycopy(args, 1, searchTerm, 0, args.length - 1);
        // this loop is designed to check whether a file exists in a specified location.
        File file = new File(filename);
        if (!file.isFile()) {
            System.out.println("File not found: " + filename);
            return;
        }

        int[] count = new int[searchTerm.length];
        int totalOccurrences = 0;

        // Using BufferedReader to read files line by line instead of scanner class.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("[^\\p{L}0-9']+");

                for (String word : words) {
                    for (int i = 0; i < searchTerm.length; i++) {
                        if (word.equals(searchTerm[i])) {
                            count[i]++;
                            totalOccurrences++;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occured.");
        }

        if (searchTerm.length == 1) {
            String oneSearchTerm = searchTerm[0];
            int count1 = count[0];
            System.out.println(
                    "The word '" + oneSearchTerm + "' appears " + count1 + " time" + (count1 == 1 ? "." : "s."));

        } else {
            resultsTable(searchTerm, count, totalOccurrences);
        }
    }

    /**
     * The resultsTable method is designed to print out the output of the checker
     * into a table format.
     * 
     * @param searchTerm       is an array of the search term input by the user.
     * @param count            is an array of the total count of the search terms.
     * @param totalOccurrences is a variable with the total times the search terms
     *                         occurred.
     **/
    private static void resultsTable(String[] searchTerm, int[] count, int totalOccurrences) {
        int wordLength = "Total".length();
        int countLength = "Count".length();

        for (String word : searchTerm) {
            if (word.length() > wordLength) {
                wordLength = word.length();
            }
        }

        for (int c : count) {
            String countStr = String.valueOf(c);
            if (countStr.length() > countLength) {
                countLength = countStr.length();
            }
        }

        System.out.println("|-" + "-".repeat(wordLength) + "-|-" + "-".repeat(countLength) + "-|");
        System.out.printf("| %-" + wordLength + "s | %" + countLength + "s |\n", "WORD", "COUNT");
        System.out.println("|-" + "-".repeat(wordLength) + "-|-" + "-".repeat(countLength) + "-|");

        // Print each word and adjusts according to the length
        for (int i = 0; i < searchTerm.length; i++) {
            System.out.printf("| %-" + wordLength + "s | %" + countLength + "d |\n", searchTerm[i], count[i]);
        }
        // Print the total occurrences.
        System.out.println("|-" + "-".repeat(wordLength) + "-|-" + "-".repeat(countLength) + "-|");
        System.out.printf("| %-" + wordLength + "s | %" + countLength + "d |\n", "TOTAL", totalOccurrences);
        System.out.println("|-" + "-".repeat(wordLength) + "-|-" + "-".repeat(countLength) + "-|");
    }

}
