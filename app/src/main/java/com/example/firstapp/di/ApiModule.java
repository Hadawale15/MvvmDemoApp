package com.example.firstapp.di;

import com.example.firstapp.model.ApiInterface;
import com.example.firstapp.di.DaggerApiComponents;
import com.example.firstapp.model.CountryServices;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ApiModule {

    public static String BASE_URL="https://raw.githubusercontent.com";

   @Provides
    public ApiInterface apiInterface()
    {
        return new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()
                .create(ApiInterface.class);
    }

    @Provides
    public CountryServices countryServices()
    {
        return CountryServices.getInstance();
    }
}
