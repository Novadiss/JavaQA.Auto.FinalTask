package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {
    private static final QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
    }


    @SneakyThrows
    public static DataHelper.TransactionId getTransactionId(String tear) {
        var codeSQL = "SELECT " + tear + " From order_entity DESK LIMIT 1";
        var conn = getConn();
        var value = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.TransactionId(value);
    }

    @SneakyThrows
    public static DataHelper.OperationStatus getStatus(String tearId, String tear) {
        var codeSQL = "SELECT status From " + tearId + " WHERE transaction_id='" + DataHelper.getTransactionId(tear).getId() + "'";
        var conn = getConn();
        var value = runner.query(conn, codeSQL, new ScalarHandler<String>());
        return new DataHelper.OperationStatus(value);
    }


    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }

    @SneakyThrows
    public static void cleanOrderEntity() {
        var connection = getConn();
        runner.execute(connection, "DELETE FROM order_entity");
    }
}
