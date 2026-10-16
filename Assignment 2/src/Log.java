import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Log
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class Log {
    private String name;
    private int year;
    private String filename;
    private ArrayList<String> lines;
    private boolean analyzed;
    private int wordCount;
    private int uniqueWordCount;
    private String longestWord;
    private String note;

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

    void addLine(String line) {
        lines.add(line);
        analyzed = false;
        wordCount = 0;
        uniqueWordCount = 0;
        longestWord = "";
    }

    String getName() {
        return this.name;
    }

    int getYear() {
        return this.year;
    }

    String getFilename() {
        return this.filename;
    }

    ArrayList<String> getLines() {
        return new ArrayList<String>(lines);
    }

    boolean matchesName(String name) {
        return this.name.equalsIgnoreCase(name);
    }

    boolean containsKeyword(String keyword) {
        for (String line : lines) {
            if (line.toLowerCase().contains(keyword.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    void analyze() {
        ArrayList<String> uniqueWords = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        for (String line : lines) {
            words = new ArrayList<String>(List.of(line.split(" ")));
            for (String word : words) {
                if (!uniqueWords.contains(word)) {
                    uniqueWords.add(word);
                    uniqueWordCount++;
                }
                if (word.length() > longestWord.length()) {
                    longestWord = word;
                }
            }
            wordCount += words.size();
        }
        analyzed = true;
    }

    boolean isAnalyzed() {
        return analyzed;
    }

    int getLineCount() {
        return lines.size();
    }

    int getWordCount() {
        return wordCount;
    }

    int getUniqueWordCount() {
        return uniqueWordCount;
    }

    String getLongestWord() {
        return longestWord;
    }

    void setNote(String note) {
        this.note = note;
    }

    String getNote() {
        return note;
    }

    String toReportString() {
        return "Name: " + name + "\nYear: " + year + "\nLines: " + lines.size() + "\nWords: " + wordCount +
                "\nUnique Words: " + uniqueWordCount + "\nLongest Word: " + longestWord + "\nNote: " + note;
    }

    public String toString() {
        return name + " | " + year + " | " + analyzed;
    }
}
