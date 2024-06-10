package com.example.firstapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firstapp.model.CountryModel;

import java.util.ArrayList;
import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries=new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    public void refresh()
    {
        fetchCountriesData();
    }

    private void fetchCountriesData() {

        CountryModel country1=new CountryModel("0Albania","abc","");
        CountryModel country2=new CountryModel("1bcvcv","1bcv","");
        CountryModel country3=new CountryModel("2bcvcv","1bcv","");

        List<CountryModel> list=new ArrayList<>();
        list.add(country1);
        list.add(country2);
        list.add(country3);
        list.add(country1);
        list.add(country2);
        list.add(country3); list.add(country1);
        list.add(country2);
        list.add(country3);


        countries.setValue(list);

        countryLoadError.setValue(false);
        loading.setValue(false);
    }
}
