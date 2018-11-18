package com.timbuchalka.testdb;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        //For earlier JDBC versions (pre 4.0) we needed to register and load JDBC files
        //Class.forName("org.sql.JDBC");

        //Another way to establish connection is to use data source objects but this is more complicated
        //(for actual projects), using Connection as simple example

        //This is "proper" way to establish connection in JDBC - this way resources will be closed when try-catch
        //block is exited
//        try(Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Tim_Buchalka's_Java_Programming_Masterclass\\DB\\testjava.db");
//            Statement statement = connection.createStatement()) {
//            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
//        } catch (SQLException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }

        //Leaving like this -> have to CLOSE DB MANUALLY when finished using it
        try {
            Connection connection = DriverManager.getConnection("jdbc:sqlite:D:\\Tim_Buchalka's_Java_Programming_Masterclass\\DB\\testjava.db");
            Statement statement = connection.createStatement();
            //Default behaviour of JDBC objects is to automatically commit changes to DB
            statement.execute("CREATE TABLE IF NOT EXISTS contacts (name TEXT, phone INTEGER, email TEXT)");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Tim', 654654, 'tim@email.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Joe', 132314, 'joe@email.com')");
//            statement.execute("INSERT INTO contacts (name, phone, email) VALUES ('Jane', 445532, 'jane@email.com')");
//            statement.execute("UPDATE contacts SET phone=5556678 WHERE name='Jane'");
//            statement.execute("DELETE FROM contacts WHERE name='Joe'");
//            statement.execute("SELECT * FROM contacts");

            //Every ResultSet has a cursor - if we execute more than one statement in Statement
            //cursor is closed and new one (for new query) is created
//            ResultSet resultSet = statement.getResultSet();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM contacts");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + " " +
                                    resultSet.getInt("phone") + " " +
                                    resultSet.getString("email"));
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
