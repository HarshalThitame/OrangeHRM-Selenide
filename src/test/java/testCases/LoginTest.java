package testCases;

import base.BaseTest;
import io.qameta.allure.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import utility.RetryAnalyzer;

import static com.codeborne.selenide.Selenide.open;
import static utility.ConfigReader.getProperty;

@Epic("OrangeHRM - Login Module")
@Feature("Login Feature")
public class LoginTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);


    @Test(description = "User should be able to log in with valid credentials", retryAnalyzer = RetryAnalyzer.class)
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with valid credentials")
    @Owner("Harshal Thitame")
    @Description("This test verifies that a user can log in to OrangeHRM using valid Admin credentials and is redirected to the dashboard.")
    public void testValidLogin_TC_Login_001() {
        log.info("Opening login URL...");
        open(getProperty("login.url"));

        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin", "admin123");
        DashboardPage dashboardPage = loginPage.gotoDashboardPage();

        log.info("Verifying Dashboard text...");
        String dashboardText = dashboardPage.getDashboardText();

        log.debug("Dashboard header found: {}", dashboardText);
        Assert.assertEquals(dashboardText, "Dashboard", "Login failed: Expected 'Dashboard' but got '" + dashboardText + "'");
        log.info("Login verified successfully with correct credentials.");
    }

    @Test(description = "User should not be able to log in with invalid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with invalid credentials")
    @Owner("Harshal Thitame")
    @Description("This test verifyies that a user can not log in to orangeHRM using invalid credentials and shows error message")
    public void testInvalidLogin_TC_Login_002() {
        log.info("Opening login URL...");
        open(getProperty("login.url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login("Adamin", "admin123");
        String invalidCredText = loginPage.getInvalidCredText();
        log.info("Verifying invalid credentials text...");
        Assert.assertEquals(invalidCredText, "Invalid credentials", "Test failed: Invalid credentials");
        log.info("Test case passed: Validation message shown for invalid credentials.");
    }

    @Test(description = "User should not be able to log in with a blank username")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login with blank username")
    @Owner("Harshal Thitame")
    @Description("Verifies that the system shows an appropriate error when trying to log in without entering a username.")
    public void testLoginWithBlankUsername_TC_Login_003() {
        log.info("Opening login URL...");
        open(getProperty("login.url"));
        LoginPage loginPage = new LoginPage();
        loginPage.login("", "admin123");
        log.info("Verifying required text...");
        String usernameRequiredText = loginPage.getUsernameRequiredText();
        Assert.assertEquals(usernameRequiredText, "Required", "Expected validation message not shown for blank username.");
        log.info("Test case passed: Validation message shown for blank username.");
    }

    @Test(description = "User should not be able to log in with a blank password")
    @Severity(SeverityLevel.NORMAL)
    @Story("Login with blank password")
    @Owner("Harshal Thitame")
    @Description("Verifies that the system shows an appropriate validation message when the password field is left blank during login.")
    public void testLoginWithBlankPassword_TC_Login_004() {
        log.info("Opening login page...");
        open(getProperty("login.url"));

        LoginPage loginPage = new LoginPage();
        loginPage.login("Admin", "");  // valid username, blank password

        String actualError = loginPage.getPasswordRequiredText();
        log.debug("Validation message for password: {}", actualError);

        Assert.assertEquals(actualError, "Required", "Expected validation message not shown for blank password.");
        log.info("Test case passed: Validation message shown for blank password.");
    }

    @Test(description = "User should not be able to log in with both fields blank")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Login with both username and password blank")
    @Owner("Harshal Thitame")
    @Description("Verifies that appropriate validation messages are shown when both username and password fields are left blank.")
    public void testLoginWithBothFieldsBlank_TC_Login_005() {
        log.info("Opening login page...");
        open(getProperty("login.url"));

        LoginPage loginPage = new LoginPage();
        loginPage.login("", "");  // blank username and password

        String usernameError = loginPage.getUsernameRequiredText();
        String passwordError = loginPage.getPasswordRequiredText();

        log.debug("Username error: {}", usernameError);
        log.debug("Password error: {}", passwordError);

        Assert.assertEquals(usernameError, "Required", "Expected 'Required' message for blank username.");
        Assert.assertEquals(passwordError, "Required", "Expected 'Required' message for blank password.");

        log.info("Test case passed: Validation messages shown for both blank username and password.");
    }

}
