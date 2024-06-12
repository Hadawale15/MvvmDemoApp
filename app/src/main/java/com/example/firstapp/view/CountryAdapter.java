package com.example.firstapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.firstapp.R;
import com.example.firstapp.model.CountryModel;
import com.example.firstapp.util.CountryImageUtil;

import java.util.List;

import okhttp3.internal.Util;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<CountryModel> countriesList;

    public CountryAdapter(List<CountryModel> list) {
        this.countriesList = list;
    }

    public void updateCountries(List<CountryModel> newList)
    {
        countriesList.clear();
        countriesList.addAll(newList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_sl_layout,parent,false);

        return new CountryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {

        holder.Bind(countriesList.get(position));
    }

    @Override
    public int getItemCount() {
        return countriesList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder{

        ImageView countryFlag;
        TextView countryName,capital;

        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryFlag=itemView.findViewById(R.id.Country_flag_id);
            countryName=itemView.findViewById(R.id.CountryName_id);
            capital=itemView.findViewById(R.id.capital_id);
        }

        void Bind(CountryModel country)
        {
            countryName.setText(country.getCountryName());
            capital.setText(country.getCapital());
            CountryImageUtil.loadImage(countryFlag,country.getFlag(), CountryImageUtil.getProgressDrawable(countryFlag.getContext()));
        }
    }
}
