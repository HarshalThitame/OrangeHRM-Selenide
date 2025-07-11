package pageObjects;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {

    private final SelenideElement usernameTxt = $(By.name("username"));
    private final SelenideElement passwordTxt = $(By.name("password"));
    private final SelenideElement loginButton = $x("//button[@type='submit' and contains(@class,'orangehrm-login-button')]");
    private final SelenideElement usernameRequiredText = $x("//input[@name='username']/parent::div/following-sibling::span");
    private final SelenideElement passwordRequiredText = $x("//input[@name='password']/parent::div/following-sibling::span");
    private final SelenideElement invalidCredText = $x("//p[contains(.,'Invalid credentials')]");

    @Step("User login using username {username} and password [PROTECTED]")
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickOnLoginButton();
    }

    @Step("Enter username : {username}")
    private void enterUsername(String username) {
        usernameTxt.setValue(username);
    }

    @Step("Enter password : [PROTECTED]")
    private void enterPassword(String password) {
        passwordTxt.setValue(password);
    }

    @Step("Click on login button")
    private void clickOnLoginButton() {
        loginButton.click();
    }

    @Step("Get required if username empty")
    public String getUsernameRequiredText() {
        return usernameRequiredText.getText();
    }

    @Step("Get required if password empty")
    public String getPasswordRequiredText() {
        return passwordRequiredText.getText();
    }

    @Step("Getting invalid credential message")
    public String getInvalidCredText() {
        return invalidCredText.getText();
    }


}
