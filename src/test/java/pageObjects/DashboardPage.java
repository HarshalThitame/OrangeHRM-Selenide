package pageObjects;

import base.BasePage;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

public class DashboardPage extends BasePage {

    private final SelenideElement dashboardText = $x("//h6[normalize-space()='Dashboard']");

    @Step("Get Dashboard validation text")
    public String getDashboardText() {
        return dashboardText.getText();
    }
}
