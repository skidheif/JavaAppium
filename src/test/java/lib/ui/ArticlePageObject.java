package lib.ui;

import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON,
            CLOSE_ARTICLE_BUTTON,
            TITLE_IOS1,
            TITLE_IOS2,
            TITLE_MW1,
            TITLE_MW2,
            ELEMENTS_IN_LIST,
            PAGE_SEARCH_BUTTON,
            OLD_TITLE,
            NAME_OF_LIST,
            SECOND_TITLE_IN_THE_LIST;

    private static String getIndexOfTitle(int index_of_title)
    {
        String index_title = String.valueOf(index_of_title);
        return ELEMENTS_IN_LIST.replace("{INDEX_OF_TITLE}", index_title);
    }

    private static String getNameOfFolder(String name_of_folder)
    {
        return NAME_OF_LIST.replace("{LIST_NAME_WITH_ARTICLE}", name_of_folder);
    }

    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title on page!", 15 );
    }

    //methods for test MyPageObjectList for IOS
    public WebElement waitForTitleElementForIOS1()
    {
        return this.waitForElementPresent(TITLE_IOS1, "Cannot find article title on page!", 15 );
    }

    public WebElement waitForTitleElementForIOS2()
    {
        return this.waitForElementPresent(TITLE_IOS2, "Cannot find article title on page!", 15 );
    }
    //end of the methods

    //methods for test MyPageObjectList for MW
    public WebElement waitForTitleElementForMW1()
    {
        return this.waitForElementPresent(TITLE_MW1, "Cannot find article title on page!", 15 );
    }

    public WebElement waitForTitleElementForMW2()
    {
        this.waitForElementPresent(
                TITLE_MW2,
                "Cannot find element on the page!",
                15
        );
        return this.waitForElementPresent(TITLE_MW2, "Cannot find article title on page!", 15 );
    }
    //end of the methods

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public String getArticleTitleForIOS()
    {
        WebElement title_element = waitForTitleElementForIOS2();
        return title_element.getAttribute("name");
    }

    public String getArticleTitleForMW()
    {
        WebElement title_element = waitForTitleElementForMW1();
        return title_element.getText();
    }

    public void waitAndCheckThatSecondElementDelete()
    {
        this.waitForElementNotPresent(
                SECOND_TITLE_IN_THE_LIST,
                "Cannot delete save article",
                15
        );
    }

    public void swipeToFooter()
    {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(
                    FOOTER_ELEMENT,
                    "Cannot find the end of the article",
                    40
            );
        } else {
            this.scrollWebPageTillElementNotVisible(
                    FOOTER_ELEMENT,
                    "Cannot find the end of article",
                    40
            );
        }
    }

    public void addArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY,
                "Cannot find 'Got it' tip overlay",
                5
        );

        this.waitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find input to set name of art",
                5
        );

        this.waitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                name_of_folder,
                "Cannot put text into articles folder input",
                5
        );

        this.waitForElementAndClick(
                MY_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || (Platform.getInstance().isAndroid())){
            this.waitForElementAndClick(
                    CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link",
                    5
            );
        } else {
            System.out.println("Method closeArticle() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickByArticleWithTitle(int index_of_title)
    {
        String get_index_of_title = getIndexOfTitle(index_of_title);
        this.waitForElementAndClick(get_index_of_title, "Cannot find and click search result with substring" + index_of_title, 10);
    }

    public void clearTitleAndChooseOldItem()
    {
        this.waitForElementAndClick(
                PAGE_SEARCH_BUTTON,
                "Cannot find search_button",
                15
        );

        this.waitForElementAndClick(
                OLD_TITLE,
                "Cannot find the first element of recent search list",
                15
        );
    }

    public void addSecondArticleToMyList(String name_of_folder)
    {

        this.waitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find button to open article options",
                5
        );

        this.waitForElementPresent(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        this.waitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5
        );

        String name_of_folder_with_articles = getNameOfFolder(name_of_folder);

        this.waitForElementAndClick(
                name_of_folder_with_articles,
                "Cannot find folder to add article to reading list",
                15
        );
    }

    public void assertElementPresentEx6() // method for task Ex6
    {
        this.assertElementPresentEx6(
                TITLE,
                "Cannot find title = "
        );
    }

    public void addArticlesToMySaved()
    {
        if (Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded()
    {
        if(this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON))
        {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click button to remove an article from saved",
                    1
            );
            this.waitForElementPresent(
                    OPTIONS_ADD_TO_MY_LIST_BUTTON,
                    "Cannot find button to add an article to saved list after removing it from this list before"
            );
        }
    }
}
