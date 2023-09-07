package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.auth;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.checkIfLogin;

import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.aboutSection;
import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.claimSection;
import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.mainSection;
import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.newsSection;

import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForAbout;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForClaims;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForMain;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.waitForNews;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Menu")
@DisplayName("Switching Menu sections")

public class MenuTest extends BaseTest {

    @Before
    public void loginIfRequired() {
        Allure.step("Authorized and log in if required");
        if (!checkIfLogin()) {
            auth("login2", "password2");
        }
    }

    @Test
    @DisplayName("to Claims")
    public void claimsMenu() {
        claimSection();
        Allure.step("Claims page is opened");
        waitForClaims();
    }

    @Test
    @DisplayName("to News")
    public void newsMenu() {
        newsSection();
        Allure.step("News page is opened");
        waitForNews();
    }

    @Test
    @DisplayName("to About")
    public void aboutMenu() {
        aboutSection();
        Allure.step("About page is opened");
        waitForAbout();
    }

    @Test
    @DisplayName("to Main")
    public void mainMenu() {
        newsMenu();
        mainSection();
        Allure.step("Main page is opened");
        waitForMain();
    }
}