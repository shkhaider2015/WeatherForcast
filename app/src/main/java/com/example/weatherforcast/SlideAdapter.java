package com.example.weatherforcast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;


public class SlideAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;
    private Data data;
    private ArrayList<JSONClass> jsonData;
    private static final String TAG = "SlideAdapter";
    //private final String degree = "\u00b0";
    private int[] wallpapers = new int[4];

    public SlideAdapter(Context context, ArrayList<JSONClass> jsonData)
    {
        this.context = context;
        data = Data.getDataInstance();
        wallpapers = new int[]{ R.drawable.a1, R.drawable.a2 };
        this.jsonData = jsonData;
    }

    @Override
    public int getCount() {
        return data.getData().size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide, container, false);


        ArrayList<WeatherData> weatherDataArrayList = jsonData.get(position).getWeatherForcast();

        TextView city, temp, humidity, condition, wind;
        ImageView icon;
        String cityName = data.getDataItem(position);
        RelativeLayout parent;
        LinearLayout linearLayout;

        city = view.findViewById(R.id.city);
        temp = view.findViewById(R.id.temprature);
        parent = view.findViewById(R.id.parent);
        linearLayout = view.findViewById(R.id.linear_layout);
        humidity = view.findViewById(R.id.humidity);
        condition = view.findViewById(R.id.condition);
        wind = view.findViewById(R.id.wind);

        TextView mDay2Name, mDay2Temp, mDay2Humd, mDay3Name, mDay3Temp, mDay3Humd , mDay4Name, mDay4Temp, mDay4Humd;
        TextView mDay5Name, mDay5Temp, mDay5Humd ,mDay6Name, mDay6Temp, mDay6Humd ,mDay7Name, mDay7Temp, mDay7Humd;;

        mDay2Name = view.findViewById(R.id.day2_name);
        mDay2Temp = view.findViewById(R.id.day2_temp);
        mDay2Humd = view.findViewById(R.id.day2_humd);

        mDay3Name = view.findViewById(R.id.day3_name);
        mDay3Temp = view.findViewById(R.id.day3_temp);
        mDay3Humd = view.findViewById(R.id.day3_humd);

        mDay4Name = view.findViewById(R.id.day4_name);
        mDay4Temp = view.findViewById(R.id.day4_temp);
        mDay4Humd = view.findViewById(R.id.day4_humd);

        mDay5Name = view.findViewById(R.id.day5_name);
        mDay5Temp = view.findViewById(R.id.day5_temp);
        mDay5Humd = view.findViewById(R.id.day5_humd);

        mDay6Name = view.findViewById(R.id.day6_name);
        mDay6Temp = view.findViewById(R.id.day6_temp);
        mDay6Humd = view.findViewById(R.id.day6_humd);

        mDay7Name = view.findViewById(R.id.day7_name);
        mDay7Temp = view.findViewById(R.id.day7_temp);
        mDay7Humd = view.findViewById(R.id.day7_humd);




        icon = view.findViewById(R.id.icon);

        //icon.setImageResource(R.drawable.sun);

        //parent.setBackgroundResource(wallpapers[4]);

        city.setText(cityName);


        for(int i=0; i<weatherDataArrayList.size(); i++)
        {
            WeatherData weatherData = weatherDataArrayList.get(i);

            if(i==0)
            {
                temp.setText(String.valueOf(weatherData.getmTemprature()));
                humidity.setText(String.valueOf(weatherData.getmHUmidity()));
                condition.setText(weatherData.getmCondition());
                wind.setText(String.valueOf(weatherData.getmWind()));
                icon.setImageDrawable(weatherData.getmIcon());

            }

            switch (i)
            {
                case 1:
                    mDay2Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay2Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay2Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
                case 2:
                    mDay3Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay3Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay3Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
                case 3:
                    mDay4Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay4Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay4Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
                case 4:
                    mDay5Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay5Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay5Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
                case 5:
                    mDay6Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay6Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay6Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
                case 6:
                    mDay7Name.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay7Temp.setText(String.valueOf(weatherData.getmTemprature()));
                    mDay7Humd.setText(String.valueOf(weatherData.getmHUmidity()));
                    break;
            }

        }





        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout)object);
    }

    private Bitmap getIcon(File mIcon)
    {
        Bitmap bitmap = null;

        if(mIcon.exists())
        {
            Log.d(TAG, "getIcon: Yes!! Image exists");
            bitmap = BitmapFactory.decodeFile(mIcon.getAbsolutePath());
        }


            Log.d(TAG, "getIcon: Image is not exists");

        return bitmap;
    }


    /*
    private int getRandomNumber()
    {
        Random random = new Random();
        return random.nextInt(wallpapers.length);
    }
    */
}
