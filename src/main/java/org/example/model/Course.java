package org.example.model;

public class Course {
    private int courseId;
    private String courseName;
    private int  credits;
    private int departmentId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int  departmentId) {
        this.departmentId = departmentId;
    }

    public Course(String courseName, int credits, int departmentId){
        this.courseName = courseName;
        this.credits = credits;
        this.departmentId = departmentId;
    }

}
