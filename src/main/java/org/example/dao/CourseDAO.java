package org.example.dao;

import org.example.model.Course;
import org.example.db.CreateDB;

import java.sql.*;

public class CourseDAO {

    //Add Course in Database
    public static boolean addCourseToDB(Course course){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "INSERT INTO courses(CNAME, CREDITS, DEPARTMENT_ID) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setInt(2, course.getCredits());
            preparedStatement.setInt(3, course.getDepartmentId());
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

    //Delete Course From Database
    public static boolean deleteCourseFromDB(int courseId){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;
        try {
            String query = "DELETE FROM courses WHERE CID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, courseId);
            ps.executeUpdate();
            flag = true;

            Statement checkStatement = connection.createStatement();
            ResultSet countSet = checkStatement.executeQuery("SELECT COUNT(*) FROM courses");
            if (countSet.next() && countSet.getInt(1) == 0) {
                Statement resetStatement = connection.createStatement();
                resetStatement.executeUpdate("ALTER TABLE courses AUTO_INCREMENT = 1");
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
        return flag;
    }

    //Get Course byId
    public static Course getCourseById(int courseId){
        Connection connection = CreateDB.createDBConnection();
        Course course = null;
        try {
            String query = "SELECT * FROM courses WHERE CID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, courseId);
            ResultSet set = ps.executeQuery();
            if (set.next()) {
                course = new Course(set.getString("CNAME"), set.getInt("CREDITS"), set.getInt("DEPARTMENT_ID"));   // ✅
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
        return course;
    }

    //Update course in databse
    public static boolean updateCourseInDB(int courseId, Course updateCourse){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;
        try {
            String query = "UPDATE courses SET CNAME=?, CREDITS=?, DEPARTMENT_ID=? WHERE CID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, updateCourse.getCourseName());
            ps.setInt(2, updateCourse.getCredits());
            ps.setInt(3, updateCourse.getDepartmentId());
            ps.setInt(4, courseId);
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

    //Show all course
    public static void showAllCoursesFromDB(){
        Connection connection = CreateDB.createDBConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM courses");
            while (set.next()) {
                System.out.println();
                System.out.println("ID: " + set.getInt(1));
                System.out.println("NAME: " + set.getString(2));
                System.out.println("CREDITS: " + set.getInt(3));
                System.out.println("DEPARTMENT_ID: " + set.getInt(4));
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

