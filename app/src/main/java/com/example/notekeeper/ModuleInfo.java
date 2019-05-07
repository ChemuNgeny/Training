package com.example.notekeeper;

import android.os.Parcel;
import android.os.Parcelable;

class ModuleInfo implements Parcelable{
//    public static final Creator<ModuleInfo> CREATOR = "CREATOR";
    public ModuleInfo getModuleId;
    private String moduleId;
    private String mTitle;
    private boolean isComplete = false;

    public ModuleInfo(ModuleInfo getModuleId, String moduleId, String mTitle, boolean isComplete) {
        this.getModuleId = getModuleId;
        this.moduleId = moduleId;
        this.mTitle = mTitle;
        this.isComplete = isComplete;
    }

    //    public void setComplete(Object status) {
//    }
    private ModuleInfo(Parcel source){
        moduleId = source.readString();
        mTitle = source.readString();
        isComplete = source.readByte() == 1;
    }

    public static final Creator<ModuleInfo> CREATOR = new Creator<ModuleInfo>() {
        @Override
        public ModuleInfo createFromParcel(Parcel in) {
            return new ModuleInfo(in);
        }

        @Override
        public ModuleInfo[] newArray(int size) {
            return new ModuleInfo[size];
        }
    };

    public ModuleInfo getGetModuleId() {
        return getModuleId;
    }

    public void setGetModuleId(ModuleInfo getModuleId) {
        this.getModuleId = getModuleId;
    }

    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(getModuleId, flags);
        dest.writeString(moduleId);
        dest.writeString(mTitle);
        dest.writeByte((byte) (isComplete ? 1 : 0));
    }

    public void setComplete(int status) {
    }
}
