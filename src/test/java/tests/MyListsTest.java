package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {

    private static final String
            name_of_folder = "Learning programming",
            name_of_folder_for_homework = "Homework Ex5",
            login = "john_heif",
            password = "Raven2017)WI";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }
        if (Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals( "We are not on the same page after login.",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );
            ArticlePageObject.addArticlesToMySaved();
        }

        if(Platform.getInstance().isIOS()){
            ArticlePageObject.closeArticle();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if(Platform.getInstance().isAndroid()){
            MyListsPageObject.openFolderByName(name_of_folder);
        }
        MyListsPageObject.swipeByArticleToDelete(article_title);
    }



    //Under this line are located homework "Ex5: Тест: сохранение двух статей"
    public void testEx5SaveTwoArticleToMyListAndDelete() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        AuthorizationPageObject Auth = new AuthorizationPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Swift");

        if (Platform.getInstance().isAndroid()) {
            String article_title = MyListsPageObject.getSecondArticleTitle();
            ArticlePageObject.clickByArticleWithTitle(0);
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyList(name_of_folder_for_homework);
            ArticlePageObject.closeArticle();
            ArticlePageObject.clearTitleAndChooseOldItem();
            ArticlePageObject.clickByArticleWithTitle(1);
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addSecondArticleToMyList(name_of_folder_for_homework);
            ArticlePageObject.closeArticle();
            NavigationUI.clickMyLists();
            MyListsPageObject.openFolderByName(name_of_folder_for_homework);
            MyListsPageObject.swipeSecondElementInListByArticleToDelete(article_title);
            ArticlePageObject.waitAndCheckThatSecondElementDelete();
            MyListsPageObject.openTheFirstArticleInListAndCheck();
            String first_element_in_list = ArticlePageObject.getArticleTitle();
            assertEquals(
                    "We see unexpected title",
                    "Swift",
                    first_element_in_list
            );
        } else if (Platform.getInstance().isIOS()){
            SearchPageObject.clickByArticleWithSubstring("Family of birds");
            ArticlePageObject.waitForTitleElementForIOS1();
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            ArticlePageObject.closeArticle();
            SearchPageObject.initSearchInput();
            SearchPageObject.clickByArticleWithSubstring("General-purpose, multi-paradigm, compiled programming language");
            ArticlePageObject.waitForTitleElementForIOS2();
            ArticlePageObject.addArticlesToMySaved();
            ArticlePageObject.closeArticle();
            NavigationUI.clickMyLists();
            MyListsPageObject.swipeBySecondArticleToDeleteForIOS();
            SearchPageObject.clickByArticleWithSubstring("General-purpose, multi-paradigm, compiled programming language");
            ArticlePageObject.waitForTitleElementForIOS2();
            String first_element_in_list = ArticlePageObject.getArticleTitleForIOS();
            assertEquals(
                    "We see unexpected title",
                    "Swift (programming language)",
                    first_element_in_list
            );
        } else if (Platform.getInstance().isMW()){
            ArticlePageObject.waitForTitleElement();
            SearchPageObject.clickByArticleWithSubstring("amily of birds");

            ArticlePageObject.waitForTitleElementForMW1();
            ArticlePageObject.addArticlesToMySaved();

            Auth.clickAuthButton();
            Auth.enterLoginData(login, password);
            Auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine("Swift");
            SearchPageObject.clickByArticleWithSubstring("eneral-purpose, multi-paradigm, compiled programming language");
            ArticlePageObject.waitForTitleElementForMW2();

            ArticlePageObject.addArticlesToMySaved();

            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();
            MyListsPageObject.swipeByArticleToDeleteForMW();

            NavigationUI.openNavigation();
            NavigationUI.clickMyLists();

            SearchPageObject.clickByArticleWithSubstringForMW();
            ArticlePageObject.waitForTitleElementForMW1();
            String first_element_in_list = ArticlePageObject.getArticleTitleForMW();
            assertEquals(
                    "We see unexpected title",
                    "Swift",
                    first_element_in_list
            );
        }
    }
    // End of the homework "Ex5: Тест: сохранение двух статей"
}
