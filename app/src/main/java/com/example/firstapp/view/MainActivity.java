package com.example.firstapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.firstapp.R;
import com.example.firstapp.model.CountryModel;
import com.example.firstapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView listError;
    ProgressBar progressBar;

    ListViewModel listViewModel;
    CountryAdapter countryAdapter= new CountryAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recyclerView_id);
        listError=findViewById(R.id.error_text_id);
        progressBar=findViewById(R.id.Progressbar_id);

        listViewModel= new ViewModelProvider(this).get(ListViewModel.class);
        listViewModel.refresh();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(countryAdapter);

        observerViewModel();
    }

    private void observerViewModel() {
        listViewModel.countries.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                if (countryModels!=null)
                {
                    recyclerView.setVisibility(View.VISIBLE);
                    countryAdapter.updateCountries(countryModels);
                }
            }
        });
        listViewModel.countryLoadError.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean!=null)
                {
                    listError.setVisibility(aBoolean?View.VISIBLE:View.GONE);
                }
            }
        });
        listViewModel.loading.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                if (isLoading!=null)
                {
                    progressBar.setVisibility(isLoading?View.VISIBLE:View.GONE);
                    if (isLoading)
                    {
                        listError.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
                    }
                }
            }
        });
    }
}