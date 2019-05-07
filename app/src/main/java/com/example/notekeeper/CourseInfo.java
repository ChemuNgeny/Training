package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class CourseInfo implements Parcelable {

    private String mCourseId;
    private String mTitle;
    private final List<ModuleInfo> mModules;

    public CourseInfo(String mCourseId, String mTitle, List<ModuleInfo> mModules) {
        this.mCourseId = mCourseId;
        this.mTitle = mTitle;
        this.mModules = mModules;
    }

    private CourseInfo(Parcel in) {
        mCourseId = in.readString();
        mTitle = in.readString();
        mModules = new ArrayList<>();
        in.readTypedList(mModules, ModuleInfo.CREATOR);
    }

    public static final Creator<CourseInfo> CREATOR = new Creator<CourseInfo>() {
        @Override
        public CourseInfo createFromParcel(Parcel in) {
            return new CourseInfo(in);
        }

        @Override
        public CourseInfo[] newArray(int size) {
            return new CourseInfo[size];
        }
    };

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public List<ModuleInfo> getmModules() {
        return mModules;
    }

    public boolean [] getModulesCompletionStatus() {
        boolean[] status = new boolean[mModules.size()];

        for (int i = 0; i < mModules.size(); i++){
            status[i] = mModules.get(i).isComplete();

            return status;
        }
        return null;
    }

    public void setmModulesCompletionStatus(boolean[] status){
        for (int i = 0; i < mModules.size(); i++){
            mModules.get(i).setComplete(status(i));
        }
    }

    private int status(int i) {
        return i;
    }

    public ModuleInfo getModule(String moduleId){
        for (ModuleInfo moduleInfo:mModules){
            if (moduleInfo.equals(moduleInfo.getModuleId)){
                return moduleInfo;
            }
        }
        return null;
    }

    public boolean equals(Object o){
        if (this == o){
            return true;
        }
            if (o == null || getClass() != o.getClass()){
                return false;
            }
        CourseInfo that = (CourseInfo) o;

            return mCourseId.equals(that.mCourseId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mCourseId);
        dest.writeString(mTitle);
    }
}
