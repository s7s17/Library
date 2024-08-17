package com.example.demo1;

import javafx.scene.chart.PieChart;

import java.sql.Date;

public class Borrow {
    private int borrow_id;
    private int student_id;
    private String studentName;
    private int ISBN;
    private String bookName;

    public String getStudentName() {
        return studentName;
    }

    public String getBookName() {
        return bookName;
    }

    private Date tokenDate;
    private Date broughtDate;
    public Borrow(){}

    public Borrow(int borrow_id, int student_id, int ISBN, Date tokenDate, Date broughtDate) {
        this.borrow_id = borrow_id;
        this.student_id = student_id;
        this.ISBN = ISBN;
        this.tokenDate = tokenDate;
        this.broughtDate = broughtDate;
    }

    //region SettersAndGetters
    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }


    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Date getTokenDate() {
        return tokenDate;
    }

    public void setTokenDate(Date tokenDate) {
        this.tokenDate = tokenDate;
    }

    public Date getBroughtDate() {
        return broughtDate;
    }

    public void setBroughtDate(Date broughtDate) {
        this.broughtDate = broughtDate;
    }

    //endregion

}
