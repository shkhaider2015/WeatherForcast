package com.example.weatherforcast;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    protected AsyncTaskRunner asyncTaskRunner;
    protected slideFragment slideFragment;
    private static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setData();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        asyncTaskRunner = new AsyncTaskRunner(this);
        ProgressBar progressBar = findViewById(R.id.progressbar);
        TextView load = findViewById(R.id.load);

        asyncTaskRunner.execute();


        Toast.makeText(this, "Wait for Data Retrieving", Toast.LENGTH_LONG).show();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                slideFragment = new slideFragment();
                slideFragment.setJsonData(asyncTaskRunner.getJsonClassesList());
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fragment_container, slideFragment);
                fragmentTransaction.commit();
                fragmentTransaction.addToBackStack(null);



            }
        },10000);





    // onCreate() ends
    }

    public void setData()
    {
        Data dataObject = Data.getDataInstance();
        dataObject.setData("Karachi");
        dataObject.setData("Islamabad");
        dataObject.setData("Peshawar");

        Log.d(TAG, "setData: Data set for Weather");
    }



    private class AsyncTaskRunner extends AsyncTask<Void, Void, Void>
    {
        private Context context;
        private ArrayList<JSONClass> jsonClassesList;
        private Data dataObject;

        public AsyncTaskRunner(Context context)
        {
            this.context = context;
            jsonClassesList = new ArrayList<>();
            dataObject = Data.getDataInstance();
        }


        @Override
        protected Void doInBackground(Void... voids)
        {

            for(int i = 0; i < dataObject.getData().size(); i++)
            {
                JSONClass jsonClass = new JSONClass(context, dataObject.getDataItem(i));
                jsonClassesList.add(i, jsonClass);

            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: ");
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d(TAG, "onPostExecute: -->");
        }

        public ArrayList<JSONClass> getJsonClassesList()
        {
            return jsonClassesList;
        }



    // AsyncTask ends
    }


    // public class ends
}