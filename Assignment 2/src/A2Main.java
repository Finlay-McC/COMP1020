import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A2Main<p>
 * A small program for storing, searching, and analyzing research station
 * logs.<p>
 * COMP 1020 Fall 2026 A01
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class A2Main {

    // Constants:
    public static final String INPUT_FILE = "logs.txt";
    public static final String OUTPUT_FILE = "report.txt";

    /**
     * Main method for the program
     * @param args
     */
    static void main(String[] args) {
        // load log directory from file
        LogDirectory directory = loadDirectory(INPUT_FILE);
        if (directory.size() == 0) {
            System.out.println("No logs were read, exiting program.");
        } else {
            // run user menu
            runMenu(directory);
        }

        System.out.println("End of processing");
    }

    /**
     * Loads a log directory from a file.
     * @param filename The name of the file to load.
     * @return The loaded LogDirectory object.
     */
    public static LogDirectory loadDirectory(String filename) {
        LogDirectory directory = new LogDirectory();
        String[] line;
        String name;
        int year;
        String logFilename;
        Log log;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            Scanner in = new Scanner(br);
            while (in.hasNextLine()) {
                line = in.nextLine().split(" \\| ");
                if (line.length == 3) {
                    name = line[0];
                    logFilename = line[2];
                    if (directory.findLog(name) == null && name != null && !name.isEmpty()) {
                        // check year is valid format and range
                        try {
                            year = Integer.parseInt(line[1]);
                            if (year < 1900 || year > 2026) {
                                System.out.println("Year " + year + " is not in valid range.");
                                continue;
                            }
                        } catch (NumberFormatException nfe) {
                            System.out.println("Year not in valid format");
                            continue;
                        }
                        try {
                            BufferedReader logReader = new BufferedReader(new FileReader(logFilename));
                            Scanner logSc = new Scanner(logReader);
                            log = new Log(name, year, logFilename);
                            while (logSc.hasNextLine()) {
                                log.addLine(logSc.nextLine());
                            }
                            logReader.close();
                            directory.add(log);
                        } catch (IOException ioe) {
                            System.out.println("IO Exception while reading file: " + logFilename);
                        }
                    } else {
                        System.out.println("Log: \"" + logFilename + "\" already exists, moving to next entry...");
                    }
                }
            }
            br.close();
            return directory;
        } catch (IOException ioe) {
            System.out.println("IO Exception while reading file: " + filename);
            return new LogDirectory();
        }
    }

    /**
     * Prints all logs in the given ArrayList.
     * @param logs The ArrayList of logs to print.
     */
    public static void printLogs(ArrayList<Log> logs) {
        if (!logs.isEmpty()) {
            for (Log log : logs) {
                System.out.println(log.toString());
            }
        }
    }

    /**
     * Saves the report to a file.
     * @param directory The LogDirectory object containing the logs to save.
     * @param filename The name of the file to save the report to.
     */
    public static void saveReport(LogDirectory directory, String filename) {
        ArrayList<Log> logs = directory.getAll();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            boolean firstAnalyzed = true;
            pw.print("");
            for (Log log : logs) {
                if (log.isAnalyzed()) {
                    if (firstAnalyzed) {
                        firstAnalyzed = false;
                        pw.println(log.toReportString());
                    } else {
                        // add a blank line between each log after the first one
                        pw.println("\n" + log.toReportString());

                    }
                }
            }
            pw.close();
            System.out.println("Report saved to " + filename + " successfully.");
        } catch (IOException ioe) {
            System.out.println("IO Exception while writing file: " + filename);
        }
    }

    /**
     * Runs the user menu.
     * @param directory The LogDirectory object containing the logs.
     */
    public static void runMenu(LogDirectory directory){
        boolean running = true;
        String input;
        Log log;
        ArrayList<Log> matches;
        while (running) {
            System.out.println("""
                    1 - List logs
                    2 - Open log
                    3 - Analyze log
                    4 - Search logs
                    5 - Add note
                    6 - Save report
                    0 - Exit"""
            );
            Scanner sc = new Scanner(System.in);
            input = sc.nextLine();
            if (input.equals("1")) {
                System.out.println(directory.toString());
            } else if (input.equals("2")) {
                System.out.println("Enter the name of the log to open: ");
                input = sc.nextLine();
                log = directory.findLog(input);
                if (log != null) {
                    ArrayList<String> lines = log.getLines();
                    for (String line : lines) {
                        System.out.println(line);
                    }
                } else {
                    System.out.println("Log not found");
                }
            } else if (input.equals("3")) {
                System.out.println("Enter the name of the log to analyze: ");
                input = sc.nextLine();
                log = directory.findLog(input);
                if (log != null) {
                    log.analyze();
                    System.out.println("Line count: " + log.getLineCount() + "\nWord count: " + log.getWordCount() +
                            "\nUnique word count: " + log.getLongestWord());
                } else {
                    System.out.println("Log not found");
                }
            } else if (input.equals("4")) {
                System.out.println("Enter the keyword you would like to search for: ");
                input = sc.nextLine();
                matches = directory.findByKeyword(input);
                if (matches.isEmpty()) {
                    System.out.println(input + " was not found in any logs.");
                } else {
                    printLogs(matches);
                }
            } else if (input.equals("5")) {
                System.out.println("Enter the name of the log you would like to add a note to: ");
                input = sc.nextLine();
                log = directory.findLog(input);
                if (log != null) {
                    System.out.println("Enter the note to add: ");
                    input = sc.nextLine();
                    log.setNote(input);
                }
            } else if (input.equals("6")) {
                saveReport(directory, OUTPUT_FILE);
            } else if (input.equals("0")) {
                running = false;
            } else {
                System.out.println("Invalid input");
            }
        }
    }
}
