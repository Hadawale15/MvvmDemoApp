package com.example.firstapp.di;

import com.example.firstapp.model.CountryServices;
import com.example.firstapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules= {ApiModule.class})
public interface ApiComponents {

    void inject(CountryServices services);

    void inject(ListViewModel listViewModel);
}
