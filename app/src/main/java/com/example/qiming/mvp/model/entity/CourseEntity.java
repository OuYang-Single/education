package com.example.qiming.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CourseEntity implements Parcelable {
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getBackgroundId() {
        return backgroundId;
    }

    public void setBackgroundId(int backgroundId) {
        this.backgroundId = backgroundId;
    }

    public String getBackgroundAddress() {
        return backgroundAddress;
    }

    public void setBackgroundAddress(String backgroundAddress) {
        this.backgroundAddress = backgroundAddress;
    }

    public List<CourseChild> getCourseChildList() {
        return courseChildList;
    }

    public void setCourseChildList(List<CourseChild> courseChildList) {
        this.courseChildList = courseChildList;
    }

    public List<addCourse> getAddCourseList() {
        return addCourseList;
    }

    public void setAddCourseList(List<addCourse> addCourseList) {
        this.addCourseList = addCourseList;
    }

    private long userId;
    private long courseId;
    private String courseName;
    private int backgroundId;
    private String backgroundAddress;
    private List<CourseChild> courseChildList;
    private List<addCourse> addCourseList;
    public CourseEntity(){}
    public CourseEntity(String courseName,int backgroundId){
        this.backgroundId=backgroundId;
        this.courseName=courseName;
    }
    protected CourseEntity(Parcel in) {
        userId = in.readLong();
        courseId = in.readLong();
        courseName = in.readString();
        backgroundId = in.readInt();
        backgroundAddress = in.readString();
        courseChildList = in.createTypedArrayList(CourseChild.CREATOR);
        addCourseList = in.createTypedArrayList(addCourse.CREATOR);
    }

    public static final Creator<CourseEntity> CREATOR = new Creator<CourseEntity>() {
        @Override
        public CourseEntity createFromParcel(Parcel in) {
            return new CourseEntity(in);
        }

        @Override
        public CourseEntity[] newArray(int size) {
            return new CourseEntity[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(userId);
        parcel.writeLong(courseId);
        parcel.writeString(courseName);
        parcel.writeInt(backgroundId);
        parcel.writeString(backgroundAddress);
        parcel.writeTypedList(courseChildList);
        parcel.writeTypedList(addCourseList);
    }
}
