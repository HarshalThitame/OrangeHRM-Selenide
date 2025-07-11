package base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utility.ConfigReader;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeClass
    public void setUp() {
        String env = System.getProperty("env", "dev");
        ConfigReader.loadProperties(env);
        Configuration.browser = get("browser");
        Configuration.remote = "http://localhost:4444/wd/hub";
        Configuration.headless = Boolean.parseBoolean(get("headless"));
        Configuration.browserSize = get("browser.size");
        Configuration.timeout = Long.parseLong(get("timeout"));

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

    }

    @AfterClass
    public void tearDown() {
        closeWebDriver();
    }

    public String get(String key) {
        return ConfigReader.getProperty(key);
    }
}
