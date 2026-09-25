package org.example;

import org.example.db.CreateDB;
import org.example.dao.*;
import org.example.model.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws IOException {
        Connection connection = CreateDB.createDBConnection();
        if (connection != null) {
            CreateDB.createTables();
        } else {
            System.out.println("Failed to connect!");
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to University Management System");

        while (true) {
            System.out.println();
            System.out.println("Select Entity: 1-Department 2-Professor 3-Course 4-Student 5-Classroom 6-EXIT");
            int entity = Integer.parseInt(br.readLine());
            if (entity == 6) { System.out.println("Exiting..."); return; }

            System.out.println("Select Operation: 1-ADD 2-DELETE 3-UPDATE 4-DISPLAY");
            int op = Integer.parseInt(br.readLine());

            switch (entity) {
                case 1: handleDepartment(op, br); break;
                case 2: handleProfessor(op, br); break;
                case 3: handleCourse(op, br); break;
                case 4: handleStudent(op, br); break;
                case 5: handleClassroom(op, br); break;
                default: System.out.println("Invalid entity!");
            }
        }
    }

    static void handleDepartment(int op, BufferedReader br) throws IOException {
        switch (op) {
            case 1:
                System.out.println("Name:"); String name = br.readLine();
                System.out.println("HOD:"); String hod = br.readLine();
                System.out.println("Building:"); String building = br.readLine();
                System.out.println(DepartmentDAO.addDepartmentToDB(new Department(name, hod, building)) ? "Added!" : "Failed!");
                break;
            case 2:
                System.out.println("ID to delete:"); int did = Integer.parseInt(br.readLine());
                System.out.println(DepartmentDAO.deleteDepartmentFromDB(did) ? "Deleted!" : "Failed!");
                break;
            case 3:
                System.out.println("ID to update:"); int uid = Integer.parseInt(br.readLine());
                Department existing = DepartmentDAO.getDepartmentById(uid);
                if (existing != null) {
                    System.out.println("New Name (blank=keep):"); String n = br.readLine();
                    System.out.println("New HOD (blank=keep):"); String h = br.readLine();
                    System.out.println("New Building (blank=keep):"); String b = br.readLine();
                    if (n.isEmpty()) n = existing.getDepartmentName();
                    if (h.isEmpty()) h = existing.getHodName();
                    if (b.isEmpty()) b = existing.getBuilding();
                    System.out.println(DepartmentDAO.updateDepartmentInDB(uid, new Department(n, h, b)) ? "Updated!" : "Failed!");
                } else System.out.println("Not found!");
                break;
            case 4:
                DepartmentDAO.showAllDepartmentsFromDB();
                break;
        }
    }

    static void handleProfessor(int op, BufferedReader br) throws IOException {
        switch (op) {
            case 1:
                System.out.println("Name:"); String name = br.readLine();
                System.out.println("Email:"); String email = br.readLine();
                System.out.println("Salary:"); double salary = Double.parseDouble(br.readLine());
                System.out.println("Department ID:"); int deptId = Integer.parseInt(br.readLine());
                System.out.println(ProfessorDAO.addProfessorToDB(new Professor(name, email, salary, deptId)) ? "Added!" : "Failed!");
                break;
            case 2:
                System.out.println("ID to delete:"); int pid = Integer.parseInt(br.readLine());
                System.out.println(ProfessorDAO.deleteProfessorFromDB(pid) ? "Deleted!" : "Failed!");
                break;
            case 3:
                System.out.println("ID to update:"); int uid = Integer.parseInt(br.readLine());
                Professor existing = ProfessorDAO.getProfessorById(uid);
                if (existing != null) {
                    System.out.println("New Name (blank=keep):"); String n = br.readLine();
                    System.out.println("New Email (blank=keep):"); String e = br.readLine();
                    System.out.println("New Salary (blank=keep):"); String s = br.readLine();
                    System.out.println("New Department ID (blank=keep):"); String d = br.readLine();
                    if (n.isEmpty()) n = existing.getProfessorName();
                    if (e.isEmpty()) e = existing.getEmail();
                    double sal = s.isEmpty() ? existing.getSalary() : Double.parseDouble(s);
                    int dept = d.isEmpty() ? existing.getDepartmentId() : Integer.parseInt(d);
                    System.out.println(ProfessorDAO.updateProfessorInDB(uid, new Professor(n, e, sal, dept)) ? "Updated!" : "Failed!");
                } else System.out.println("Not found!");
                break;
            case 4:
                ProfessorDAO.showAllProfessorsFromDB();
                break;
        }
    }

    static void handleCourse(int op, BufferedReader br) throws IOException {
        switch (op) {
            case 1:
                System.out.println("Name:"); String name = br.readLine();
                System.out.println("Credits:"); int credits = Integer.parseInt(br.readLine());
                System.out.println("Department ID:"); int deptId = Integer.parseInt(br.readLine());
                System.out.println(CourseDAO.addCourseToDB(new Course(name, credits, deptId)) ? "Added!" : "Failed!");
                break;
            case 2:
                System.out.println("ID to delete:"); int cid = Integer.parseInt(br.readLine());
                System.out.println(CourseDAO.deleteCourseFromDB(cid) ? "Deleted!" : "Failed!");
                break;
            case 3:
                System.out.println("ID to update:"); int uid = Integer.parseInt(br.readLine());
                Course existing = CourseDAO.getCourseById(uid);
                if (existing != null) {
                    System.out.println("New Name (blank=keep):"); String n = br.readLine();
                    System.out.println("New Credits (blank=keep):"); String cr = br.readLine();
                    System.out.println("New Department ID (blank=keep):"); String d = br.readLine();
                    if (n.isEmpty()) n = existing.getCourseName();
                    int newcredits = cr.isEmpty() ? existing.getCredits() : Integer.parseInt(cr);
                    int dept = d.isEmpty() ? existing.getDepartmentId() : Integer.parseInt(d);
                    System.out.println(CourseDAO.updateCourseInDB(uid, new Course(n, newcredits, dept)) ? "Updated!" : "Failed!");
                } else System.out.println("Not found!");
                break;
            case 4:
                CourseDAO.showAllCoursesFromDB();
                break;
        }
    }

    static void handleStudent(int op, BufferedReader br) throws IOException {
        switch (op) {
            case 1:
                System.out.println("Name:"); String name = br.readLine();
                System.out.println("Roll No:"); String roll = br.readLine();
                System.out.println("Email:"); String email = br.readLine();
                System.out.println("Department ID:"); int deptId = Integer.parseInt(br.readLine());
                System.out.println(StudentDAO.addStudentToDB(new Student(name, roll, email, deptId)) ? "Added!" : "Failed!");
                break;
            case 2:
                System.out.println("ID to delete:"); int sid = Integer.parseInt(br.readLine());
                System.out.println(StudentDAO.deleteStudentFromDB(sid) ? "Deleted!" : "Failed!");
                break;
            case 3:
                System.out.println("ID to update:"); int uid = Integer.parseInt(br.readLine());
                Student existing = StudentDAO.getStudentById(uid);
                if (existing != null) {
                    System.out.println("New Name (blank=keep):"); String n = br.readLine();
                    System.out.println("New Roll No (blank=keep):"); String r = br.readLine();
                    System.out.println("New Email (blank=keep):"); String e = br.readLine();
                    System.out.println("New Department ID (blank=keep):"); String d = br.readLine();
                    if (n.isEmpty()) n = existing.getStudentName();
                    if (r.isEmpty()) r = existing.getRollNo();
                    if (e.isEmpty()) e = existing.getEmail();
                    int dept = d.isEmpty() ? existing.getDepartmentId() : Integer.parseInt(d);
                    System.out.println(StudentDAO.updateStudentInDB(uid, new Student(n, r, e, dept)) ? "Updated!" : "Failed!");
                } else System.out.println("Not found!");
                break;
            case 4:
                StudentDAO.showAllStudentsFromDB();
                break;
        }
    }

    static void handleClassroom(int op, BufferedReader br) throws IOException {
        switch (op) {
            case 1:
                System.out.println("Room Number:"); String room = br.readLine();
                System.out.println("Capacity:"); int cap = Integer.parseInt(br.readLine());
                System.out.println("Department ID:"); int deptId = Integer.parseInt(br.readLine());
                System.out.println(ClassroomDAO.addClassroomToDB(new Classroom(room, cap, deptId)) ? "Added!" : "Failed!");
                break;
            case 2:
                System.out.println("ID to delete:"); int rid = Integer.parseInt(br.readLine());
                System.out.println(ClassroomDAO.deleteClassroomFromDB(rid) ? "Deleted!" : "Failed!");
                break;
            case 3:
                System.out.println("ID to update:"); int uid = Integer.parseInt(br.readLine());
                Classroom existing = ClassroomDAO.getClassroomById(uid);
                if (existing != null) {
                    System.out.println("New Room Number (blank=keep):"); String r = br.readLine();
                    System.out.println("New Capacity (blank=keep):"); String c = br.readLine();
                    System.out.println("New Department ID (blank=keep):"); String d = br.readLine();
                    if (r.isEmpty()) r = existing.getRoomNumber();
                    int newcap = c.isEmpty() ? existing.getCapacity() : Integer.parseInt(c);
                    int dept = d.isEmpty() ? existing.getDepartmentId() : Integer.parseInt(d);
                    System.out.println(ClassroomDAO.updateClassroomInDB(uid, new Classroom(r, newcap, dept)) ? "Updated!" : "Failed!");
                } else System.out.println("Not found!");
                break;
            case 4:
                ClassroomDAO.showAllClassroomsFromDB();
                break;
        }
    }
}