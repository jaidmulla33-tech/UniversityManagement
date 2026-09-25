package org.example.model;

public class Classroom {
    private int roomId;
    private String roomNumber;
    private int capacity;
    private int departmentId;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public Classroom(String roomNumber, int capacity, int departmentId){
        this.roomNumber = roomNumber;
        this.capacity = capacity;
        this.departmentId = departmentId;
    }
}
