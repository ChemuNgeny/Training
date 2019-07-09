package com.example.notekeeper;

import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static org.hamcrest.Matchers.*;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.assertion.ViewAssertions.*;

@RunWith(AndroidJUnit4.class)
public class NoteCreationTest {

    static DataManager sDataManager;

    public static void classSetUp() throws Exception{
        sDataManager = DataManager.getInstance();
    }

    //creating UI test
    @Rule
    public ActivityTestRule<NoteListActivity> mNoteListActivity = new ActivityTestRule<>(NoteListActivity.class);

    @Test
    public void createNewNote(){
        final CourseInfo course = sDataManager.getCourse("Java_Lang");
        final String noteTitle = "Test not title";
        final String noteText = "This is the body of our test note";
//        ViewInteraction fabNewnote = onView(withId(R.id.fab));
//        fabNewnote.perform(click());
        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.spinner_courses)).perform(click());
        onData(allOf(instanceOf(CourseInfo.class), equalTo(course))).perform(click());
        onView(withText(R.id.spinner_courses)).check(matches(withSpinnerText(containsString(course.getmTitle()))));

        //select course from the spinner
        onView(withId(R.id.text_note_title)).perform(typeText(noteTitle)).check(matches(withText(containsString(noteTitle ))));
        onView(withId(R.id.text_note_text)).perform(typeText(noteText), closeSoftKeyboard());
        onView(withId(R.id.text_note_text)).check(matches(withText(containsString(noteText))));

        pressBack();

        //get index of the last note on the screen
        int noteIndex = sDataManager.getmNotes().size() -1;
        NoteInfo note = sDataManager.getmNotes().get(noteIndex);
        assertEquals(course, note.getmCourses());
        assertEquals(noteTitle, note.getmTitle());
        assertEquals(noteText, note.getmText());
    }
    //noteListActivityTest-working with android tools and testing-understanding instrumental testing
}