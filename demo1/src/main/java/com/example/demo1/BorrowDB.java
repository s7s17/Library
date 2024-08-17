package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class BorrowDB {
    static Connection connection = StudentDB.connectDB();
    static PreparedStatement prepared;
    static ResultSet result;
    static Borrow borrow;
    static ObservableList<Borrow> borrows = FXCollections.observableArrayList();
    static int ret;

    //region Borrow Functions

    /*This method takes borrow data from the user and insert it into the database
    using SQL  insert statement and returns the number of rows affected and if there
    was an exception it will return zero.*/
    public static int createBorrow(Borrow borrow)
    {
        try {
            prepared = connection.prepareStatement("INSERT INTO tbl_borrow(borrow_id,student_id,ISBN,tokenDate,broughtDate) values (?,?,?,?,?)");
            prepared.setInt(1,borrow.getBorrow_id());
            prepared.setInt(2,borrow.getStudent_id());
            prepared.setInt(3,borrow.getISBN());
            prepared.setDate(4,borrow.getTokenDate());
            prepared.setDate(5,borrow.getBroughtDate());
            ret = prepared.executeUpdate();
            return ret;        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /*This method takes the id of the borrow that user wants to delete
    and delete it from the database and returns the number of rows affected
    and if there was an exception it will return zero.*/
    public static int DeleteBorrow(int id)
    {
        try
        {
            prepared = connection.prepareStatement("DELETE FROM tbl_borrow WHERE borrow_id=?");
            prepared.setInt(1,id);
            ret = prepared.executeUpdate();
            return ret;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }


    /*This method uses a SQL statement to return the whole data of the borrow table
    from the database and loop on the result set row by row to put it on an object from
     the Borrow class and pass it to ObservableList<Borrow>.*/
    public static ObservableList<Borrow> getAllBorrow() {
        ObservableList<Borrow> borrowList = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tbl_borrow");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Borrow borrow1 = new Borrow();
                borrow1.setISBN(resultSet.getInt("ISBN"));
                borrow1.setStudent_id(resultSet.getInt("student_id"));
                borrow1.setBorrow_id(resultSet.getInt("borrow_id"));
                borrow1.setTokenDate(resultSet.getDate("tokenDate"));
                borrow1.setBroughtDate(resultSet.getDate("broughtDate"));
                borrow1.setStudentName(StudentDB.getStudentById(borrow1.getStudent_id()));
                borrow1.setBookName(BookDB.getBookById(borrow1.getISBN()));
                borrowList.add(borrow1);
            }
            return borrowList;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList(); // Return an empty list on failure
    }

    /*This method uses a SQL statement to return the borrow with the id passed by
    user from the database and loop on this result to put it on an object from the Borrow
     class and pass it to ObservableList<Borrow>.*/
    public static ObservableList<Borrow> getBorrow(int id)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_borrow WHERE borrow_id = ?");
            prepared.setInt(1,id);
            result = prepared.executeQuery();
            if (result.next()) {
                borrow=new Borrow();
                borrow.setISBN(result.getInt("ISBN"));
                borrow.setBorrow_id(result.getInt("borrow_id"));
                borrow.setStudent_id(result.getInt("student_id"));
                borrow.setTokenDate(result.getDate("tokenDate"));
                borrow.setBroughtDate(result.getDate("broughtDate"));
                borrow.setStudentName(StudentDB.getStudentById(borrow.getStudent_id()));
                borrow.setBookName(BookDB.getBookById(borrow.getISBN()));
                borrows.add(borrow);
            }
            return borrows;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };

    /*This is the overloaded version of the above method,This method uses a SQL
     statement to return the list of borrows with the broughtDate passed by user from the
     database and loop on this result row by row to put it on an object from the Borrow
     class and pass it to ObservableList<Borrow> at the end of the loop.*/
    public static ObservableList<Borrow> getBorrow(Date broughtDate)
    {
        ObservableList<Borrow> borrowList = FXCollections.observableArrayList();
        try {
            String sql = "SELECT * FROM tbl_borrow WHERE broughtDate = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setDate(1, broughtDate);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Borrow borrow1 = new Borrow();
                        borrow1.setISBN(resultSet.getInt("ISBN"));
                        borrow1.setBorrow_id(resultSet.getInt("borrow_id"));
                        borrow1.setStudent_id(resultSet.getInt("student_id"));
                        borrow1.setTokenDate(resultSet.getDate("tokenDate"));
                        borrow1.setBroughtDate(resultSet.getDate("broughtDate"));
                        borrow1.setStudentName(StudentDB.getStudentById(borrow1.getStudent_id()));
                        borrow1.setBookName(BookDB.getBookById(borrow1.getISBN()));
                        borrowList.add(borrow1);
                    }
                }
            }
            return borrowList;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };



    //endregion
}
