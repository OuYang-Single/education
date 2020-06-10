package com.example.qiming.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class OptionEntity implements Parcelable {
    private long Id;
    private long AimsId;
    private int JumpType;
    private int ImageId;
    private String ImageName;
    private String ImageAddress;
    private String WebsiteAddress;
    private String Name;
    private int NameId;

    public  OptionEntity () {
    }
    public OptionEntity(int ImageId,String Name,int NameId) {
        this.ImageId=ImageId;
        this.Name=Name;
        this.NameId=NameId;
    }
    protected OptionEntity(Parcel in) {
        Id = in.readLong();
        AimsId = in.readLong();
        JumpType = in.readInt();
        ImageId = in.readInt();
        ImageName = in.readString();
        ImageAddress = in.readString();
        WebsiteAddress = in.readString();
        Name = in.readString();
    }

    public static final Creator<OptionEntity> CREATOR = new Creator<OptionEntity>() {
        @Override
        public OptionEntity createFromParcel(Parcel in) {
            return new OptionEntity(in);
        }

        @Override
        public OptionEntity[] newArray(int size) {
            return new OptionEntity[size];
        }
    };

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getAimsId() {
        return AimsId;
    }

    public void setAimsId(long aimsId) {
        AimsId = aimsId;
    }

    public int getJumpType() {
        return JumpType;
    }

    public void setJumpType(int jumpType) {
        JumpType = jumpType;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getImageName() {
        return ImageName;
    }

    public void setImageName(String imageName) {
        ImageName = imageName;
    }

    public String getImageAddress() {
        return ImageAddress;
    }

    public void setImageAddress(String imageAddress) {
        ImageAddress = imageAddress;
    }

    public String getWebsiteAddress() {
        return WebsiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        WebsiteAddress = websiteAddress;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(Id);
        parcel.writeLong(AimsId);
        parcel.writeInt(JumpType);
        parcel.writeInt(ImageId);
        parcel.writeString(ImageName);
        parcel.writeString(ImageAddress);
        parcel.writeString(WebsiteAddress);
        parcel.writeString(Name);
    }

    public int getNameId() {
        return NameId;
    }

    public void setNameId(int nameId) {
        NameId = nameId;
    }
}
