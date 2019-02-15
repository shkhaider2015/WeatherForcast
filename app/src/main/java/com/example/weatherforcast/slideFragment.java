package com.example.weatherforcast;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class slideFragment extends Fragment {

    ArrayList<JSONClass> jsonData;

    public slideFragment()
    {
        jsonData = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.current_weather, container, false);


        ViewPager viewPager = view.findViewById(R.id.slider);
        SlideAdapter slideAdapter = new SlideAdapter(getActivity(), jsonData);
        viewPager.setAdapter(slideAdapter);



        return view;
    }

    public void setJsonData(ArrayList<JSONClass> jsonData)
    {
        this.jsonData = new ArrayList<>();
        this.jsonData = jsonData;
    }
}
