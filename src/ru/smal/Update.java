package ru.smal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "12345";
    public static void main(String[] args) throws ClassNotFoundException, IOException, SQLException {

        Class.forName(DB_DRIVER);

        var reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите id");
        int id = Integer.parseInt(reader.readLine());
        System.out.println("Введите firstName");
        String firstName = reader.readLine();
        System.out.println("Введите lastName");
        String lastName = reader.readLine();
        System.out.println("Введите age");
        int age = Integer.parseInt(reader.readLine());


        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)) {

            try {
                connection.setAutoCommit(false);
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

                String sqlUpdate = "UPDATE user SET firstName=?, lastName=?, age=? where id=?";

                PreparedStatement statement = connection.prepareStatement(sqlUpdate);
                statement.setString(1, firstName);
                statement.setString(2, lastName);
                statement.setInt(3, 10);
                statement.setInt(4, id);

                if (statement.executeUpdate() == 1) {
                    connection.commit();
                    System.out.printf("Запись под номером %d обновлена", id);
                }
            }
            catch (SQLException exception) {
                connection.rollback();
            }
        }
    }
}
