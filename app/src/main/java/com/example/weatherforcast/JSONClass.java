package com.example.weatherforcast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class JSONClass {

    private static final String TAG = "JSONClass";
    private String mCityName;
    private RequestQueue mQueue;
    private ArrayList<WeatherData> weatherForcast;

    public JSONClass(Context context, String mCityName) {
        this.mCityName = mCityName;
        weatherForcast = new ArrayList<>();
        mQueue = Volley.newRequestQueue(context);
        JSONAccess();
    }

    private void strictModeAlklow()
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void JSONAccess()
    {
        String mURL1 = "https://api.openweathermap.org/data/2.5/forecast?q=";
        String mURL2 = "&units=metric&appid=58dbadc09a2eb83225b7d177ba78ab0a";

        String mURL = mURL1 + mCityName + mURL2;

        JsonObjectRequest mObjectRequest = new JsonObjectRequest(Request.Method.GET, mURL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Log.d(TAG, "onResponse: Run");

                        try {

                            JSONArray mList = response.getJSONArray("list");
                            Log.d(TAG, "onResponse: " + mList.length());
                            for(int i=0; i<mList.length(); i++)
                            {
                                if( (i+1) %8 != 0 && i != 0)
                                {
                                    continue;
                                }


                                WeatherData mWeatherData = new WeatherData();

                                JSONObject index = mList.getJSONObject(i);
                                JSONObject main = index.getJSONObject("main");
                                JSONObject wind = index.getJSONObject("wind");
                                JSONObject weatherMain = index.getJSONArray("weather").getJSONObject(0);

                                mWeatherData.setmTemprature(main.getInt("temp"));
                                mWeatherData.setmHUmidity(main.getInt("humidity"));
                                mWeatherData.setmWind(wind.getDouble("speed"));
                                mWeatherData.setmCondition(weatherMain.getString("main"));

                                String URL1 = "http://openweathermap.org/img/w/";
                                String URL2 = ".png";
                                String URL = URL1 + weatherMain.getString("icon") + URL2;

                                Drawable drawableIcon = Drawable.createFromStream(((InputStream) new URL(URL).getContent()), "src");
                                mWeatherData.setmIcon(drawableIcon);



                                weatherForcast.add(mWeatherData);

                            }

                        } catch (JSONException e)
                        {
                            Log.d(TAG, "onResponseException: " + e.getMessage());
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            Log.d(TAG, "onResponse: " + e.getMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.d(TAG, "onResponse: " + e.getMessage());
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: Volley Errorr" + error.getMessage());
                    }
                });

        mQueue.add(mObjectRequest);

    }

    public ArrayList<WeatherData> getWeatherForcast() {
        return weatherForcast;
    }

}
