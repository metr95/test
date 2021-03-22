package epam.test.base;

import epam.test.Driver;
import epam.test.TestDataLoader;
import lombok.Getter;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private static final Logger log = Logger.getLogger(BaseTest.class);
    @Getter
    private TestDataLoader testDataLoader = new TestDataLoader();
    private Driver driver;

    public WebDriver getDriver() {
        return driver.getDriver(System.getProperty("browser"), System.getProperty("resolution"));
    }

    public void initializeDriver() {
        driver = Driver.getInstanse();
        driver.getDriver(System.getProperty("browser"), System.getProperty("resolution"));
    }

    @BeforeClass
    public void beforeClass() {
    }

    @BeforeMethod
    public void beforeMethod() {
        initializeDriver();
        getDriver().navigate().to(testDataLoader.getProperty("testPage"));
    }

    @AfterMethod
    public void afterMethod() {
        if (driver != null)
            driver.close();
    }
}
