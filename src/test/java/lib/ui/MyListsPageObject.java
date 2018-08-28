package lib.ui;

import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject {

   protected static String
            FOLDER_BY_NAME,
            ARTICLE_BY_TITLE_TPL,
            THE_FIRST_ARTICLE_IN_LIST_PATH,
            TITLE_OF_THE_FIRST_ARTICLE_IN_LIST,
            XPATH_FOR_SECOND_ELEMENT_IN_LIST_IOS,
            XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW,
            XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW_DELETE,
            SECOND_TITLE_IN_THE_LIST,
            REMOVED_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return REMOVED_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getRemoveLocatorByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getSavedSecondArticleXpathByTitle(String article_title)
    {
        return SECOND_TITLE_IN_THE_LIST;
    }

   public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_by_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementPresent(
                folder_name_by_xpath,
                "Cannot find folder by name " + name_of_folder,
                10
        );

        this.waitForElementAndClick(
                folder_name_by_xpath,
                "Cannot find folder by name " + name_of_folder,
                10
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent((article_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitleForIOS()
    {
        String article_xpath = XPATH_FOR_SECOND_ELEMENT_IN_LIST_IOS;
        this.waitForElementNotPresent((article_xpath), "Saved article still present with title ", 15);
    }
    public void waitForArticleToDisappearByTitleForMW()
    {
        String article_xpath = XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW;
        this.waitForElementNotPresent((article_xpath), "Saved article still present with title ", 15);
    }

    public void waitForArticleToDisappearSecondArticle(String article_title)
    {
        String article_xpath = getSavedSecondArticleXpathByTitle(article_title);
        this.waitForElementNotPresent((article_xpath), "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {

        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || (Platform.getInstance().isAndroid())){
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find save article"
            );
        } else {
            String remove_locator = getRemoveLocatorByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved.",
                    10
            );
        }

        if (Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }

        if (Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }

        this.waitForArticleToDisappearByTitle(article_title);
    }

    public void swipeByArticleToDeleteForMW()
    {
        this.waitForElementPresent(
                XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW,
                "Cannot find the second element in MW list",
                30
        );
        this.waitForElementAndClick(
                XPATH_FOR_SECOND_ELEMENT_IN_LIST_MW_DELETE,
                "Cannot find the second element in MW list",
                30
        );
        driver.navigate().refresh();
        this.waitForArticleToDisappearByTitleForMW();
    }

    public void swipeBySecondArticleToDeleteForIOS()
    {
        this.waitForElementPresent(
                XPATH_FOR_SECOND_ELEMENT_IN_LIST_IOS,
                "Cannot find the second article"
        );
        this.swipeElementToLeft(
                XPATH_FOR_SECOND_ELEMENT_IN_LIST_IOS,
                "Cannot delete second article"
        );
        this.clickElementToTheRightUpperCornerIOS(XPATH_FOR_SECOND_ELEMENT_IN_LIST_IOS, "Cannot find the second article");
        this.waitForArticleToDisappearByTitleForIOS();
    }


    public WebElement waitForSecondTitleElement()
    {
        return this.waitForElementPresent((SECOND_TITLE_IN_THE_LIST), "Cannot find second article title on page!", 15 );
    }

    public String getSecondArticleTitle()
    {
        WebElement title_element = waitForSecondTitleElement();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeSecondElementInListByArticleToDelete(String article_title)
    {
        this.getSavedSecondArticleXpathByTitle(article_title);
        String article_xpath = getSavedSecondArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_xpath,
                "Cannot find save article"
        );
        this.waitForArticleToDisappearSecondArticle(article_title);
    }

    public void openTheFirstArticleInListAndCheck()
    {
        this.waitForElementPresent(
                THE_FIRST_ARTICLE_IN_LIST_PATH,
                "Cannot find the article with title Swift",
                15
        );

        this.waitForElementAndClick(
                THE_FIRST_ARTICLE_IN_LIST_PATH,
                "Cannot find the element with article Swift in the list",
                15
        );


        this.waitForElementPresent(
                TITLE_OF_THE_FIRST_ARTICLE_IN_LIST,
                "Cannot find article title",
                15
        );
    }
}
