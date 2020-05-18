package com.example.cttarde.tcc_wissen.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.PerfilSeguidorActivity;

public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener{

    private Context context;
    public TextView countryName;
    public ImageView countryPhoto;

    public RecyclerViewHolders(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        countryName = (TextView)itemView.findViewById(R.id.country_name);
        countryPhoto = (ImageView)itemView.findViewById(R.id.country_photo);
    }





    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(), "Pessoa: " + getPosition(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(view.getContext(), PerfilSeguidorActivity.class);
        view.getContext().startActivity(intent);
    }

}