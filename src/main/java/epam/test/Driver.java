package epam.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private WebDriver driver = null;
    private static final Logger log = Logger.getLogger(Driver.class);


    public WebDriver getDriver(String browser, String resolution) {
        if (driver == null || driver.toString().contains("null"))
            init(browser, resolution);
        return driver;
    }

    private void init(String browser, String resolution) {
        log.info(String.format("Starting to create [%s] driver with resolution [%s]", browser, resolution));
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
        }

        log.info(String.format("finished to create [%s] driver", browser));
        log.info(String.format("[%s] driver created.", browser));
        driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);
        log.info(String.format("set resolution [%s] for [%s] driver", resolution, browser));
        driver.manage().window().setSize(
                new Dimension(Integer.parseInt(resolution.split("x")[0]),
                        Integer.parseInt(resolution.split("x")[1])));
    }

    public void close() {
        driver.quit();
    }
}
