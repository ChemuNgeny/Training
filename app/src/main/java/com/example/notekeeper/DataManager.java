package com.example.notekeeper;

import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static DataManager ourInstance = null;

    private List<CourseInfo> mCourses = new ArrayList<>();
    private List<NoteInfo> mNotes = new ArrayList<>();

    public static DataManager getInstance(){
        if (ourInstance == null){
            ourInstance= new DataManager();
            ourInstance.initializeCourses();
            ourInstance.initializeExampleNotes();
        }
        return ourInstance;
    }

    public static DataManager getOurInstance() {
        return ourInstance;
    }

    public static void setOurInstance(DataManager ourInstance) {
        DataManager.ourInstance = ourInstance;
    }

    public void setmCourses(List<CourseInfo> mCourses) {
        this.mCourses = mCourses;
    }

    public List<NoteInfo> getmNotes() {
        return mNotes;
    }

    public void setmNotes(ArrayList<NoteInfo> mNotes) {
        this.mNotes = mNotes;
    }

    private void initializeExampleNotes() {
    }

    private void initializeCourses() {
    }

    public String getCurrentUserName(){
        return "Nancy Chemutai";
    }

    public String getCurrentUserEmail(){
        return "chemu@gmail.com";
    }

    public int createNewNote(){
        NoteInfo note = new NoteInfo(null, null,null);
        mNotes.add(note);
        return mNotes.size()-1;
    }

    public int findNote(NoteInfo note){
        for (int index = 0; index < mNotes.size(); index++){
            if (note.equals(mNotes.get(index))){
                return index;
            }
        }
        return -1;
    }

    public List<CourseInfo> getmCourses() {
        return mCourses;
    }

    public void removeNote(int index){
        mNotes.remove(index);
    }

    public CourseInfo getCourse(String id){
        for (CourseInfo course: mCourses){
            if (id.equals(course.getmCourseId())){
                return course;
            }
        }
        return null;
    }

    public CourseInfo getmCourses(String originalNoteCourseId) {
        return null;
    }

    public void initializeExampleNote() {

    }

    public int createNewNote(CourseInfo course, String noteTitle, String noteText) {
        int index = createNewNote();
        NoteInfo note = getmNotes().get(index);
        note.setmCourses(course);
        note.setmTitle(noteTitle);
        note.setmText(noteText);

        return index;
    }
}
