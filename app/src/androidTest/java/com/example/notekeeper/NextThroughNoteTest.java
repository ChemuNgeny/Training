package com.example.notekeeper;

import android.support.design.widget.NavigationView;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

public class NextThroughNoteTest {
    @Rule
    public ActivityTestRule<NavActivity> mActivityTestRule = new ActivityTestRule(NavActivity.class);

    @Test
    public void NextThroughNotes(){
        onView(withId(R.id.drawer_layout)).perform(DrawerAction.open());//opens navigation bar
        onView(withId(R.id.nav_view)).perform(NavigationViewActions.navigateTo(R.id.nav_notes));//selects nav option

        onView(withId(R.id.list_notes)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));//selects first note

        ArrayList<NoteInfo> notes = DataManager.getInstance().getmNotes();
        for (int index = 0; index < notes.size(); index ++){
            NoteInfo note = notes.get(index);

            onView(withId(R.id.spinner_courses)).check(matches(withSpinnerText(note.getmCourses().getmTitle())));
            onView(withId(R.id.text_note_title)).check(matches(withText(note.getmTitle())));
            onView(withId(R.id.text_note_text)).check(matches(withText(note.getmText())));

            if (index < notes.size() -1){
                onView(allOf(withId(R.id.action_next), isEnabled())).perform(click());
            }
            onView(withId(R.id.action_next)).check(matches(not(isEnabled())));
            pressBack();
        }

    }
}