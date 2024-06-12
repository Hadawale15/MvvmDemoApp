package com.example.firstapp.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.firstapp.di.DaggerApiComponents;
import com.example.firstapp.model.CountryModel;
import com.example.firstapp.model.CountryServices;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
public class ListViewModel extends ViewModel {

    public MutableLiveData<List<CountryModel>> countries=new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError=new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading=new MutableLiveData<Boolean>();

    @Inject
    public CountryServices countryServices;
    private CompositeDisposable disposable=new CompositeDisposable();

    public ListViewModel()
    {
       //super();
        DaggerApiComponents.create().inject(this);
    }
    public void refresh()
    {
        fetchCountriesData();
    }

    private void fetchCountriesData() {

        countryLoadError.setValue(false);
        loading.setValue(true);

        CountryServices.getInstance();
//
//        CountryServices.getInstance().getContries().enqueue(new Callback<List<CountryModel>>() {
//            @Override
//            public void onResponse(Call<List<CountryModel>> call, Response<List<CountryModel>> response) {
//                countries.setValue(response.body());
//                countryLoadError.setValue(false);
//                loading.setValue(false);
//            }
//
//            @Override
//            public void onFailure(Call<List<CountryModel>> call, Throwable t) {
//                countryLoadError.setValue(true);
//                loading.setValue(false);
//                t.printStackTrace();
//            }
//        });
        disposable.add(countryServices.getContries()
                .observeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                countryLoadError.setValue(false);
                loading.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        countryLoadError.setValue(true);
                        loading.setValue(false);
                        e.printStackTrace();
                    }
                })
        );

    }
}
