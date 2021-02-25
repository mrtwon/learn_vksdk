package com.example.firstvk;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

public class ParceableJava implements Parcelable {
    View mView;
    public ParceableJava(View view){
        this.mView = view;
    }
    protected ParceableJava(Parcel in) {
        mView = (View) in.readValue(mView.getClass().getClassLoader());
    }

    public static final Creator<ParceableJava> CREATOR = new Creator<ParceableJava>() {
        @Override
        public ParceableJava createFromParcel(Parcel in) {
            return new ParceableJava(in);
        }

        @Override
        public ParceableJava[] newArray(int size) {
            return new ParceableJava[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    dest.writeValue(mView);
    }
}
