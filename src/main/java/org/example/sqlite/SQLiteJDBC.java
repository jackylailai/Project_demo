package org.example.sqlite;

import java.sql.*;
public class SQLiteJDBC
{
//    public static void main( String args[] )
    {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:user.db");
            System.out.println("Opened database successfully");

            statement = connection.createStatement();
            String sql = "CREATE TABLE COURSE " +
                    "(id INT PRIMARY KEY     NOT NULL," +
                    " courseID   TEXT    NOT NULL, " +
                    " courseName  VARCHAR(255)  NOT NULL, " +
                    " courseDesc  TEXT, " +
                    " creditUnit  INT       )";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
}