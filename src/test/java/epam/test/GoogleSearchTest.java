package epam.test;

import epam.test.base.BaseTest;
import epam.test.pageObject.FirstGooglePage;
import epam.test.pageObject.ResultPage;
import epam.test.pageObject.SearchResultPage;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

public class GoogleSearchTest extends BaseTest {

    private static final Logger log = Logger.getLogger(GoogleSearchTest.class);

    @Test
    public void titleInLowerCaseFirstPageInSearch(Method method) {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();

        SearchResultPage srp = new SearchResultPage(getDriver());
        srp.clickFirstLink();

        ResultPage rp = new ResultPage(getDriver());
        String strTitle = rp.getTitle();
        Assert.assertTrue(strTitle.toLowerCase().contains(getTestDataLoader().getProperty("searchWord")),
                String.format("check if title: \"%s\" in lowerCase contains \"%s\" word",
                        strTitle.toLowerCase(), getTestDataLoader().getProperty("searchWord")));
    }

    @Test
    public void titleFirstPageInSearch(Method method) {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        Assert.assertEquals(gp.getPageTitle(), getTestDataLoader().getProperty("googlePageIdentification"),
                "check that page is Google.");

        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();

        SearchResultPage srp = new SearchResultPage(getDriver());
        Assert.assertTrue(srp.getPageTitle().contains(getTestDataLoader().getProperty("googlePageIdentification")),
                "check if page title contains Google.");

        srp.clickFirstLink();

        ResultPage rp = new ResultPage(getDriver());
        Assert.assertTrue(rp.getTitle().contains(getTestDataLoader().getProperty("searchWord")));

        String strTitle = rp.getTitle();
        Assert.assertTrue(strTitle.contains(getTestDataLoader().getProperty("searchWord")),
                String.format("check if title: \"%s\" contains \"%s\" word",
                        strTitle, getTestDataLoader().getProperty("searchWord")));
    }

    @Test
    public void searchForDomain(Method method) {
        FirstGooglePage gp = new FirstGooglePage(getDriver());
        gp.typeSearchQuery(getTestDataLoader().getProperty("searchWord"));
        gp.submitQuery();

        SearchResultPage srp = new SearchResultPage(getDriver());
        int maxPageCounter = Integer.parseInt(getTestDataLoader().getProperty("maxPagesNumberForSearch"));

        if (srp.getNumberOfPages() < maxPageCounter)
            maxPageCounter = srp.getNumberOfPages();

        boolean found = srp.searchForDomain(getTestDataLoader().getProperty("searchForDomain"), maxPageCounter);

        Assert.assertTrue(found,
                String.format("check if there is a site \"%s\" on first %s google's pages.",
                        getTestDataLoader().getProperty("searchForDomain"), getTestDataLoader().getProperty("maxPagesNumberForSearch")));
    }
}
