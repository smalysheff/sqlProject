package ru.smal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "12345";
    public static void main(String[] args) throws ClassNotFoundException, IOException {

        Class.forName(DB_DRIVER);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите id для удаления");
        int id = Integer.parseInt(reader.readLine());

        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)){

            String sqlDelete = "DELETE FROM user where id = ?";

            PreparedStatement statement = connection.prepareStatement(sqlDelete);
            statement.setInt(1, id);
            int res = statement.executeUpdate();
            if(res == 1){
                System.out.printf("Запись под № %d удалена", id);
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Connection FAILED");
        }

    }
}
