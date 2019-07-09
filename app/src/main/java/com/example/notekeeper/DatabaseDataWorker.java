package com.example.notekeeper;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseDataWorker {
    private SQLiteDatabase mdb;

    public DatabaseDataWorker(SQLiteDatabase mdb) {
        this.mdb = mdb;
    }

    public void insertCourses(){
        insertCourse("android_intents", "Android Programming with Intents");
        insertCourse("android_async", "Android Async Programming and services");
        insertCourse("java_lang", "Java Fundamentals: The Java Language");
        insertCourse("java_core", "Java Fundamentals: The Core Platform");
    }

    public void insertSampleNotes(){
        insertNote("android_intents", "Dynamic intent resolution", "Wow intents allow components to be....");
        insertNote("android_intents", "Delegating intents", "Pending intents are powerful: they delegate....");

        insertNote("android_async", "Service default threads", "Did you know that by default an Android.....?");
        insertNote("android_async", "Long running operations", "Foreground services can be tied to.....");

        insertNote("java_lang", "Parameters", "Leverage variable-length parameter lists?");
        insertNote("java_lang", "Anonymous classes", "Anonymous classes simplify implementing one-use ty....");

        insertNote("java_core", "Compiler options", "The -jar option isn't compatible with the -cp");
        insertNote("java_core", "Serialization", "Remember to include serialVersionUID to assure version");
    }
}
