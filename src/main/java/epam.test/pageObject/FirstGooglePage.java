package epam.test.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FirstGooglePage {

    @FindBy(name = "q")
    private WebElement queryStringLocator;

    private final WebDriver driver;

    public FirstGooglePage(WebDriver driver) {
        this.driver = driver;

        if (!"Google".equals(driver.getTitle()))
            throw new IllegalStateException("This is not a Google page.");
        PageFactory.initElements(driver, this);
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
