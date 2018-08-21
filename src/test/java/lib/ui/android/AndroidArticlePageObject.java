package lib.ui.android;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "id:org.wikipedia:id/view_page_title_text";
        FOOTER_ELEMENT = "xpath://*[@text = 'View page in browser']";
        OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc = 'More options']";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text = 'Add to reading list']";
        ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button";
        MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input";
        MY_LIST_OK_BUTTON = "xpath://*[@text = 'OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc = 'Navigate up']";

        //under is lying xpath to homework
        ELEMENTS_IN_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/page_list_item_container'][@index = '{INDEX_OF_TITLE}']";
        PAGE_SEARCH_BUTTON = "id:org.wikipedia:id/menu_page_search";
        OLD_TITLE = "id:org.wikipedia:id/text1";
        NAME_OF_LIST = "xpath://*[@resource-id = 'org.wikipedia:id/item_title'][@text = '{LIST_NAME_WITH_ARTICLE}']";
        SECOND_TITLE_IN_THE_LIST = "id:org.wikipedia:id/page_list_item_title";
    }

    public AndroidArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}


