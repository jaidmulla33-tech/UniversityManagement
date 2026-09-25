package org.example.model;

public class Department {
    private int departmentId;
    private String departmentName;
    private String hodName;
    private String building;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getHodName() {
        return hodName;
    }

    public void setHodName(String hodName) {
        this.hodName = hodName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Department(String departmentName, String hodName, String building){
        this.departmentName = departmentName;
        this.hodName = hodName;
        this.building = building;
    }
}
