import java.util.ArrayList;
import java.util.List;

/**
 * Log<p>
 * A single research station log.<p>
 * COMP 1020 Fall 2026 A01
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class Log {

    private final String name;
    private final int year;
    private final String filename;
    private final ArrayList<String> lines;

    private boolean analyzed;
    private int wordCount;
    private int uniqueWordCount;
    private String longestWord;
    private String note;

    /**
     * Constructor for the Log class
     * @param name The name of the log
     * @param year The year the log was created
     * @param filename The filename of the log
     */
    Log(String name, int year, String filename) {
        this.name = name;
        this.year = year;
        this.filename = filename;
        this.lines = new ArrayList<>();
        this.analyzed = false;
        this.wordCount = 0;
        this.uniqueWordCount = 0;
        longestWord = "";
        note = "";
    }

    /**
     * Adds a line to the Log
     * @param line The line to add
     */
    void addLine(String line) {
        lines.add(line);
        analyzed = false;
        wordCount = 0;
        uniqueWordCount = 0;
        longestWord = "";
    }

    /**
     * Gets the name of the log
     * @return The name of the log
     */
    String getName() {
        return this.name;
    }

    /**
     * Gets the year the log was created
     * @return The year the log was created
     */
    int getYear() {
        return this.year;
    }

    /**
     * Gets the filename of the log
     * @return The filename of the log
     */
    String getFilename() {
        return this.filename;
    }

    /**
     * Gets the lines of the log
     * @return An ArrayList of the lines of the log
     */
    ArrayList<String> getLines() {
        return new ArrayList<String>(lines);
    }

    /**
     * Checks if the log matches the given name
     * @param name The name to check
     * @return True if the name matches, false otherwise
     */
    boolean matchesName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    /**
     * Checks if the log contains the given keyword
     * @param keyword The keyword to check
     * @return True if the keyword is found in the log, false otherwise
     */
    boolean containsKeyword(String keyword) {
        for (String line : lines) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Analyzes the log to calculate word count, unique word count, and longest word
     */
    void analyze() {
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        char beginning;
        char end;
        for (String line : lines) {
            words = new ArrayList<String>(List.of(line.split(" ")));
            for (String word : words) {
                word  = word.toLowerCase().strip();
                beginning = word.charAt(0);
                end = word.charAt(word.length() - 1);
                if (beginning == '.' || beginning == ',' || beginning == '!' || beginning == '?' || beginning == ';' ||
                        beginning == ':') {
                    word = word.substring(1);
                }
                if (end == '.' || end == ',' || end == '!' || end == '?' || end == ';' || end == ':') {
                    word = word.substring(0, word.length() - 1);
                }
                if (!word.isEmpty()) {
                    if (!uniqueWords.contains(word)) {
                        uniqueWords.add(word);
                        uniqueWordCount++;
                    }
                    if (word.length() > longestWord.length()) {
                        longestWord = word;
                    }
                }
            }
            wordCount += words.size();
        }
        analyzed = true;
    }

    /**
     * Checks if the log has been analyzed
     * @return True if the log has been analyzed, false otherwise
     */
    boolean isAnalyzed() {
        return analyzed;
    }

    /**
     * Gets the number of lines in the log
     * @return The number of lines in the log
     */
    int getLineCount() {
        return lines.size();
    }

    /**
     * Gets the number of words in the log
     * @return The number of words in the log
     */
    int getWordCount() {
        return wordCount;
    }

    /**
     * Gets the number of unique words in the log
     * @return The number of unique words in the log
     */
    int getUniqueWordCount() {
        return uniqueWordCount;
    }

    /**
     * Gets the longest word in the log
     * @return The longest word in the log
     */
    String getLongestWord() {
        return longestWord;
    }

    /**
     * Sets the note for the log
     * @param note The new note for the log
     */
    void setNote(String note) {
        this.note = note;
    }

    /**
     * Gets the note for the log
     * @return The note for the log
     */
    String getNote() {
        return note;
    }

    /**
     * Returns a string representation of the log formatted as a report
     * @return The formatted string
     */
    String toReportString() {
        return "Name: " + name + "\nYear: " + year + "\nLines: " + lines.size() + "\nWords: " + wordCount +
                "\nUnique words: " + uniqueWordCount + "\nLongest word: " + longestWord + "\nNote: " + note;
    }

    /**
     * toString method for the Log class
     * @return Formatted string representation of the log
     */
    public String toString() {
        return name + " | " + year + " | " + analyzed;
    }
}
