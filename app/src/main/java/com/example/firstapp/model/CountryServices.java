package com.example.firstapp.model;



import com.example.firstapp.di.ApiComponents;
import com.example.firstapp.di.DaggerApiComponents;

import java.util.List;

import javax.inject.Inject;

import dagger.Component;
import io.reactivex.rxjava3.core.Single;




public class CountryServices {


    static CountryServices instance;
    @Inject
    public ApiInterface apiInterface;



//
//    apiInterface=new Retrofit.Builder().baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//            .build()
//                .create(ApiInterface.class);
    CountryServices()
    {
        DaggerApiComponents.create().inject(this);

    }

    public static CountryServices getInstance()
    {
        if (instance==null)
        {
            instance=new CountryServices();
        }
        return instance;
    }
    public Single<List<CountryModel>> getContries()
    {
        return apiInterface.getUserDetails();
    }

}
