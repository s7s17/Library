package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDB {
    static Connection connection = StudentDB.connectDB();
    static PreparedStatement prepared;
    static ResultSet result;
    static book Book;
    static ObservableList<book> books = FXCollections.observableArrayList();
    static int ret;

    //region BookFunctions

    /*This method takes book data from the user and insert it into the database using
    SQL  insert statement and returns the number of rows affected and if there was an exception
    it will return zero.*/
    public static int createBook(book book1)
    {
        try {
            prepared = connection.prepareStatement("INSERT INTO tbl_book(ISBN,name,pageCount,author_id,type_id) values (?,?,?,?,?)");
            prepared.setInt(1,book1.getISBN());
            prepared.setString(2,book1.getName());
            prepared.setInt(3,book1.getPageCount());
            prepared.setInt(4,book1.getAuthor_id());
            prepared.setInt(5,book1.getType_id());
            ret = prepared.executeUpdate();
            return ret;        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /*This method takes the id of the book that user wants to delete
    and delete it from the database and returns the number of rows affected and if there
    was an exception it will return zero.*/
    public static int DeleteBook(int id)
    {
        try
        {
            prepared = connection.prepareStatement("DELETE FROM tbl_book WHERE ISBN=?");
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

    /*This method uses a SQL statement to return the whole data of the book table
    from the database and loop on the result set row by row to put it on an object
    from the Book class and pass it to ObservableList<Book>.*/
    public static ObservableList<book> getAllBooks() {
        ObservableList<book> books = FXCollections.observableArrayList();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tbl_book");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                book book1 = new book();
                book1.setISBN(resultSet.getInt("ISBN"));
                book1.setName(resultSet.getString("name"));
                book1.setPageCount(resultSet.getInt("pageCount"));
                book1.setAuthor_id(resultSet.getInt("author_id"));
                book1.setType_id(resultSet.getInt("type_id"));
                book1.setTypeName(StudentDB.getTypeById(book1.getType_id()));
                book1.setAuthorName(AuthorDB.getAuthorById(book1.getAuthor_id()));
                books.add(book1);
            }
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList(); // Return an empty list on failure
    }

    /*This method returns all the names of books in the database
    and it’s used to show them I a choice box for the user to choose from them when creating a borrow.*/
    public static ResultSet bookNames()
    {
        try {
            prepared = connection.prepareStatement("SELECT name FROM tbl_book");
            result = prepared.executeQuery();
            return result;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /*This method takes the Name of the book  from the user and uses SQL statement
    to return the id(ISBN) of the book with that name from the database. it’s used for
    showing the names of the book in a choice box when creating a borrow instead of selecting
     the id of the book to make the project user friendly and easy to use.*/
    public static int bookByName(String Name)
    {
        try {
            prepared = connection.prepareStatement("SELECT ISBN FROM tbl_book WHERE name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next()) {
                int id =result.getInt("ISBN");
                return id;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }


    /*This method takes the id of the book (ISBN) from the user and uses
    SQL statement to return the name of the book with that id from the database
    it’s used for showing the names of the book in the borrow table instead of the id’s
    to make the project user friendly and easy to use.*/
    public static String getBookById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM tbl_book WHERE ISBN = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /*This method uses a SQL statement to return the book with the id passed
    by user from the database and loop on this result to put it on an object from
    the Book class and pass it to ObservableList<Book>.*/
    public static ObservableList<book> getBook(int id)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_book WHERE ISBN = ?");
            prepared.setInt(1,id);
            result = prepared.executeQuery();
            if (result.next()) {
                Book = new book();
                Book.setISBN(result.getInt("ISBN"));
                Book.setName(result.getString("name"));
                Book.setPageCount(result.getInt("pageCount"));
                Book.setAuthor_id(result.getInt("author_id"));
                Book.setType_id(result.getInt("type_id"));
                Book.setAuthorName(AuthorDB.getAuthorById(Book.getAuthor_id()));
                Book.setTypeName(StudentDB.getTypeById(Book.getType_id()));
                books.add(Book);
            }
            return books;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };

    /*This is the overloaded version of the above method,This method
    uses a SQL statement to return the list of books with the name of the book passed
     by user from the database and loop on this result row by row to put it on an
      object from the Book class and pass it to ObservableList<Book> at the end of the loop.*/
    public static ObservableList<book> getBook(String Name)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_book WHERE name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next()) {
                Book = new book();
                Book.setISBN(result.getInt("ISBN"));
                Book.setName(result.getString("name"));
                Book.setPageCount(result.getInt("pageCount"));
                Book.setAuthor_id(result.getInt("author_id"));
                Book.setType_id(result.getInt("type_id"));
                Book.setAuthorName(AuthorDB.getAuthorById(Book.getAuthor_id()));
                Book.setTypeName(StudentDB.getTypeById(Book.getType_id()));
                books.add(Book);
                return books;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };

    //endregion
}
