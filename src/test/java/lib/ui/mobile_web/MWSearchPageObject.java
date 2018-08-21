package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "css:button#searchIcon";
        SEARCH_INPUT = "css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON = "css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENT = "css://ul.page-list>li.page-summary";
        SEARCH_EMPTY_RESULT_ELEMENT = "css:p.without-results";

        ELEMENTS_IN_LIST = "css://*[@resource-id = 'org.wikipedia:id/page_list_item_container'][@index = '{INDEX_OF_TITLE}']";
        MY_TITLE_NAME_INPUT = "id:org.wikipedia:id/search_src_text";
        TITLE_CHECK_CLEAR = "id:org.wikipedia:id/search_empty_message";
    }

    public MWSearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}
