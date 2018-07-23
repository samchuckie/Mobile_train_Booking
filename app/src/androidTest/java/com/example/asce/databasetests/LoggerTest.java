package com.example.asce.databasetests;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class LoggerTest {
    @Rule
    public ActivityTestRule<Logger> loggerActivityTestRule = new ActivityTestRule<>(Logger.class);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void FakeLoginTester()
    {
        Espresso.onView(withId(R.id.username)).perform(typeText("Samuelnya@gmail.com"));
        Espresso.onView(withId(R.id.password)).perform(typeText("123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.log_in)).perform(click());

    }
    @Test
    public void RealLoginTester()
    {
        Espresso.onView(withId(R.id.username)).perform(typeText("s@gmail.com"));
        Espresso.onView(withId(R.id.password)).perform(typeText("123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.log_in)).perform(click());

    }
    @Test
    public void reg_user()
    {
        Espresso.onView(withId(R.id.reg_user)).perform(click());
    }
}