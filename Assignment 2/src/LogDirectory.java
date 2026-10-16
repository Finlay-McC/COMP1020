import java.util.ArrayList;

/**
 * LogDirectory
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class LogDirectory {
    private ArrayList<Log> logs;

    LogDirectory() {
        logs = new ArrayList<Log>();
    }

    boolean add(Log log) {
        for (Log l : logs) {
            if (l.getName().equalsIgnoreCase(log.getName())) {
                return false;
            }
        }
        logs.add(log);
        return true;
    }

    Log findLog(String name) {
        for (Log l : logs) {
            if (l.getName().equalsIgnoreCase(name)) {
                return l;
            }
        }
        return null;
    }

    ArrayList<Log> findByKeyword(String keyword) {
        ArrayList<Log> matches = new ArrayList<Log>();
        for (Log l : logs) {
            if (l.containsKeyword(keyword)) {
                matches.add(l);
            }
        }
        return matches;
    }

    ArrayList<Log> getAll() {
        return new ArrayList<Log>(logs);
    }

    int size() {
        return logs.size();
    }

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
