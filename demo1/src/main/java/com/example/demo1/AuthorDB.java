package com.example.demo1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.example.demo1.StudentDB.connectDB;

public class AuthorDB {
    static Connection connection = connectDB();
    static PreparedStatement prepared;
    static ResultSet result;
    static author author1;
    static ObservableList<author> authors = FXCollections.observableArrayList();
    static int ret;


    //region AuthorFunctions

    /* This method takes author  data from the user and insert it into the
    database using SQL  insert statement and returns the number of rows affected
    and if there was an exception it will return zero. */
    public static int createAuthor(author auth)
    {
        try {
            prepared = connection.prepareStatement("INSERT INTO tbl_author(author_id,Name,surName) values (?,?,?)");
            prepared.setInt(1,auth.getId());
            prepared.setString(2,auth.getName());
            prepared.setString(3,auth.getSurName());
            ret = prepared.executeUpdate();
            return ret;        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /* This method takes the id of the author  that user wants to delete
    and delete it from the database
    and returns the number of rows affected and if there was an exception it will return zero. */
    public static int DeleteAuthor(int id)
    {
        try
        {
            prepared = connection.prepareStatement("DELETE FROM tbl_author WHERE author_id=?");
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

    /* This method takes the id of the Author from the user and uses SQL statement
    to return the name of the author  with that id from the database it’s used for
     showing the names of the author  in the book  table instead of the id’s
      to make the project user-friendly and easy to use. */
    public static String getAuthorById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tbl_author WHERE author_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("Name");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /* This method uses a SQL statement to return the whole data of the author table
     from the database and loop on the result set row by row to put it on an object from
      the Author  class and pass it to ObservableList<Author>. */
    public static ObservableList<author> getAllAuthor()
    {
        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_author");
            result= prepared.executeQuery();
            while (result.next())
            {
                author1 = new author();
                author1.setId(result.getInt("author_id"));
                author1.setName(result.getString("Name"));
                author1.setSurName(result.getString("surName"));

                authors.add(author1);
            }
            return authors;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }
/*
    This method uses a SQL statement to return the author with the id passed by user from
     the database and loop on this result to put it on an object from the Author  class
     and pass it to ObservableList<Author>.
*/
    public static ObservableList<author> getAuthor(int id)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_author WHERE author_id = ?");
            prepared.setInt(1,id);
            result = prepared.executeQuery();
            if (result.next()) {
                author1 = new author();
                author1.setId(result.getInt("author_id"));
                author1.setName(result.getString("Name"));
                author1.setSurName(result.getString("surName"));
                authors.add(author1);
                return authors;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };

    /* This is the overloaded version of the above method,
    This method uses a SQL statement to return the list of authors  with the name of the author
    passed by user from the database and loop on this result row by row to put it
    on an object from the Author  class and pass it to ObservableList<Author> at the end of the loop. */
    public static ObservableList<author> getAuthor(String Name)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_author WHERE Name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next()) {
                author1 = new author();
                author1.setId(result.getInt("author_id"));
                author1.setName(result.getString("Name"));
                author1.setSurName(result.getString("surName"));
                authors.add(author1);
                return authors;
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
