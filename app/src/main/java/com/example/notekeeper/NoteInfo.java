package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

class NoteInfo implements Parcelable {

    private CourseInfo mCourses;
    private String mTitle;
    private String mText;

    public NoteInfo(CourseInfo mCourses, String mTitle, String mText) {
        this.mCourses = mCourses;
        this.mTitle = mTitle;
        this.mText = mText;
    }

    private NoteInfo(Parcel source) {
        mCourses = source.readParcelable(CourseInfo.class.getClassLoader());
        mTitle = source.readString();
        mText = source.readString();
    }

    public CourseInfo getmCourses() {
        return mCourses;
    }

    public void setmCourses(CourseInfo mCourses) {
        this.mCourses = mCourses;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    private String getCompareKey(){
        return mCourses.getmCourseId()+"|"+mTitle+"|"+mText;
    }

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        NoteInfo that = (NoteInfo) o;

        return getCompareKey().equals(that.getCompareKey());
    }

    public int hashCode(){
        return getCompareKey().hashCode();
    }

    public String toString(){
        return getCompareKey();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mCourses, 0);
        dest.writeString(mTitle);
        dest.writeString(mText);
    }

    public final static Parcelable.Creator<NoteInfo> CREATOR= new Parcelable.Creator<NoteInfo>(){

        @Override
        public NoteInfo createFromParcel(Parcel source) {
            return new NoteInfo(source);
        }

        @Override
        public NoteInfo[] newArray(int size) {
            return new NoteInfo[size];
        }
    };

//    public NoteInfo(Object o, Object o1, Object o2) {
//    }
}
