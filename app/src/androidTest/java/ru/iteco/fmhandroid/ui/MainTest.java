package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.claimAfterAddExec;
import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.claimBeforeAddExec;
import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.findClaim;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.authExists;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.checkIfLogin;

import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.claimSection;

import static ru.iteco.fmhandroid.ui.testHelper.Steps.authButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.butterflyButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.changeNews;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.chooseClaim;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.clickAllClaims;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.clickAllNews;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.logoutButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.posAuth;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.startPage;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitClaim;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForButterfly;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForClaims;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForNews;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitNews;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Main page")
@DisplayName("Main page")

public class MainTest extends BaseTest {

    @Before
    public void loginIfRequired() {
        Allure.step("Authorized and log in if required");
        if (!checkIfLogin()) {
            posAuth();
            authExists();
        }
    }

    @Test
    @DisplayName("Show all news")
    public void allNews() {
        Allure.step("Press 'Все новости' button");
        clickAllNews();
        Allure.step("Moving to 'Все новости' completed");
        waitForNews();
    }

    @Test
    @DisplayName("Show claims")
    public void claimView() {
        Allure.step("Choose claim");
        chooseClaim();
        Allure.step("Claim is showed up");
        waitClaim();
    }

    @Test
    @DisplayName("View all claims")
    public void allClaim() {
        Allure.step("Press 'Заявки' button");
        clickAllClaims();
        Allure.step("Moving to Claims page completed");
        waitForClaims();
    }


    @Test
    @DisplayName("Creating claim from Main page")
    public void createClaimMain() {
        Allure.step("Moving to creating Claim form");
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        claimSection();
        findClaim(text);
    }

    @Test
    @DisplayName("View news from Main page")
    public void newView() {
        Allure.step("Choose news");
        changeNews();
        Allure.step("View news");
        waitNews();
    }

    @Test
    @DisplayName("View quotes")
    public void quote() {
        Allure.step("Press 'butterfly' button");
        butterflyButton();
        Allure.step("Moving to quotes");
        waitForButterfly();
    }

    @Test
    @DisplayName("Log out")
    public void exit() {
        Allure.step("Log out");
        authButton();
        logoutButton();
        Allure.step("Log out completed");
        startPage();
    }
}