package com.myhermes.demo.myhermeslib;

import android.os.Parcel;
import android.os.Parcelable;

public class Response implements Parcelable {

    Object returnData;

    public Response() {
    }

    public Response(String returnData) {
        this.returnData = returnData;
    }

    protected Response(Parcel in) {
        returnData = in.readValue(getClass().getClassLoader());
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(returnData);
    }

    public void readFromParcel(Parcel out){
        returnData = out.readValue(getClass().getClassLoader());
    }

    public void setReturnData(Object returnData) {
        this.returnData = returnData;
    }

    public Object getReturnData() {
        return returnData;
    }
}
