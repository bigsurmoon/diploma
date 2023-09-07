package ru.iteco.fmhandroid.ui.testHelper;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasSibling;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.core.AllOf.allOf;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewAction.waitId;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.chooseDate;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.chooseDateEnd;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.clickOnElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.execPopup;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.findElement;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.scroll;
import static ru.iteco.fmhandroid.ui.testHelper.CustomViewMatchers.waitForElement;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.contrib.PickerActions;

import java.time.LocalDateTime;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class NewsHelper {

    @Step("Create news")
    public static void createNews(String text, LocalDateTime now, String popup) {
        Allure.step("Create news");
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        execPopup(popup);
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(typeText(text));
        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(isAssignableFrom(DatePicker.class))
                .perform(PickerActions.setDate(
                        now.getYear(),
                        now.getMonthValue(),
                        now.getDayOfMonth())
                );
        onView(withText(android.R.string.ok)).perform(click());
        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
        onView(isAssignableFrom(TimePicker.class))
                .perform(PickerActions.setTime(
                        now.getHour(),
                        now.getMinute())
                );
        onView(withText(android.R.string.ok)).perform(click());
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(typeText("Hi" + now), closeSoftKeyboard());
        onView(withText(R.string.save)).perform(click());
    }

    @Step("Filter news by category")
    public static void filterNewsByCategory(String found, String text) {
        Allure.step("Filter news by category");
        onView(withId(R.id.filter_news_material_button)).perform(click());
        onView(withId(R.id.news_item_category_text_auto_complete_text_view)).perform(click());
        execPopup(found);
        onView(withId(R.id.filter_button)).perform(click());
        onView(isRoot()).perform(waitId(R.id.news_list_recycler_view, 10000));
        onView(withId(R.id.news_list_recycler_view))
                .perform(scrollTo(hasDescendant(withText(found + text))))
                .check(matches(isDisplayed()));
    }

    public static void clickOkButton() {
        onView(withText(android.R.string.ok)).perform(click());
    }

    public static void deleteNews(String text) {
        onView(allOf(hasSibling(withText(text)), withId(R.id.delete_news_item_image_view))).perform(click());
    }

    public static void filterDate(LocalDateTime now) {
        clickOnElement(R.id.filter_news_material_button);
        waitForElement(R.id.news_item_publish_date_start_text_input_edit_text);
        clickOnElement(R.id.news_item_publish_date_start_text_input_edit_text);
        chooseDate(now);
        clickOkButton();
        clickOnElement(R.id.news_item_publish_date_end_text_input_edit_text);
        chooseDateEnd(now);
        clickOkButton();
        clickOnElement(R.id.filter_button);
    }

    public static void noNews(String text) {
        try {
            scroll(R.id.news_list_recycler_view, (text));
        } catch (PerformException ex) {
            findElement(R.id.news_list_recycler_view);
        }
    }
}