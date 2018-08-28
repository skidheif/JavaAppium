package lib.ui.mobile_web;

import lib.ui.MyListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWMyListsPageObject extends MyListsPageObject
{
    static {
        ARTICLE_BY_TITLE_TPL = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]";
        REMOVED_FROM_SAVED_BUTTON = "xpath://ul[contains(@class,'watchlist')]//h3[contains(text(),'{TITLE}')]/../../div[contains(@class,'watched')]";
        XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW = "xpath://*[@id=\"mw-content-text\"]/ul/li[2]/a/h3";
        XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW_DELETE = "xpath://*[@id=\"mw-content-text\"]/ul/li[2]/div";
    }

    public MWMyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}