package com.example.cttarde.tcc_wissen.adapter;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cttarde.tcc_wissen.DPO.IPrequest;
import com.example.cttarde.tcc_wissen.R;
import com.example.cttarde.tcc_wissen.activity.PerfilActivity;
import com.squareup.picasso.Picasso;

public class CustomListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] itemname;
    private final String[] imgid;

    public CustomListAdapter(Activity context, String[] itemname, String[] imgid) {
        super(context, R.layout.mylist, itemname);
        // TODO Auto-generated constructor stub

        this.context = context;
        this.itemname = itemname;
        this.imgid = imgid;
    }

    @NonNull
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.mylist, null, true);

        TextView txtTitle = (TextView) rowView.findViewById(R.id.item);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView extratxt = (TextView) rowView.findViewById(R.id.textView1);

        txtTitle.setText(itemname[position]);
        //imageView.setImageResource(imgid[position]);
        Picasso.with(getContext()).load(IPrequest.getImgCapa() + PerfilActivity.ImagensCapaOwn[position]).into(imageView);
        extratxt.setText("Responsavel: " + PerfilActivity.ResponsavelOwn[position]);
        return rowView;

    };
}