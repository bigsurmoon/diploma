package ru.iteco.fmhandroid.ui;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import io.qameta.allure.android.rules.ScreenshotRule;
import io.qameta.allure.android.runners.AllureAndroidJUnit4;


@RunWith(AllureAndroidJUnit4.class)
public class BaseTest {

    @Rule
    public RuleChain chain = RuleChain.outerRule(new ActivityTestRule<>(AppActivity.class))
            .around(new ScreenshotRule(ScreenshotRule.Mode.FAILURE, "ss_end"));
}