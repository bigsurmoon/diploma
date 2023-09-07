package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.claimAfterAddExec;
import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.claimBeforeAddExec;
import static ru.iteco.fmhandroid.ui.testHelper.ClaimHelper.findClaim;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.checkIfLogin;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.execPopup;

import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.claimSection;

import static ru.iteco.fmhandroid.ui.testHelper.Steps.chooseExec;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.claimEdit;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.claimInProgress;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.claimIsCanceled;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.claimOpen;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.clickOnFilter;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.clickSaveButton;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.closeClaimKeyboard;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.filterInProgress;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.filterOk;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.posAuth;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.statusButton;

import androidx.test.filters.LargeTest;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.time.LocalDateTime;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Description;
import io.qameta.allure.kotlin.Epic;
import io.qameta.allure.kotlin.junit4.DisplayName;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
@Epic("Claims")
@DisplayName("Claims")
public class ClaimsTest extends BaseTest {

    @Before
    public void loginIfRequired() {
        Allure.step("Authorized and log in if required");
        if (!checkIfLogin()) {
            posAuth();
        }
        claimSection();
    }

    @Test
    @DisplayName("Creating claim with executor")
    @Description("Creating claim with executor sets its status to 'In progress'")
    public void withExec() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        chooseExec();
        execPopup("Ivanov Ivan Ivanovich");
        claimAfterAddExec(now);
        findClaim(text);
        Allure.step("Claim created with In progress status");
        claimInProgress();
    }

    @Test
    @DisplayName("Creating claim without executor")
    @Description("Creating claim without executor sets its status to Open")
    public void withoutExec() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        findClaim(text);
        Allure.step("Claim created with 'Open' status");
        claimOpen();
    }

    @Test
    @DisplayName("Filter Claims")
    @Description("Filter Claims")
    public void filterClaim() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        Allure.step("Filtering claims");
        clickOnFilter();
        filterInProgress();
        filterOk();
        findClaim(text);
    }

    @Test
    @DisplayName("Change claim status to Closed")
    @Description("Closing a claim")
    public void openToClose() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        findClaim(text);
        Allure.step("Cancelling claim");
        statusButton();
        execPopup("Cancel");
        Allure.step("Claim is cancelled");
        claimIsCanceled();
    }

    @Test
    @DisplayName("Take claim")
    @Description("Change claim status from 'Open' to 'In progress'")
    public void openToWork() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        findClaim(text);
        Allure.step("Change claim status to 'In progress'");
        statusButton();
        execPopup("take to work");
        Allure.step("Claim status changed to 'In progress'");
        claimInProgress();
    }

    @Test
    @DisplayName("Change claim info")
    @Description("Change claim info")
    public void editClaim() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        claimBeforeAddExec(text);
        claimAfterAddExec(now);
        findClaim(text);
        Allure.step("Changing claim info");
        claimEdit();
        chooseExec();
        execPopup("Ivanov Ivan Ivanovich");
        closeClaimKeyboard();
        clickSaveButton();
        Allure.step("Claim moved to the required status");
        claimInProgress();
    }
}