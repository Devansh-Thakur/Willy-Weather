package com.example.willyweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherRVAdapter extends RecyclerView.Adapter<WeatherRVAdapter.ViewHolder> {
    private Context context;
    private ArrayList<RVModal> rvModalArrayList;

    public WeatherRVAdapter(Context context, ArrayList<RVModal> rvModalArrayList) {
        this.context = context;
        this.rvModalArrayList = rvModalArrayList;
    }

    @NonNull
    @Override
    public WeatherRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_rv_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherRVAdapter.ViewHolder holder, int position) {
        RVModal modal=rvModalArrayList.get(position);
        holder.temperatureTV.setText(modal.getTemperature()+"°c");
        Picasso.get().load("http:".concat(modal.getIcon())).into(holder.conditionIV);
        holder.windSpeedTV.setText(modal.getWindSpeed()+"km/hr");
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        SimpleDateFormat output= new SimpleDateFormat("hh:mm aa");
        try {
            Date t= input.parse(modal.getTime());
            holder.timeTV.setText(output.format());
        }
        catch (ParseException e){
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return rvModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView temperatureTV,timeTV,windSpeedTV;
        private ImageView conditionIV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            temperatureTV=itemView.findViewById(R.id.idTVTemperature);
            timeTV=itemView.findViewById(R.id.idTVTime);
            windSpeedTV=itemView.findViewById(R.id.idTVWindSpeed);
            conditionIV=itemView.findViewById(R.id.idIVCondition);
        }
    }
}
