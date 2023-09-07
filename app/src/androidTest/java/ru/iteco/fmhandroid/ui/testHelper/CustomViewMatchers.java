package ru.iteco.fmhandroid.ui.testHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.AllOf.allOf;


import static ru.iteco.fmhandroid.ui.testHelper.CustomViewAction.waitId;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewAction.waitText;

import android.view.View;
import android.widget.DatePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.RootMatchers;

import com.google.android.material.textfield.TextInputEditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.time.LocalDateTime;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CustomViewMatchers {
    @Step("Authorization")

    public static void auth(String login, String password) {
        onView(isRoot()).perform(waitId(R.id.login_text_input_layout, 5000));
        onView(allOf(
                withClassName(containsString(TextInputEditText.class.getSimpleName())),
                isDescendantOfA(withId(R.id.login_text_input_layout))
        )).perform(typeText(login));
        onView(allOf(
                withClassName(containsString(TextInputEditText.class.getSimpleName())),
                isDescendantOfA(withId(R.id.password_text_input_layout))
        )).perform(typeText(password), closeSoftKeyboard());
        onView(withText(R.string.sign_in)).perform(click());
    }

    @Step("Not Authorized")
    public static boolean checkIfLogin() {
        boolean isLogin = true;
        try {
            onView(isRoot()).perform(waitId(R.id.main_swipe_refresh, 7000));
        } catch (RuntimeException ex) {
            isLogin = false;
        }
        return isLogin;
    }

    @Step("Authorized")
    public static void authExists() {
        onView(isRoot()).perform(waitId(R.id.main_swipe_refresh, 7000)).check(matches(isDisplayed()));
    }

    public static void execPopup(String name) {
        Allure.step("Choose " + (name) + "");
        onView(withText(name))
                .inRoot(RootMatchers.isPlatformPopup())
                .perform(click());
    }

    public static void clickOnString(final int matcher) {
        onView(withText(matcher)).perform(click());
    }

    public static void clickOnFoundString(final int matcher) {
        onView(isRoot()).perform(waitText(matcher, 10000)).perform(click());
    }

    public static void clickOnFoundElement(int matcher) {
        onView(withId(matcher)).check(matches(isDisplayed())).perform(click());
    }

    public static void waitForString(final int matcher) {
        onView(isRoot()).perform(waitText(matcher, 10000)).check(matches(isDisplayed()));
    }

    public static void findElement(final int matcher) {
        onView(withId(matcher)).check(matches(isDisplayed()));
    }

    public static void changeIndex(final int matcher) {
        onView(withIndex(withId(matcher), 0)).perform(click());
    }

    public static void clickOnElement(final int matcher) {
        onView(withId(matcher)).perform(click());
    }

    public static void closeKeyboard(final int matcher) {
        onView(withId(matcher)).perform(closeSoftKeyboard());
    }

    public static void waitForElement(final int matcher) {
        onView(isRoot()).perform(waitId(matcher, 10000)).check(matches(isDisplayed()));
    }

    public static void scroll(final int matcher, String found) {
        onView(withId(matcher))
                .perform(scrollTo(hasDescendant(withText(found))))
                .check(matches(isDisplayed()));
    }

    public static void chooseDate(LocalDateTime now) {
        onView(isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(
                        now.getYear(),
                        now.getMonthValue(),
                        now.getDayOfMonth() - 1)
                );
    }

    public static void chooseDateEnd(LocalDateTime now) {
        onView(isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(
                        now.getYear(),
                        now.getMonthValue(),
                        now.getDayOfMonth())
                );
    }

    public static Matcher<View> withIndex(final Matcher<View> matcher, final int index) {
        return new TypeSafeMatcher<View>() {
            int currentIndex = 0;

            @Override
            public void describeTo(Description description) {
                description.appendText("with index: ");
                description.appendValue(index);
                matcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                return matcher.matches(view) && currentIndex++ == index;
            }
        };
    }
}