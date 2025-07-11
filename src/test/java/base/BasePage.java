package base;

import pageObjects.DashboardPage;

public class BasePage {

    public DashboardPage gotoDashboardPage(){
        return new DashboardPage();
    }
}
