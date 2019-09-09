package db;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_DIRECTORY= "src/main/java/db/";
    public DBConnection(String dbName) {
        verifyDB(dbName);
    }

    private void verifyDB(String dbName) {

        final File dbFile = new File(DB_DIRECTORY,dbName);

        if (dbFile.isDirectory()) {
            System.out.println("WARNING: " + dbName + " database file shares its name with a directory. Rename the file and try again.");
        } else if (!dbFile.exists()) {
            System.out.println("WARNING: Specified DB file: \"" + dbName + "\" not found. Creating new SQL lite DB");
            connect(dbName);
        } else {
            System.out.println("Specified \"" + dbName + "\" file found.");
            connect(dbName);
        }
    }

    private void connect(String dbName) {

        Connection connection = null;
        try {

            String url = "jdbc:sqlite:" + DB_DIRECTORY + dbName;
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite \"" + dbName + "\" has been established.");

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        } finally {
            if (connection != null) {
                try {
                    System.out.println("Closing connection to \"" + dbName + "\"");
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

