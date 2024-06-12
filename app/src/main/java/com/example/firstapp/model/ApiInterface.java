package com.example.firstapp.model;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("/DevTides/Countries/master/countriesV2.json")
    Single<List<CountryModel>> getUserDetails();
}
