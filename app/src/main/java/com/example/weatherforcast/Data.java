package com.example.weatherforcast;

import java.util.ArrayList;

public class Data {

    private ArrayList<String> mCities;;
    private static Data Data_Instance = null;

    public Data()
    {
        mCities = new ArrayList<>();
    }

    public void setData(String city)
    {
        mCities.add(city);
    }

    public ArrayList getData()
    {
        return mCities;
    }

    public String getDataItem(int index)
    {
        return mCities.get(index);
    }

    public static Data getDataInstance()
    {
        if(Data_Instance == null)
        {
            Data_Instance = new Data();
        }
        return Data_Instance;
    }
}
