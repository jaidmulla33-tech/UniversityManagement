package org.example.model;



public class Professor {
    private int professorId;
    private String professorName;
    private String email;
    private double salary;
    private int departmentId;

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
    
    public Professor(String professorName, String email, double salary, int departmentId) {
        this.professorName = professorName;
        this.email = email;
        this.salary = salary;
        this.departmentId = departmentId;
    }
}
