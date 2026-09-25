package org.example.dao;

import org.example.model.Student;
import org.example.db.CreateDB;


import java.sql.*;

public class StudentDAO {

    //Add Student To database
    public static boolean addStudentToDB(Student student){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "INSERT INTO students(SNAME, ROLL_NO, EMAIL, DEPARTMENT_ID) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getStudentName());
            preparedStatement.setString(2, student.getRollNo());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getDepartmentId());
            preparedStatement.executeUpdate();
            flag = true;
        }catch (SQLException e){
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        }finally {
            try {
                if (connection != null && !connection.isClosed()) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    //Delete student from database
    public static boolean deleteStudentFromDB(int studentId){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "DELETE FROM students WHERE SID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            preparedStatement.executeUpdate();
            flag = true;

            Statement checkStatement = connection.createStatement();
            ResultSet countSet = checkStatement.executeQuery("SELECT COUNT (*) FROM students");
            if(countSet.next() && countSet.getInt(1) == 0){
                Statement resetStatement = connection.createStatement();
                resetStatement.executeUpdate("ALTER TABLE students AUTO_INCREMENT = 1");
            }
        }catch (SQLException e){
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        }finally {
            try{
                if(connection != null && !connection.isClosed()) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    //Get Student by id
    public static Student getStudentById(int studentId){
        Connection connection = CreateDB.createDBConnection();
        Student student = null;

        try{
            String query = "SELECT * FROM students WHERE SID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            ResultSet set = preparedStatement.executeQuery();
            if(set.next()){
                student = new Student(set.getString("SNAME"), set.getString("ROLL_NO"),
                        set.getString("EMAIL"), set.getInt("DEPARTMENT_ID"));
            }
        }catch (SQLException e){
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        }finally {
            try{
                if(connection != null && !connection.isClosed()) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return student;
    }

    //update student from database
    public static boolean updateStudentInDB(int studentId, Student updateStudent){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;
        try {
            String query = "UPDATE students SET SNAME=?, ROLL_NO=?, EMAIL=?, DEPARTMENT_ID=? WHERE SID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, updateStudent.getStudentName());
            ps.setString(2, updateStudent.getRollNo());
            ps.setString(3, updateStudent.getEmail());
            ps.setInt(4, updateStudent.getDepartmentId());
            ps.setInt(5, studentId);
            int rows = ps.executeUpdate();
            if (rows > 0) flag = true;
        } catch (SQLException e) {
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

    //show all student
    public static void showAllStudentsFromDB(){
        Connection connection = CreateDB.createDBConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM students");
            while (set.next()) {
                System.out.println();
                System.out.println("ID: " + set.getInt(1));
                System.out.println("NAME: " + set.getString(2));
                System.out.println("ROLL_NO: " + set.getString(3));
                System.out.println("EMAIL: " + set.getString(4));
                System.out.println("DEPARTMENT_ID: " + set.getInt(5));
                for (int i = 0; i < 25; i++) System.out.print("+");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        } finally {
            try {
                if (connection != null && !connection.isClosed()) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

