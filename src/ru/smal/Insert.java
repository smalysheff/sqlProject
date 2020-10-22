package ru.smal;

import java.sql.*;

public class Insert {
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/users?serverTimezone=UTC";
    private static final String DB_USER = "user1";
    private static final String DB_PASSWORD = "12345";

    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName(DB_DRIVER);

        try(Connection connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD)){

            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            String sqlInsert = "INSERT INTO user (firstName, lastName, age) VALUES (?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, "Client");
            statement.setString(2, "Clientov");
            statement.setInt(3, 10);
            int res = statement.executeUpdate();

            if(res == 1){
                connection.commit();
                ResultSet result = statement.getGeneratedKeys();
                if(result.next()){
                    int id = result.getInt(1);
                    System.out.printf("Пользователь добавлен под номером: %d", id);
                }
            }



        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
}
