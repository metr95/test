package epam.test;

import epam.test.base.BaseTest;
import epam.test.pageObject.FirstGooglePage;
import epam.test.pageObject.ResultPage;
import epam.test.pageObject.SearchResultPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class GoogleSearchTest extends BaseTest {

    private static final Logger log = Logger.getLogger(GoogleSearchTest.class);

    @Test
    public void titleInLowerCaseFirstPageInSearch() {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();
        SearchResultPage srp = new SearchResultPage(getDriver());
        srp.clickFirstLink();
        ResultPage rp = new ResultPage(getDriver());
        String strTitle = rp.getTitle();
        Assert.assertTrue(strTitle.toLowerCase().contains(getTestDataLoader().getProperty("searchWord")),"check if title: \"" + strTitle.toLowerCase() + "\" in lowerCase contains \"" + getTestDataLoader().getProperty("searchWord") + "\" word");
    }

    @Test
    public void titleFirstPageInSearch() {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();
        SearchResultPage srp = new SearchResultPage(getDriver());
        srp.clickFirstLink();
        ResultPage rp = new ResultPage(getDriver());
        String strTitle = rp.getTitle();
        Assert.assertTrue(strTitle.contains(getTestDataLoader().getProperty("searchWord")), "check if title: \"" + strTitle + "\" contains \"" + getTestDataLoader().getProperty("searchWord") + "\" word");
    }

    @Test
    public void searchForDomain() {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();
        SearchResultPage srp = new SearchResultPage(getDriver());
        int maxPageCounter = Integer.parseInt(getTestDataLoader().getProperty("maxPagesNumberForSearch"));
        if (srp.getNumberOfPages() < maxPageCounter)
            maxPageCounter = srp.getNumberOfPages();
        boolean found = srp.searchForDomain(getTestDataLoader().getProperty("searchForDomain"), maxPageCounter);
        Assert.assertTrue(found, "check if there is a site \"" + getTestDataLoader().getProperty("searchForDomain") + "\" on first " + getTestDataLoader().getProperty("searchForDomain") + " google's pages.");
    }
}
