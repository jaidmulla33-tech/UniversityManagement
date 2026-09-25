package org.example.dao;

import org.example.model.Professor;
import org.example.db.CreateDB;

import java.sql.*;

public class ProfessorDAO {

    public static boolean addProfessorToDB(Professor professor){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "INSERT INTO professors(PNAME, EMAIL, SALARY, DEPARTMENT_ID) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, professor.getProfessorName());
            preparedStatement.setString(2, professor.getEmail());
            preparedStatement.setDouble(3, professor.getSalary());
            preparedStatement.setInt(4, professor.getDepartmentId());
            preparedStatement.executeUpdate();
            flag = true;
        }catch (SQLException e){
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        }finally {
            try {
                if(connection != null && !connection.isClosed()) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static boolean deleteProfessorFromDB(int professorId){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "DELETE FROM professors WHERE PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            preparedStatement.executeUpdate();
            flag = true;

            Statement checkStatement = connection.createStatement();
            ResultSet countSet = checkStatement.executeQuery("SELECT COUNT(*) FROM professors");
            if(countSet.next() && countSet.getInt(1) == 0){
                Statement resetStatement = connection.createStatement();
                resetStatement.executeUpdate("ALTER TABLE professors AUTO_INCREMENT = 1");
            }
        }catch (SQLException e){
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        }finally {
            try {
                if (connection != null && !connection.isClosed()) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return flag;
    }

    public static Professor getProfessorById(int professorId){
        Connection connection = CreateDB.createDBConnection();
        Professor professor = null;

        try{
            String query = "SELECT * FROM professors WHERE PID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, professorId);
            ResultSet set = preparedStatement.executeQuery();

            if(set.next()){
                professor = new Professor(set.getString("PNAME"), set.getString("EMAIL"),
                        set.getDouble("SALARY"), set.getInt("DEPARTMENT_ID"));
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
        return professor;
    }

    public static boolean updateProfessorInDB(int professorId, Professor updateProfessor){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;
        try {
            String query = "UPDATE professors SET PNAME=?, EMAIL=?, SALARY=?, DEPARTMENT_ID=? WHERE PID=?";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, updateProfessor.getProfessorName());
            ps.setString(2, updateProfessor.getEmail());
            ps.setDouble(3, updateProfessor.getSalary());
            ps.setInt(4, updateProfessor.getDepartmentId());
            ps.setInt(5, professorId);
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

    //******** Show All Professor in Database *********//
    public static void showAllProfessorsFromDB(){
        Connection connection = CreateDB.createDBConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM professors");
            while (set.next()) {
                System.out.println();
                System.out.println("ID: " + set.getInt(1));
                System.out.println("NAME: " + set.getString(2));
                System.out.println("EMAIL: " + set.getString(3));
                System.out.println("SALARY: " + set.getDouble(4));
                System.out.println("DEPARTMENT_ID: " + set.getInt(5));
                for (int i = 0; i < 25; i++) System.out.print("+");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Oops, Try again!");
            e.printStackTrace();
        } finally {
            try { if (connection != null && !connection.isClosed()) connection.close(); } catch (Exception e) { e.printStackTrace(); }
        }
    }
}