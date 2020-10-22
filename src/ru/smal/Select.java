package ru.smal;

import java.sql.*;

public class Select {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "12345";

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName(DB_DRIVER);

        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)){
            System.out.println("Connected");

            String sql = "SELECT * FROM user";

            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                int age = resultSet.getInt("age");

                System.out.printf("%d %s %s \t%d\n", id , firstName, lastName, age);

            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Not connected");
        }

    }
}
