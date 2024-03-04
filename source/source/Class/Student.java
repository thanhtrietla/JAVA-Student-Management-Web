/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Student {
    private int id;
    private String name;
    private int grade;
    private Date birthday;
    private String address;
    private String notes;

    public Student() {
        this.id = 0;
        this.name = null;
        this.grade = 0;
        this.birthday = null;
        this.address = null;
        this.notes = null;
    }

    
    public Student(int id, String name, int grade, Date birthday, String address, String notes) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    
}
