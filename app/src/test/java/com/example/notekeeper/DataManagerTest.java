package com.example.notekeeper;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;//allows use of class without class name

public class DataManagerTest {

    static DataManager sDataManager;

    @BeforeClass
    public static void classSetUp() throws Exception{
        sDataManager = DataManager.getInstance();
    }

    @Before
    public void setUp() throws Exception{
//        DataManager dm = DataManager.getInstance();
        sDataManager.getmNotes().clear();//before the test runs it should have an empty note
        sDataManager.initializeExampleNote();
    }
    @Test
    public void createNewNote() throws Exception{
//        final DataManager dm = DataManager.getInstance();
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body test of my text note";

        int noteIndex = sDataManager.createNewNote();
        NoteInfo newNote = sDataManager.getmNotes().get(noteIndex);
        newNote.setmCourses(course);
        newNote.setmTitle(noteTitle);
        newNote.setmText(noteText);

        NoteInfo compareNote = sDataManager.getmNotes().get(noteIndex);//contains the things that we put on the note at that point
        assertEquals(compareNote.getmCourses(), course);
        assertEquals(compareNote.getmTitle(), noteTitle);
        assertEquals(compareNote.getmText(), noteText);
//        assertSame(newNote, compareNote);//checks if two references point to the same object
    }

    @Test
    public void findSimilarNotes() throws Exception{
//        final DataManager dm = DataManager.getInstance();
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText1 = "This is the body test of my text note";
        final String noteText2 = "This is the body test of my second text note";

        int noteIndex1 = sDataManager.createNewNote();
        NoteInfo newNote1 = sDataManager.getmNotes().get(noteIndex1);
        newNote1.setmCourses(course);
        newNote1.setmTitle(noteTitle);
        newNote1.setmText(noteText1);

        int noteIndex2 = sDataManager.createNewNote();
        NoteInfo newNote2 = sDataManager.getmNotes().get(noteIndex2);
        newNote2.setmCourses(course);
        newNote2.setmTitle(noteTitle);
        newNote2.setmText(noteText2);

        int foundIndex1 = sDataManager.findNote(newNote1);
        assertEquals(noteIndex1, foundIndex1);

        int foundIndex2 = sDataManager.findNote(newNote2);
        assertEquals(noteIndex2, foundIndex2);
    }

    @Test
    public void createNewNoteOneStepCreation(){
        final CourseInfo course = sDataManager.getCourse("android_async");
        final String noteTitle = "Test note title";
        final String noteText = "This is the body test of my text note";

        int noteIndex = sDataManager.createNewNote(course, noteTitle, noteText);

        //get back a note at index created
        NoteInfo compareNote = sDataManager.getmNotes().get(noteIndex);
        assertEquals(course, compareNote.getmCourses());
        assertEquals(noteTitle, compareNote.getmTitle());
        assertEquals(noteText, compareNote.getmText());
    }
}