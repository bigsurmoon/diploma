package ru.iteco.fmhandroid.ui;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.checkIfLogin;

import static ru.iteco.fmhandroid.ui.testHelper.MenuHelper.newsSection;
import static ru.iteco.fmhandroid.ui.testHelper.NewsHelper.createNews;
import static ru.iteco.fmhandroid.ui.testHelper.NewsHelper.deleteNews;
import static ru.iteco.fmhandroid.ui.testHelper.NewsHelper.filterDate;
import static ru.iteco.fmhandroid.ui.testHelper.NewsHelper.filterNewsByCategory;
import static ru.iteco.fmhandroid.ui.testHelper.NewsHelper.noNews;

import static ru.iteco.fmhandroid.ui.testHelper.Steps.clickPositive;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.findNews;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.newsWait;
import static ru.iteco.fmhandroid.ui.testHelper.Steps.posAuth;

import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

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
//@RunWith(AndroidJUnit4.class)
@Epic("News")
@DisplayName("News")

public class NewsTest extends BaseTest {

    @Before
    public void loginIfRequired() {
        Allure.step("Authorized and log in if required");
        if (!checkIfLogin()) {
            posAuth();
        }
        newsSection();
    }

    @Test
    @DisplayName("Create news")
    public void createNewsTest() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Объявление";
        createNews(text, now, found);
        Allure.step("Searching for created news");
        newsWait();
        findNews(found + text);
    }


    @Test
    @DisplayName("Filter by 'Date'")
    public void filterByDate() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        createNews(text, now, "Объявление");
        Allure.step("Filtering by date");
        filterDate(now);
        Allure.step("Finding created news");
        newsWait();
        findNews("Объявление" + text);
    }

    @Test
    @DisplayName("Filter by 'Обявление'")
    public void filterByAdv() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Объявление";
        createNews(text, now, found);
        filterNewsByCategory(found, text);
    }

    @Test
    @DisplayName("Filter by 'День рождения'")
    public void filterByBirthday() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "День рождения";
        createNews(text, now, found);
        filterNewsByCategory(found, text);
    }

    @Test
    @DisplayName("Filter by 'Зарплата'")
    public void filterBySalary() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Зарплата";
        createNews(text, now, found);
        filterNewsByCategory(found, text);
    }

    @Test
    @DisplayName("Filter by 'Профсоюз'")
    public void filterNewsByUnion() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Профсоюз";
        createNews(text, now, found);
        filterNewsByCategory(found, text);
    }


    @Test
    @DisplayName("Filter by 'Праздник'")
    public void filterNewsByHoliday() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Праздник";
        createNews(text, now, found);
        filterNewsByCategory(found, text);
    }

    @Test
    @DisplayName("Delete news")
    public void newsDelete() {
        LocalDateTime now = LocalDateTime.now();
        String text = "Test" + now;
        String found = "Объявление";
        createNews(text, now, found);
        Allure.step("Searching for news");
        newsWait();
        findNews((found + text));
        Allure.step("Delete news");
        deleteNews((found + text));
        clickPositive();
        Allure.step("Cant find news");
        noNews((found + text));
    }
}