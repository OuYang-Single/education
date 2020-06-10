package com.example.qiming.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class addCourse implements Parcelable {
    private long addCourseId;
    private long userId;
    private long courseId;

    protected addCourse(Parcel in) {
        addCourseId = in.readLong();
        userId = in.readLong();
        courseId = in.readLong();
    }

    public static final Creator<addCourse> CREATOR = new Creator<addCourse>() {
        @Override
        public addCourse createFromParcel(Parcel in) {
            return new addCourse(in);
        }

        @Override
        public addCourse[] newArray(int size) {
            return new addCourse[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(addCourseId);
        parcel.writeLong(userId);
        parcel.writeLong(courseId);
    }
}
