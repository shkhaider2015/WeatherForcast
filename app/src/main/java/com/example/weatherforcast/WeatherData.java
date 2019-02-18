package com.example.weatherforcast;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.StrictMode;

import java.io.File;
import java.net.URL;


public class WeatherData {

    private int mTemprature;
    private int mHUmidity;
    private double mWind;
    private String mCondition;
    private Drawable mIcon = null;


    public Drawable getmIcon() {
        return mIcon;
    }

    public void setmIcon(Drawable mIcon) {
        this.mIcon = mIcon;
    }

    public int getmTemprature() {
        return mTemprature;
    }

    public void setmTemprature(int mTemprature) {
        this.mTemprature = mTemprature;
    }

    public int getmHUmidity() {
        return mHUmidity;
    }

    public void setmHUmidity(int mHUmidity) {
        this.mHUmidity = mHUmidity;
    }

    public double getmWind() {
        return mWind;
    }

    public void setmWind(double mWind) {
        this.mWind = mWind;
    }

    public String getmCondition() {
        return mCondition;
    }

    public void setmCondition(String mCondition) {
        this.mCondition = mCondition;
    }

    public String getAllData()
    {
        String data = "Temp : " + getmTemprature() + " Cond : " + getmCondition() +  " Wind : " + getmWind() + " icon : " + getmIcon();
        return  data;
    }
}
