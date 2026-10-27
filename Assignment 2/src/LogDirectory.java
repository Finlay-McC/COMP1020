import java.util.ArrayList;

/**
 * LogDirectory<p>
 * A directory of research station logs.<p>
 * COMP 1020 Fall 2026 A01
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class LogDirectory {

    private final ArrayList<Log> logs;

    /**
     * Constructor for LogDirectory
     */
    LogDirectory() {
        logs = new ArrayList<Log>();
    }

    /**
     * Adds a log to the directory.
     * @param log The log to add
     * @return True if the log was added, false if it already exists
     */
    boolean add(Log log) {
        for (Log l : logs) {
            if (l.getName().equalsIgnoreCase(log.getName())) {
                return false;
            }
        }
        logs.add(log);
        return true;
    }

    /**
     * Finds a log in the directory by name
     * @param name The name of the log to find
     * @return The log with the given name, or null if not found
     */
    Log findLog(String name) {
        for (Log l : logs) {
            if (l.matchesName(name)) {
                return l;
            }
        }
        return null;
    }

    /**
     * Finds all logs in the directory that contain the given keyword
     * @param keyword The keyword to search for
     * @return An ArrayList of logs that contain the keyword
     */
    ArrayList<Log> findByKeyword(String keyword) {
        ArrayList<Log> matches = new ArrayList<Log>();
        for (Log l : logs) {
            if (l.containsKeyword(keyword)) {
                matches.add(l);
            }
        }
        return matches;
    }

    /**
     * Gets all logs in the directory
     * @return An ArrayList of all logs in the directory
     */
    ArrayList<Log> getAll() {
        return new ArrayList<Log>(logs);
    }

    /**
     * Gets the number of logs in the directory
     * @return The number of logs in the directory
     */
    int size() {
        return logs.size();
    }

    /**
     * toString method for LogDirectory
     * @return A string representation of the directory
     */
    public String toString() {
        String output = "";
        for (int i = 0; i < logs.size(); i++) {
            if (i == logs.size() - 1) {
                output += logs.get(i).toString();
            } else {
                output += logs.get(i).toString() + "\n";
            }
        }
        return output;
    }
}
