package org.example.model;

public class Student {
    private int studentId;
    private String studentName;
    private String rollNo;
    private String email;
    private int departmentId;


    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int  getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Student(String studentName, String rollNo, String email, int departmentId){
        this.studentName = studentName;
        this.rollNo = rollNo;
        this.email = email;
        this.departmentId = departmentId;
    }
}
