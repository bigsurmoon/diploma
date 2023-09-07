package ru.iteco.fmhandroid.ui.testHelper;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.auth;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.changeIndex;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnFoundElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnFoundString;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnString;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.closeKeyboard;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.findElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.scroll;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.waitForElement;

import ru.iteco.fmhandroid.R;

public class Steps {

    // Authorization
    public static void authButton() {
        clickOnFoundElement(R.id.authorization_image_button);
    }

    public static void logoutButton() {
        clickOnString(R.string.log_out);
    }

    public static void posAuth() {
        auth("login2", "password2");
    }

    public static void negAuth() {
        auth("random1", "randompass1");
    }

    public static void emptyAuth() {
        auth("", "");
    }

    // Claims
    public static void chooseExec() {
        clickOnElement(R.id.executor_drop_menu_auto_complete_text_view);
    }

    public static void claimInProgress() {
        clickOnFoundString(R.string.in_progress);
    }

    public static void claimOpen() {
        clickOnFoundString(R.string.open);
    }

    public static void clickOnFilter() {
        clickOnElement((R.id.filters_material_button));
    }

    public static void filterInProgress() {
        clickOnElement(R.id.item_filter_in_progress);
    }

    public static void filterOk() {
        clickOnElement(R.id.claim_list_filter_ok_material_button);
    }

    public static void statusButton() {
        clickOnElement(R.id.status_processing_image_button);
    }

    public static void claimIsCanceled() {
        clickOnFoundString(R.string.status_claim_canceled);
    }

    public static void claimEdit() {
        clickOnElement(R.id.edit_processing_image_button);
    }

    public static void closeClaimKeyboard() {
        closeKeyboard(R.id.executor_drop_menu_auto_complete_text_view);
    }

    public static void clickSaveButton() {
        clickOnElement(R.id.save_button);
    }

    // Main
    public static void clickAllNews() {
        clickOnString(R.string.all_news);
    }

    public static void waitForNews() {
        findElement(R.id.all_news_cards_block_constraint_layout);
    }

    public static void chooseClaim() {
        changeIndex(R.id.claim_list_card);
    }

    public static void waitClaim() {
        waitForElement(R.id.container_custom_app_bar_include_on_fragment_open_claim);
    }

    public static void clickAllClaims() {
        clickOnString(R.string.all_claims);
    }

    public static void waitForClaims() {
        waitForElement(R.id.container_custom_app_bar_include_on_fragment_list_claim);
    }

    public static void changeNews() {
        changeIndex(R.id.news_item_material_card_view);
    }

    public static void waitNews() {
        waitForElement(R.id.news_item_description_text_view);
    }

    public static void butterflyButton() {
        clickOnElement(R.id.our_mission_image_button);
    }

    public static void waitForButterfly() {
        waitForElement(R.id.our_mission_title_text_view);
    }

    public static void startPage() {
        waitForElement(R.id.login_text_input_layout);
    }

    //Menu
    public static void waitForAbout() {
        waitForElement(R.id.container_custom_app_bar_include_on_fragment_about);
    }

    public static void waitForMain() {
        waitForElement(R.id.container_custom_app_bar_include_on_fragment_main);
    }

    // News
    public static void newsWait() {
        waitForElement(R.id.news_list_recycler_view);
    }

    public static void findNews(String text) {
        scroll(R.id.news_list_recycler_view, (text));
    }

    public static void clickPositive() {
        clickOnString(R.string.fragment_positive_button);
    }
}