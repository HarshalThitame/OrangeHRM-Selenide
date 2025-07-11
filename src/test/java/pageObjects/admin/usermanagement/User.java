package pageObjects.admin.usermanagement;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class User extends BasePage {

    private final SelenideElement addUserButton = $x("//button[normalize-space()='Add']");
    private final SelenideElement usernameTxt = $x("//label[text()='Username']/parent::div/following-sibling::div/input");
    private final SelenideElement employeeName = $x("//label[.='Employee Name']/parent::div/following-sibling::div//input");
    
}
