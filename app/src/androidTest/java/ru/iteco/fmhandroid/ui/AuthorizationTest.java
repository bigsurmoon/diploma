package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.authExists;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.checkIfLogin;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.waitForString;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.authButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.emptyAuth;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.logoutButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.negAuth;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.posAuth;

import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Authorization")
@DisplayName("Authorization")
public class AuthorizationTest extends BaseTest {

    @Before
    public void logoutIfRequired() {
        Allure.step("Logout if required");
        if (checkIfLogin()) {
            authButton();
            logoutButton();
        }
    }

    @Test
    @DisplayName("Successful Authorization")
    @Description("Authorization with valid username and password")
    public void positiveAuthTest() {
        Allure.step("Authorization with valid username and password");
        posAuth();
        Allure.step("Authorization is completed");
        authExists();
    }

    @Test
    @DisplayName("Authorization not successful")
    @Description("Authorization with invalid username and password")
    public void negativeAuthTest() {
        Allure.step("Authorization with invalid username and password");
        negAuth();
        Allure.step("Authorization is not completed");
        waitForString(R.string.authorization);
    }

    @Test
    @DisplayName("Authorization not successful")
    @Description("Authorization with empty fields")
    public void emptyAuthTest() {
        Allure.step("Authorization with empty fields");
        emptyAuth();
        Allure.step("Authorization is not completed");
        waitForString(R.string.authorization);
    }
}