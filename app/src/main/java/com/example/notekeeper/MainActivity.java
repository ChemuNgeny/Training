package com.example.notekeeper;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String NOTE_POSITION = "com.example.notekeeper.NOTE_POSITION";
    public static final String ORIGINAL_NOTE_COURSE_ID = "com.example.notekeeper.ORIGINAL_NOTE_COURSE_ID";
    public static final String ORIGINAL_NOTE_TITLE = "com.example.notekeeper.ORIGINAL_NOTE_TITLE";
    public static final String ORIGINAL_NOTE_TEXT = "com.example.notekeeper.ORIGINAL_NOTE_TEXT";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo note;
    private boolean isNewNote, isCancelling;
    private Spinner spinnerCourses;
    private EditText etTitle, etText;
    private int notePosition;
    private ArrayAdapter<CourseInfo> adapterCourses;
    private String originalNoteCourseId,originalNoteTitle,  originalNoteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        spinnerCourses = findViewById(R.id.spinner_courses);
        List<CourseInfo> course = DataManager.getInstance().getmCourses();
        adapterCourses = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, course);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);

        readDisplayStateValues();

        if (savedInstanceState == null){
            saveOriginalNoteValues();
        } else{
            restoreOriginalNoteValues(savedInstanceState);
        }


        etTitle = findViewById(R.id.text_note_title);
        etText = findViewById(R.id.text_note_text);

        if (!isNewNote)
                 displayNote(spinnerCourses, etTitle, etText);
    }

    private void restoreOriginalNoteValues(Bundle savedInstanceState) {
        originalNoteCourseId = savedInstanceState.getString(ORIGINAL_NOTE_COURSE_ID);
        originalNoteTitle = savedInstanceState.getString(ORIGINAL_NOTE_TITLE);
        originalNoteText = savedInstanceState.getString(ORIGINAL_NOTE_TEXT);
    }

    private void saveOriginalNoteValues() {
        if (isNewNote)
            return;

        originalNoteCourseId = note.getmCourses().getmCourseId();
        originalNoteTitle = note.getmTitle();
        originalNoteText = note.getmText();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterCourses.notifyDataSetChanged();
    }

    private void displayNote(Spinner spinnerCourses, EditText etTitle, EditText etText) {
        List<CourseInfo> courseInfo = DataManager.getInstance().getmCourses();
        int courseIndex = courseInfo.indexOf(note.getmCourses());
        spinnerCourses.setSelection(courseIndex);

        etTitle.setText(note.getmTitle());
        etText.setText(note.getmText());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(NOTE_POSITION,    POSITION_NOT_SET);
        isNewNote = position == POSITION_NOT_SET;
        if (isNewNote){
            creatNewNote();
        }else {
//            Log.i(TAG, "readDisplayStateValues: ");
            DataManager.getInstance().getmNotes().get(position);
        }

    }

    private void creatNewNote() {
        DataManager dm = DataManager.getInstance();
        notePosition = dm.createNewNote();
        note = dm.getmNotes().get(notePosition);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isCancelling) {
            if (isNewNote) {
                DataManager.getInstance().removeNote(notePosition);
            }else{
                storePreviousNoteValues();
            }
        }else{
                saveNote();
            }
    }

    private void storePreviousNoteValues() {
        CourseInfo course = DataManager.getInstance().getmCourses(originalNoteCourseId);
        note.setmCourses(course);
        note.setmTitle(originalNoteTitle);
        note.setmText(originalNoteText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ORIGINAL_NOTE_COURSE_ID, originalNoteCourseId);
        outState.putString(ORIGINAL_NOTE_TITLE, originalNoteTitle);
        outState.putString(ORIGINAL_NOTE_TEXT, originalNoteText);
    }

    private void saveNote() {
        note.setmCourses((CourseInfo) spinnerCourses.getSelectedItem());
        note.setmTitle(etTitle.getText().toString());
        note.setmText(etText.getText().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_mail) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel){
            isCancelling = true;
            finish();
        } else if (id == R.id.action_next){
            movement();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_next);
        int lastNoteIndex = DataManager.getInstance().getmNotes().size() - 1;//disabling the next note menu item
        item.setEnabled(notePosition < lastNoteIndex);
        return super.onPrepareOptionsMenu(menu);
    }

    private void movement() {
        saveNote();
        ++notePosition;
        note = DataManager.getInstance().getmNotes().get(notePosition);
        saveOriginalNoteValues();

        displayNote(spinnerCourses, etTitle, etText);
        isValidateOptionsMenu();

    }

    private void isValidateOptionsMenu() {

    }

    private void sendEmail() {
    CourseInfo course = (CourseInfo) spinnerCourses.getSelectedItem();
    String subject = etTitle.getText().toString();
    String text = "Introduction to PluralSight \""+course.getmTitle()+"\"\n"+etTitle.getText().toString();

    Intent intent = new Intent(Intent.ACTION_SEND);//implicint intent
        intent.setType("message/rfc2822");//identifies our target
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }
}
