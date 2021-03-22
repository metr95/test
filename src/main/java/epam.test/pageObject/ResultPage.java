package epam.test.pageObject;

import org.openqa.selenium.WebDriver;

public class ResultPage {

    private final WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;

        if (!driver.getTitle().toLowerCase().contains("automation"))
            throw new IllegalStateException("This is not a result page.");
    }

    public String getTitle() {
        return driver.getTitle();
    }
}
