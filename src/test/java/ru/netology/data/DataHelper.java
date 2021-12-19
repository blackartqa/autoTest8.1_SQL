package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getValidLogin() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidLogin() {
        Faker faker = new Faker();
        return new AuthInfo("vasya", "zxcvb");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getValidVerificationCode() {
        String codeSQL = "SELECT code FROM auth_codes " + "ORDER BY created DESC limit 1";

        val runner = new QueryRunner();

        String code = null;

        try (val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");) {
            code = runner.query(connection, codeSQL, new ScalarHandler<>());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new VerificationCode(code);
    }

    public static VerificationCode getInvalidVerificationCode() {
        return new VerificationCode("987654");
    }

    public static void cleanTables() {
        val runner = new QueryRunner();
        val deleteUsers = "DELETE FROM users";
        val deleteCards = "DELETE FROM cards";
        val deleteAuthCodes = "DELETE FROM auth_codes";
        val deleteCardTrans = "DELETE FROM card_transactions";
        try (val connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");) {
            runner.update(connection, deleteCardTrans);
            runner.update(connection, deleteAuthCodes);
            runner.update(connection, deleteCards);
            runner.update(connection, deleteUsers);
            System.out.println("Tables are clean");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}