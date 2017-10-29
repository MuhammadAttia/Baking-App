package com.example.muhammad.bakingapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

/**
 * Created by Muhammad Attia on 17/06/2017.
 * Steps Class
 */


public class Steps implements Parcelable {
    private int mID;
    private String mShortDescription;
    private String mDescription;
    private String mVideoURL;
    private String mThumbnailURL;

    public Steps(JSONObject stepJason) {
        this. mID = stepJason.optInt("id");
        this.mShortDescription = stepJason.optString("shortDescription");
        this.mDescription = stepJason.optString("description");
        this.mVideoURL = stepJason.optString("videoURL");
        this.mThumbnailURL = stepJason.optString("thumbnailURL");
    }

    public int getmID() {
        return mID;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmVideoURL() {
        return mVideoURL;
    }

    public String getmThumbnailURL() {
        return mThumbnailURL;
    }

    protected Steps(Parcel in) {
        mID = in.readInt();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mVideoURL = in.readString();
        mThumbnailURL = in.readString();
    }

    public static final Creator<Steps> CREATOR = new Creator<Steps>() {
        @Override
        public Steps createFromParcel(Parcel in) {
            return new Steps(in);
        }

        @Override
        public Steps[] newArray(int size) {
            return new Steps[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(mID);
        parcel.writeString(mShortDescription);
        parcel.writeString(mDescription);
        parcel.writeString(mVideoURL);
        parcel.writeString(mThumbnailURL);
    }
}