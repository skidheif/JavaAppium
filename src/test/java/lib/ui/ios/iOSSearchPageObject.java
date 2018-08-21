package lib.ui.ios;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "xpath://XCUIElementTypeNavigationBar[@name='Wikipedia, scroll to top of Explore']";
        SEARCH_CANCEL_BUTTON = "id:Close";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "xpath://XCUIElementTypeLink";
        SEARCH_EMPTY_RESULT_ELEMENT = "xpath://XCUIElementTypeStaticText[@name='No results found']";

        ELEMENTS_IN_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container'][@index = '{INDEX_OF_TITLE}']";
        MY_TITLE_NAME_INPUT = "id:org.wikipedia:id/search_src_text";
        TITLE_CHECK_CLEAR = "id:org.wikipedia:id/search_empty_message";
    }

    public iOSSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
