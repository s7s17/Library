package com.example.demo1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;


public class StudentDB {
   static Connection connection = connectDB();
   static PreparedStatement prepared;
   static ResultSet result;
   static Student student;
   static ObservableList<Student> students = FXCollections.observableArrayList();
    static author author1;
    static ObservableList<author> authors = FXCollections.observableArrayList();
    static book Book;
    static ObservableList<book> books = FXCollections.observableArrayList();
    static Borrow borrow;
    static ObservableList<Borrow> borrows = FXCollections.observableArrayList();
   static int ret;
    public static Connection connectDB(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Connecting.....");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1/demo","root","102030");
            System.out.println("DatabaseConnected");
            return connection;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    };
    //region StudentFunctions

    /*This method takes student  data from the user and insert it into the database using
     SQL  insert statement and returns the number of rows affected and if there was an
     exception it will return zero.*/
    public static int createStudent(Student student)
    {
        try {
            prepared = connection.prepareStatement("INSERT INTO tbl_student(student_id,name,surName,birthDate,gender,class) values (?,?,?,?,?,?)");
            prepared.setInt(1,student.getId());
            prepared.setString(2,student.getName());
            prepared.setString(3,student.getSurName());
            prepared.setDate(4,student.getDateOfBirth());
            prepared.setString(5,student.getGender());
            prepared.setInt(6,student.getClassroom());
            ret = prepared.executeUpdate();
            return ret;        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return 0;
    }

    /*This method takes the id of the student  that user wants to delete and delete
    it from the database and returns the number of rows affected and if there was an
    exception it will return zero.*/
    public static int DeleteStudent(int id)
    {
        try
        {
            prepared = connection.prepareStatement("DELETE FROM tbl_student WHERE student_id=?");
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

    /*This method uses a SQL statement to return the whole data of the student
    table from the database and loop on the result set row by row to put it on an
     object from the student  class and pass it to ObservableList<Student>.*/
    public static ObservableList<Student> getAllStudents()
    {
        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_student");
            result= prepared.executeQuery();
            while (result.next())
            {
                student = new Student();
                student.setId(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setSurName(result.getString("surName"));
                student.setDateOfBirth(result.getDate("birthDate"));
                student.setGender(result.getString("gender"));
                student.setClassroom(result.getInt("class"));
                students.add(student);
            }
            return students;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return FXCollections.observableArrayList();
    }

    /*This method uses a SQL statement to return the student
    with the id passed by user from the database and loop on this
    result to put it on an object from the student  class and pass it to ObservableList<Book>.*/
    public static ObservableList<Student> getStudent(int id)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_student WHERE student_id = ?");
            prepared.setInt(1,id);
            result = prepared.executeQuery();
            if (result.next()) {
                student = new Student();
                student.setId(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setSurName(result.getString("surName"));
                student.setDateOfBirth(result.getDate("birthDate"));
                student.setGender(result.getString("gender"));
                student.setClassroom(result.getInt("class"));
                students.add(student);
                return students;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

            return FXCollections.observableArrayList();
    };

    /*This is the overloaded version of the above method,This method uses a SQL statement
     to return the list of students  with the name of the student passed by user from the
     database and loop on this result row by row to put it on an object from the student
     class and pass it to ObservableList<Student> at the end of the loop.*/
    public static ObservableList<Student> getStudent(String Name)
    {

        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_student WHERE name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next())
            {
                student= new Student();
                student.setId(result.getInt("student_id"));
                student.setName(result.getString("name"));
                student.setSurName(result.getString("surName"));
                student.setDateOfBirth(result.getDate("birthDate"));
                student.setGender(result.getString("gender"));
                student.setClassroom(result.getInt("class"));
                students.add(student);
                return students;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return FXCollections.observableArrayList();
    };
    //endregion


    //region Additional Functions

    /*This method takes the Name of the type from the user and uses SQL statement to
    return the id of the type  with that name from the database. it’s used for showing
    the names of the type  in a choice box when creating a book instead of selecting the
    id of the type  to make the project user-friendly and easy to use.*/
    public static int typeByName(String Name)
    {
        try {
            prepared = connection.prepareStatement("SELECT * FROM tbl_type WHERE Name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next()) {
                int id =result.getInt("type_id");
                return id;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }

    /*This method takes the Name of the student  from the user and uses SQL statement
    to return the id of the student  with that name from the database. it’s used for showing
     the names of the student  in a choice box when creating a borrow instead of selecting
      the id of the student  to make the project user-friendly and easy to use.*/
    public static int studentByName(String Name)
    {
        try {
            prepared = connection.prepareStatement("SELECT student_id FROM tbl_student WHERE name = ?");
            prepared.setString(1,Name);
            result = prepared.executeQuery();
            if (result.next()) {
                int id =result.getInt("student_id");
                return id;
            }
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }

        return 0;
    }

    /*This method returns all the names of types  in the database and it’s used
    to show them I a choice box for the user to choose from them when creating a book.*/
    public static ResultSet typeNames()
    {
        try {
            prepared = connection.prepareStatement("SELECT Name FROM tbl_type");
            result = prepared.executeQuery();
            return result;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }

    /*This method returns all the names of students  in the database and it’s used to show them In
     a choice box for the user to choose from them when creating a borrow.*/
    public static ResultSet studentNames()
    {
        try {
            prepared = connection.prepareStatement("SELECT name FROM tbl_student");
            result = prepared.executeQuery();
            return result;
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return null;
    }


    /*This method takes the id of the student from the user and uses SQL
     statement to return the name of the student  with that id from the database
     it’s used for showing the names of the student  in the borrow table instead of
      the id’s to make the project user-friendly and easy to use.*/
    public static String getStudentById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tbl_student WHERE student_id = ?")) {
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

    /*This method takes the id of the Type  from the user and uses SQL statement to return the name of the types  with that id from the database it’s used for showing the names of the types  in the book
    table instead of the id’s to make the project user-friendly and easy to use.*/
    public static String getTypeById(int id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM tbl_type WHERE type_id = ?")) {
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

    //endregion





}
