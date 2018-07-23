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
@RunWith(AndroidJUnit4.class)
public class RegisterTest {

    @Rule
    public ActivityTestRule<Register> loggerActivityTestRule = new ActivityTestRule<>(Register.class);
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void FakeSignupTester()
    {
        Espresso.onView(withId(R.id.new_username)).perform(typeText("Samuelnya@gmail.com"));
        Espresso.onView(withId(R.id.new_username)).perform(typeText("123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.confirm_password)).perform(typeText("ksakldk"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.sign_newuser)).perform(click());

    }
    @Test
    public void RealSignupTester()
    {
        Espresso.onView(withId(R.id.new_username)).perform(typeText("Samuelnya@gmail.com"));
        Espresso.onView(withId(R.id.new_username)).perform(typeText("123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.confirm_password)).perform(typeText("123456"),closeSoftKeyboard());
        Espresso.onView(withId(R.id.sign_newuser)).perform(click());

    }

    @Test
    public void signup_user()
    {
        Espresso.onView(withId(R.id.sign_newuser)).perform(click());
    }
}