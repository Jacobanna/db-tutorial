package com.timbuchalka.testdb;

import java.sql.*;

public class Main2 {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\Tim_Buchalka's_Java_Programming_Masterclass\\DB\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try{
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                                 " (" + COLUMN_NAME + " text, " +
                                        COLUMN_PHONE + " integer, " +
                                        COLUMN_EMAIL + " text" + ")");
//            statement.execute("INSERT INTO " + TABLE_CONTACTS +
//                                     " (" + COLUMN_NAME + ", " +
//                                            COLUMN_PHONE + ", " +
//                                            COLUMN_EMAIL + ") " +
//                                            "VALUES('Tim', 666555, 'tim@email.com')");
            insertContact(statement,"Tim", 666555, "tim@email.com");
            insertContact(statement,"Joe", 333444, "joe@email.com");
            insertContact(statement,"Jane", 111222, "jane@email.com");
            insertContact(statement,"Fido", 888999, "dog@email.com");
            statement.execute("UPDATE " + TABLE_CONTACTS + " SET " +
                                    COLUMN_PHONE + "=123456" +
                                    " WHERE " + COLUMN_NAME + "='Jane'");
            statement.execute("DELETE FROM " + TABLE_CONTACTS +
                                    " WHERE " + COLUMN_NAME + "='Joe'");
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(COLUMN_NAME) + " " +
                                    resultSet.getInt(COLUMN_PHONE) + " " +
                                    resultSet.getString(COLUMN_EMAIL));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("INSERT INTO " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ") " +
                "VALUES('" + name + "', " + phone + ", '" + email + "')");
    }
}
