package Database;
import Entity.Student;

import javax.rmi.CORBA.Stub;
import java.net.ConnectException;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;


/**
 * Created by Thuan Nguyen on 10/18/2016.
 */
public class Database {

    public void InsertDatabase(String sql)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Student.db");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            statement.executeUpdate(sql);
           // System.out.println("Added Database");
            statement.close();
            connection.commit();
            System.out.println("Added Database");
            connection.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public ArrayList<Student> SelectDatabase()
    {
        ArrayList<Student> listStudent = new ArrayList<Student>();
        Connection connection = null;
        Statement statement = null;
        try
        {

            Class.forName("org.sqlite.JDBC");
            //
            connection = DriverManager.getConnection("jdbc:sqlite:Student.db");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery( "SELECT * FROM STUDENT;" );
            while(resultSet.next())
            {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String sex = resultSet.getString("sex");
                String address = resultSet.getString("address");
                double mathPoint = resultSet.getDouble("mathPoint");
                double physicalPoint = resultSet.getDouble("physicalPoint");
                double chemistryPoint = resultSet.getDouble("chemistryPoint");
                Student student = new Student(id,name,sex,address,mathPoint,physicalPoint,chemistryPoint);
                listStudent.add(student);
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listStudent;
    }
    public void UpdateDatabase(String sql)
    {
        Connection connection = null;
        Statement statement = null;
      try{
          Class.forName("org.sqlite.JDBC");
          connection = DriverManager.getConnection("jdbc:sqlite:Student.db");
          connection.setAutoCommit(false);
          statement = connection.createStatement();
          statement.executeUpdate(sql);
          connection.commit();
          connection.close();
      } catch (ClassNotFoundException e) {
          e.printStackTrace();
      } catch (SQLException e) {
          e.printStackTrace();
      }
    }
    public  void DeleteDatabase(String id )
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Student.db");
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            String sql = " DELETE from STUDENT where ID = " + id;
            statement.executeUpdate(sql);
            System.out.println("Đã xóa");
            connection.commit();
            //ResultSet resultSet = statement.executeQuery("SELECT  *FROM STUDENT");
            statement.close();
            connection.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    }

