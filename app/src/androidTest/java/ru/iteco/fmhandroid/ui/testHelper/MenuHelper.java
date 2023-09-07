package ru.iteco.fmhandroid.ui.testHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.authExists;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnString;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.R;

public class MenuHelper {

    public static void claimSection() {
        Allure.step("Open 'Claim page' from menu");
        authExists();
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText(R.string.claims)).perform(click());
    }

    public static void newsSection() {
        Allure.step("Open 'News page' from menu");
        authExists();
        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText(R.string.news)).perform(click());
    }

    public static void aboutSection() {
        Allure.step("Open 'About page' from menu");
        authExists();
        clickOnElement(R.id.main_menu_image_button);
        clickOnString(R.string.about);
    }

    public static void mainSection() {
        Allure.step("Open 'Main page' from menu");
        clickOnElement(R.id.main_menu_image_button);
        clickOnString(R.string.main);
    }
}