package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject{

     protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT,
            ELEMENTS_IN_LIST,
            MY_TITLE_NAME_INPUT,
            TITLE_CHECK_CLEAR;

    private static String getIndexOfTitle(int index_of_title)
    {
        String index_title = String.valueOf(index_of_title);
        return ELEMENTS_IN_LIST.replace("{INDEX_OF_TITLE}", index_title);
    }

    public SearchPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */


    public void initSearchInput()
    {
        this.waitForElementAndClick((SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 5);
        this.waitForElementPresent((SEARCH_INIT_ELEMENT), "Cannot find search input after clicking search init element");
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON), "Cannot find search cancel button!", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick((SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys((SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath), "Cannot find search result with substring " + substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath = getResultSearchElement(substring);
        this.waitForElementAndClick((search_result_xpath), "Cannot find and click search result with substring " + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent((SEARCH_RESULT_ELEMENT), "Cannot find anything by request", 15);
        return this.getAmountOfElements((SEARCH_RESULT_ELEMENT));
    }

    public void waitForEmptyResultsLabel()
    {

        this.waitForElementPresent((SEARCH_EMPTY_RESULT_ELEMENT), "Cannot find empty result element", 15);
    }

    public void assertThereIsNoResultOfSearch()
    {
        this.assertElementNotPresent((SEARCH_RESULT_ELEMENT), "We supposed not to find any results");
    }

    public void waitForElementTitleInList(int index_of_title) //this method for Ex3SearchAndCancelSearch
    {
        String get_index_of_title = getIndexOfTitle(index_of_title);
        this.waitForElementPresent((get_index_of_title), "Cannot find the first element in list, searching by Swift", 15);
    }

    public void clearTitleInInput() //this method for Ex3SearchAndCancelSearch
    {
        this.waitForElementAndClear((MY_TITLE_NAME_INPUT),"Cannot find input to set name of art",15);
    }

    public void checkTitleClear() //this method for Ex3SearchAndCancelSearch
    {
        this.waitForElementAndClear((TITLE_CHECK_CLEAR),"Cannot find 'Search and read the free encyclopedia in your language'",15);
    }
}
