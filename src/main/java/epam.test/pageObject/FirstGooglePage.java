package epam.test.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstGooglePage {

    @FindBy(name = "q")
    private static WebElement queryStringLocator;

    private final WebDriver driver;

    public FirstGooglePage(WebDriver driver) {
        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public FirstGooglePage typeSearchQuery(String searchQuery) {
        queryStringLocator.sendKeys(searchQuery);
        return this;
    }

    public FirstGooglePage submitQuery() {
        queryStringLocator.submit();
        return this;
    }
}
