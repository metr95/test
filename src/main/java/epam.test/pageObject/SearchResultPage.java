package epam.test.pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultPage {

    @FindBy(xpath = "//div[@id='search']//a[1]")
    private static WebElement firstLinkByXPath;
    @FindBy(xpath = "//div[@id='navcnt']//td")
    private static List<WebElement> googlePages;
    @FindBy(id = "tads")
    private static List<WebElement> googleAds;
    @FindBy(xpath = "//div[@id='tads']//a")
    private static WebElement googleAdsLink;
    @FindBy(xpath = "//div[@id='search']//div[@class='g']")
    private static List<WebElement> googleSearchResults;

    private static final By googleSearchResultLinks = By.xpath("div/div/div[@class='r']//a/div");

    private final WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public SearchResultPage clickFirstLink() {
        firstLinkByXPath.click();
        return this;
    }

    public SearchResultPage navigateToPage(int i) {
        googlePages.get(i).click();
        return this;
    }

    public int getNumberOfPages() {
        return googlePages.size();
    }

    public boolean isUrlOnPage(String url) {
        if (googleAds.size() > 0) {
            if (googleAdsLink.getAttribute("href").contains(url))
                return true;
        }
        return googleSearchResults.stream().anyMatch(g -> g.findElement(googleSearchResultLinks).getText().contains(url));
    }

    public boolean searchForDomain(String searchForDomain, int maxPageCounter) {
        for (int i = 1; i <= maxPageCounter; i++) {
            this.navigateToPage(i);
            if (this.isUrlOnPage(searchForDomain)) {
                return true;
            }
        }
        return false;
    }
}
