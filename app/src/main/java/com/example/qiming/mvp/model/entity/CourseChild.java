package com.example.qiming.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseChild implements Parcelable {
    private long id;
    private long courseId;
    private String courseChildName;

    protected CourseChild(Parcel in) {
        id = in.readLong();
        courseId = in.readLong();
        courseChildName = in.readString();
    }

    public static final Creator<CourseChild> CREATOR = new Creator<CourseChild>() {
        @Override
        public CourseChild createFromParcel(Parcel in) {
            return new CourseChild(in);
        }

        @Override
        public CourseChild[] newArray(int size) {
            return new CourseChild[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeLong(courseId);
        parcel.writeString(courseChildName);
    }
}
