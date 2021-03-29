package epam.test.base;

import epam.test.Driver;
import epam.test.TestDataLoader;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class);
    @Getter
    private TestDataLoader testDataLoader = new TestDataLoader();
    private WebDriver driver;

    public WebDriver getDriver() {
        if (driver == null || driver.toString().contains("null"))
            driver = new Driver().getDriver(testDataLoader.getProperty("browser"), testDataLoader.getProperty("resolution"));
        return driver;
    }


    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod
    public void beforeMethod() {
        getDriver().navigate().to(testDataLoader.getProperty("testPage"));
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null)
            driver.quit();
    }
}
