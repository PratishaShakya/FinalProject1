package com.example.finalproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.finalproject.Activity.SearchActivity;
import com.example.finalproject.Models;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
//variable
    Context mContext;
    LayoutInflater inflater;
    List<Models> modelsList;
    ArrayList<Models> arrayList;
    //constructor


    public ListViewAdapter( Context context,List<Models> modelsList) {
       this.mContext=context;
        this.modelsList = modelsList;
        inflater=LayoutInflater.from(mContext);
        this.arrayList=new ArrayList<Models>();
        this.arrayList.addAll(modelsList);
    }

    public class  ViewHolder{
        TextView mTitleTv, mDescTv;
        ImageView mIconTv;
    }

    @Override
    public int getCount() {
        return modelsList.size();
    }

    @Override
    public Object getItem(int i) {

        return modelsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       ViewHolder holder;
       if (convertView==null){
           holder=new ViewHolder();
           convertView=inflater.inflate(R.layout.row_data,null);

           //locate the views in rowdata.xml
           holder.mTitleTv=convertView.findViewById(R.id.events);
           holder.mDescTv=convertView.findViewById(R.id.eventdescription);
           holder.mIconTv=convertView.findViewById(R.id.img);

           convertView.setTag(holder);
       }
       else {
           holder=(ViewHolder)convertView.getTag();
       }
       //set the results into textviews

        holder.mTitleTv.setText(modelsList.get(position).getTitle());
       holder.mDescTv.setText(modelsList.get(position).getDesc());

       //set the results in imageview

       holder.mIconTv.setImageResource(modelsList.get(position).getIcon());

        //listview from click

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(modelsList.get(position).getTitle().equals("Sports")){
    //start new activities
    Intent intent=new Intent(mContext, SearchActivity.class);
    intent.putExtra("actionBarTitle","Sports");

    intent.putExtra("contentTv","@drawable/evento");


    mContext.startActivity(intent);
}
                if(modelsList.get(position).getTitle().equals("Workshop")){
                    //start new activities
                    Intent intent=new Intent(mContext,SearchActivity.class);
                    intent.putExtra("actionBarTitle","Workshop");
                    intent.putExtra("contentTv","this is event workshop details");
                    mContext.startActivity(intent);
                }
                if(modelsList.get(position).getTitle().equals("art")){
                    //start new activities
                    Intent intent=new Intent(mContext,SearchActivity.class);
                    intent.putExtra("actionBarTitle","art");
                    intent.putExtra("contentTv","this is event art details");
                    mContext.startActivity(intent);
                }
                if(modelsList.get(position).getTitle().equals("festival")){
                    //start new activities
                    Intent intent=new Intent(mContext,SearchActivity.class);
                    intent.putExtra("actionBarTitle","festival");
                    intent.putExtra("contentTv","this is event festival details");
                    mContext.startActivity(intent);
                }


            }
        });
       return convertView;
    }
    //filter
    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        modelsList.clear();
        if (charText.length()==0){
            modelsList.addAll(arrayList);
        }
        else {
            for (Models models : arrayList)
            {
                if (models.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelsList.add(models);
                }
            }
        }
        notifyDataSetChanged();
    }
}
