package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    private static final String SERVER_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "university_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "pass";

    public static Connection createDBConnection(){
        Connection connection = null;

        try{
            Connection tempConnection = DriverManager.getConnection(SERVER_URL, USERNAME, PASSWORD);

            Statement statement = tempConnection.createStatement();
            statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " +DB_NAME);
            tempConnection.close();

            connection = DriverManager.getConnection(SERVER_URL + DB_NAME , USERNAME , PASSWORD);
            System.out.println("Connected to database: " + DB_NAME);
        }catch (SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
        }
        return connection;
    }
    public static void createTables() {
        Connection connection = createDBConnection();
        try {
            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS departments (" +
                    "DID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "DNAME VARCHAR(100), " +
                    "HOD_NAME VARCHAR(100), " +
                    "BUILDING VARCHAR(50))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS professors (" +
                    "PID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "PNAME VARCHAR(100), " +
                    "EMAIL VARCHAR(100), " +
                    "SALARY DOUBLE, " +
                    "DEPARTMENT_ID INT, " +
                    "FOREIGN KEY (DEPARTMENT_ID) REFERENCES departments(DID))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS courses (" +
                    "CID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "CNAME VARCHAR(100), " +
                    "CREDITS INT, " +
                    "DEPARTMENT_ID INT, " +
                    "FOREIGN KEY (DEPARTMENT_ID) REFERENCES departments(DID))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS students (" +
                    "SID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "SNAME VARCHAR(100), " +
                    "ROLL_NO VARCHAR(20), " +
                    "EMAIL VARCHAR(100), " +
                    "DEPARTMENT_ID INT, " +
                    "FOREIGN KEY (DEPARTMENT_ID) REFERENCES departments(DID))");

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS classrooms (" +
                    "RID INT AUTO_INCREMENT PRIMARY KEY, " +
                    "ROOM_NUMBER VARCHAR(20), " +
                    "CAPACITY INT, " +
                    "DEPARTMENT_ID INT, " +
                    "FOREIGN KEY (DEPARTMENT_ID) REFERENCES departments(DID))");

            System.out.println("All tables created successfully!");
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
