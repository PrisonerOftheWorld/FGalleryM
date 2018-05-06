package com.devilsoftware;

import android.app.Application;

import com.devilsoftware.fgallery.API.PixAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    Retrofit retrofit;
    private static PixAPI pixAPI;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        pixAPI = retrofit.create(PixAPI.class);
    }

    public static PixAPI getAPI(){
        return pixAPI;
    }

}
