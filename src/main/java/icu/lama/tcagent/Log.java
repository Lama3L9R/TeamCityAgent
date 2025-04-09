package icu.lama.tcagent;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Log {
    private static File LOG_FILE;
    private static final PrintStream OUT;

    static {
        String catalinaHome = System.getProperty("catalina.home");
        if (catalinaHome == null) {
            catalinaHome = System.getenv("CATALINA_HOME");
        }

        if (catalinaHome != null) {
            String logPath = catalinaHome + "/logs/tcagent.log";
            LOG_FILE = new File(logPath);
            try {
                if (LOG_FILE.exists()) {
                    LOG_FILE.delete();
                }

                LOG_FILE.createNewFile();
                OUT = new PrintStream(LOG_FILE);
            } catch (IOException e) {
                throw new RuntimeException("Failed to create log file", e);
            }
        } else {
            System.err.println("CATALINA_HOME not set, using System.out for logging");
            OUT = System.out;
        }
    }

    public static void info(String message) {
        OUT.println("[I] " + message);
        OUT.flush();
    }

    public static void error(String message) {
        OUT.println("[E] " + message);
        OUT.flush();
    }

    public static void error(String message, Throwable e) {
        OUT.println("[E] " + message);
        e.printStackTrace(OUT);
        OUT.flush();
    }

    public static void println(String message) {
        OUT.println(message);
        OUT.flush();
    }
}
