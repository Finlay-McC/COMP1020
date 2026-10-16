import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A2Main
 * <p>
 * COMP 1020 Fall 2026 A01
 *
 * @author Finlay McCorrister
 * @since 2026-10-09
 */
public class A2Main {
    public static final String INPUT_FILE = "logs.txt";
    public static final String OUTPUT_FILE = "report.txt";

    public static void main(String[] args) {
        LogDirectory directory = loadDirectory(INPUT_FILE);
        if (directory.size() == 0) {
            System.out.println("No logs were read, exiting program.");
        } else {
            runMenu(directory);
        }

        System.out.println("End of processing");
    }

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

    public static void printLogs(ArrayList<Log> logs) {
        if (!logs.isEmpty()) {
            for (Log log : logs) {
                System.out.println(log.toString());
            }
        }
    }

    public static void saveReport(LogDirectory directory, String filename) {
        ArrayList<Log> logs = directory.getAll();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(filename));
            pw.print("");
            for (Log log : logs) {
                if (log.isAnalyzed()) {
                    pw.println(log.toReportString() + "\n");
                }
            }
            pw.close();
            System.out.println("Report saved to " + filename + "successfully.");
        } catch (IOException ioe) {
            System.out.println("IO Exception while writing file: " + filename);
        }
    }

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
                directory.toString();
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
                System.out.println("Enter the keyword you woud like to search for: ");
                input = sc.nextLine();
                matches = directory.findByKeyword(input);
                if (matches.isEmpty()) {
                    System.out.println(input + " was not found in any logs.");
                } else {
                    printLogs(matches);
                }
            }
        }
    }
}
