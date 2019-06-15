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
import com.example.finalproject.Model;
import com.example.finalproject.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
//variable
    Context mContext;
    LayoutInflater inflater;
    List<Model> modelList;
    ArrayList<Model> arrayList;
    //constructor


    public ListViewAdapter( Context context,List<Model> modelList) {
       this.mContext=context;
        this.modelList = modelList;
        inflater=LayoutInflater.from(mContext);
        this.arrayList=new ArrayList<Model>();
        this.arrayList.addAll(modelList);
    }

    public class  ViewHolder{
        TextView mTitleTv, mDescTv;
        ImageView mIconTv;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }

    @Override
    public Object getItem(int i) {

        return modelList.get(i);
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

        holder.mTitleTv.setText(modelList.get(position).getTitle());
       holder.mDescTv.setText(modelList.get(position).getDesc());

       //set the results in imageview

       holder.mIconTv.setImageResource(modelList.get(position).getIcon());

        //listview from click

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

if(modelList.get(position).getTitle().equals("Sports")){
    //start new activities
    Intent intent=new Intent(mContext, SearchActivity.class);
    intent.putExtra("actionBarTitle","Sports");

    intent.putExtra("contentTv","@drawable/evento");


    mContext.startActivity(intent);
}
                if(modelList.get(position).getTitle().equals("Workshop")){
                    //start new activities
                    Intent intent=new Intent(mContext,SearchActivity.class);
                    intent.putExtra("actionBarTitle","Workshop");
                    intent.putExtra("contentTv","this is event workshop details");
                    mContext.startActivity(intent);
                }
                if(modelList.get(position).getTitle().equals("art")){
                    //start new activities
                    Intent intent=new Intent(mContext,SearchActivity.class);
                    intent.putExtra("actionBarTitle","art");
                    intent.putExtra("contentTv","this is event art details");
                    mContext.startActivity(intent);
                }
                if(modelList.get(position).getTitle().equals("festival")){
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
        modelList.clear();
        if (charText.length()==0){
            modelList.addAll(arrayList);
        }
        else {
            for (Model model : arrayList)
            {
                if (model.getTitle().toLowerCase(Locale.getDefault()).contains(charText)){
                    modelList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}
