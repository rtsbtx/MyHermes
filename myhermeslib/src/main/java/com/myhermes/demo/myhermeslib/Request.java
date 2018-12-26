package com.myhermes.demo.myhermeslib;

import android.os.Parcel;
import android.os.Parcelable;

public class Request implements Parcelable {

    Object[] methodParams;
    String methodId;

    public Request(Object[] methodParams, String methodId) {
        this.methodParams = methodParams;
        this.methodId = methodId;
    }

    protected Request(Parcel in) {
        methodParams = in.readArray(getClass().getClassLoader());
        methodId = in.readString();
    }

    public static final Creator<Request> CREATOR = new Creator<Request>() {
        @Override
        public Request createFromParcel(Parcel in) {
            return new Request(in);
        }

        @Override
        public Request[] newArray(int size) {
            return new Request[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeArray(methodParams);
        dest.writeString(methodId);
    }



    public Object[] getMethodParams() {
        return methodParams;
    }

    public void setMethodParams(Object[] methodParams) {
        this.methodParams = methodParams;
    }

    public String getMethodId() {
        return methodId;
    }

    public void setMethodId(String methodId) {
        this.methodId = methodId;
    }
}
