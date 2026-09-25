package org.example.dao;


import org.example.model.Classroom;
import org.example.db.CreateDB;

import java.sql.*;

public class ClassroomDAO {
    //Add into classroom
    public static boolean addClassroomToDB(Classroom classroom) {
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try {
            String query = "INSERT INTO classrooms(ROOM_NUMBER, CAPACITY, DEPARTMENT_ID) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, classroom.getRoomNumber());
            preparedStatement.setInt(2, classroom.getCapacity());
            preparedStatement.setInt(3, classroom.getDepartmentId());
            preparedStatement.executeUpdate();
            flag = true;
        } catch (Exception e) {
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

    //Delete classroom from Database
    public static boolean deleteClassroomFromDB(int roomId) {
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try {
            String query = "DELETE FROM classrooms WHERE RID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            preparedStatement.executeUpdate();
            flag = true;

            Statement checkStatement = connection.createStatement();
            ResultSet countSet = checkStatement.executeQuery("SELECT COUNT(*) FROM classrooms");
            if (countSet.next() && countSet.getInt(1) == 0) {
                Statement resetStatement = connection.createStatement();
                resetStatement.executeUpdate("ALTER TABLE classrooms AUTO_INCREMENT = 1");
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

    //Get classroom by id
    public static Classroom getClassroomById(int roomId) {
        Connection connection = CreateDB.createDBConnection();
        Classroom classroom = null;

        try {
            String query = "SELECT * FROM classrooms WHERE RID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, roomId);
            ResultSet set = preparedStatement.executeQuery();
            if (set.next()) {
                classroom = new Classroom(set.getString("ROOM_NUMBER"), set.getInt("CAPACITY"), set.getInt("DEPARTMENT_ID"));
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
        return classroom;
    }

    //Udate classroom by id
    public static boolean updateClassroomInDB(int roomId, Classroom updateClassroom) {
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try {
            String query = "UPDATE classrooms SET ROOM_NUMBER=?, CAPACITY=?, DEPARTMENT_ID=? WHERE RID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, updateClassroom.getRoomNumber());
            ps.setInt(2, updateClassroom.getCapacity());
            ps.setInt(3, updateClassroom.getDepartmentId());
            ps.setInt(4, roomId);
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

    //show all classroom from Database
    public static void showAllClassroomsFromDB() {
        Connection connection = CreateDB.createDBConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM classrooms");
            while (set.next()) {
                System.out.println();
                System.out.println("ID: " + set.getInt(1));
                System.out.println("ROOM_NUMBER: " + set.getString(2));
                System.out.println("CAPACITY: " + set.getInt(3));
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
