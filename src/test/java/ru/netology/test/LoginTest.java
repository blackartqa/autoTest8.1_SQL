package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.DataHelper.*;

public class LoginTest {

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999");
    }

    @AfterAll
    public static void CleanTables() {
        DataHelper.cleanTables();
    }

    @Test
    public void shouldSuccessfulLogin() {
        val loginPage = new LoginPage();
        loginPage.login(getValidLogin());
        val verificationPage = loginPage.goToVerificationPage();
        verificationPage.verify(getValidVerificationCode());
        val dashboardPage = verificationPage.goToDashboardPage();
        dashboardPage.dashboardPage();
    }

    @Test
    public void shouldGetErrorIfInvalidLogin() {
        val loginPage = new LoginPage();
        loginPage.login(getInvalidLogin());
        loginPage.getErrorIfInvalidUser();
    }

    @Test
    public void shouldGetErrorIfInvalidVerificationCode() {
        val loginPage = new LoginPage();
        loginPage.login(getValidLogin());
        val verificationPage = loginPage.goToVerificationPage();
        verificationPage.verify(getInvalidVerificationCode());
        verificationPage.getErrorIfInvalidVerify();
    }
}