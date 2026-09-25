package org.example.dao;

import org.example.model.Department;
import org.example.db.CreateDB;

import java.sql.*;

public class DepartmentDAO {

    //Add Department To Database
    public static boolean addDepartmentToDB(Department department){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try {
            String query = "INSERT INTO departments(DNAME, HOD_NAME, BUILDING) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, department.getDepartmentName());
            preparedStatement.setString(2, department.getHodName());
            preparedStatement.setString(3, department.getBuilding());
            preparedStatement.executeUpdate();
            flag = true;
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

    //Delete Department From The Database
    public static boolean deleteDepartmentFromDB(int departmentId){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "DELETE FROM departments WHERE DID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            preparedStatement.executeUpdate();
            flag = true;

            Statement checkStatement = connection.createStatement();
            ResultSet countSet = checkStatement.executeQuery("SELECT COUNT(*) FROM departments");
            if(countSet.next() && countSet.getInt(1) == 0){
                Statement resetStatement = connection.createStatement();
                resetStatement.executeUpdate("ALTER TABLE departments AUTO_INCREMENT = 1");
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

    //Show All Departments From Database
    public static void showAllDepartmentsFromDB(){
        Connection connection = CreateDB.createDBConnection();

        try{
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM departments";
            ResultSet set = statement.executeQuery(query);
            while (set.next()){
                System.out.println();
                System.out.println("ID: " + set.getInt(1));
                System.out.println("NAME: " + set.getString(2));
                System.out.println("HOD: " + set.getString(3));
                System.out.println("BUILDING: " + set.getString(4));
                for (int i = 0; i < 25; i++) System.out.print("+");
                System.out.println();
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
    }

    //Get Department By Id
    public  static Department getDepartmentById(int departmentId){
        Connection connection = CreateDB.createDBConnection();
        Department department = null;

        try{
            String query = "SELECT * FROM departments WHERE DID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, departmentId);
            ResultSet set = preparedStatement.executeQuery();
            if(set.next()){
                String name = set.getString("DNAME");
                String hod = set.getString("HOD_NAME");
                String building = set.getString("BUILDING");
                department = new Department(name, hod, building);
            }
        }catch (SQLException e){
            System.out.println("Oops, Try agian!");
            e.printStackTrace();
        }finally {
            try{
                if(connection != null && !connection.isClosed()) connection.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return department;
    }

    //Update Department In Database
    public static boolean updateDepartmentInDB(int departmentId, Department updatedDepartment){
        Connection connection = CreateDB.createDBConnection();
        boolean flag = false;

        try{
            String query = "UPDATE departments SET DNAME=?, HOD_NAME=?, BUILDING=? WHERE DID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, updatedDepartment.getDepartmentName());
            preparedStatement.setString(2, updatedDepartment.getHodName());
            preparedStatement.setString(3, updatedDepartment.getBuilding());
            preparedStatement.setInt(4, departmentId);
            int rowsEffected = preparedStatement.executeUpdate();
            if (rowsEffected > 0){
                flag = true;
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
}