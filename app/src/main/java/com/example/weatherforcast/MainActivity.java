package com.example.weatherforcast;

import android.content.Context;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    ThreadForJSON threadForJSON;
    slideFragment slideFragment;
    Button mCheckNow;


    public MainActivity()
    {
        threadForJSON = new ThreadForJSON(this);
        slideFragment = new slideFragment();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        threadForJSON.start();

        mCheckNow = findViewById(R.id.check_now);
        mCheckNow.setOnClickListener(this);

        slideFragment.setJsonData(threadForJSON.getJsonClasses());






    }


    @Override
    public void onClick(View v)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.add(R.id.fragment_container, slideFragment);
        fragmentTransaction.commit();
        fragmentTransaction.addToBackStack(null);

        mCheckNow.setText("back");


    }
}

class ThreadForJSON extends Thread
{
    private ArrayList<JSONClass> jsonClasses;
    private Data data;
    private Context context;

    public ThreadForJSON(Context context)
    {
        this.context = context;
        jsonClasses = new ArrayList<>();
        data = Data.getDataInstance();
    }

    @Override
    public void run()
    {

        mSetData();
        mFetchJSONData();

    }

    private void mFetchJSONData()
    {
        for(int i=0; i<data.getData().size(); i++)
        {
            JSONClass jsonClass = new JSONClass(context, data.getDataItem(i));
            jsonClasses.add(i, jsonClass);
        }
    }

    private void mSetData()
    {
        data.setData("karachi");
        data.setData("peshawar");
        data.setData("islamabad");
    }

    public ArrayList<JSONClass> getJsonClasses() {
        return jsonClasses;
    }
}