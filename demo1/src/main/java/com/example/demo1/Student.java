package com.example.demo1;
import java.sql.Date;


public class Student extends fullName
{
    private int id;

    private Date dateOfBirth;
    private String gender;
    private int classroom;

    // Constructors
    public Student() {
    }

    public Student(int id, String name, String surName, Date dateOfBirth, String gender, int classroom) {
        super(name,surName);
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.classroom = classroom;
    }

    //region SettersAndGetters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }


    public void setGender(String gender) {
        this.gender = gender;
    }


    public void setClassroom(int classroom) {
        this.classroom = classroom;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public int getClassroom() {
        return classroom;
    }

    //endregion

}

