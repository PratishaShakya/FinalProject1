package com.example.finalproject.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalproject.R;
import com.example.finalproject.SingleHorizontal;

import java.util.ArrayList;

import static com.example.finalproject.Activity.MainActivity.getHorizontalData;


public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Object> items;

    private final int HORIZONTAL = 2;

    public MainAdapter(Context context, ArrayList<Object> items) {
        this.context = context;
        this.items = items;
    }
    //this method returns the number according to the Vertical/Horizontal object
    @Override
    public int getItemViewType(int position) {

        if (items.get(position) instanceof SingleHorizontal)
            return HORIZONTAL;
        return -1;
    }
    //this method returns the holder that we've inflated according to the viewtype.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup    parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        RecyclerView.ViewHolder holder;
        switch (viewType) {

            case HORIZONTAL:
                view = inflater.inflate(R.layout.horizontal, parent, false);
                holder = new HorizontalViewHolder(view);
                break;

            default:
                view = inflater.inflate(R.layout.horizontal, parent, false);
                holder = new HorizontalViewHolder(view);
                break;
        }


        return holder;
    }
    //here we bind view with data according to the position that we have defined.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

     if (holder.getItemViewType() == HORIZONTAL)
            horizontalView((HorizontalViewHolder) holder);
    }



    private void horizontalView(HorizontalViewHolder holder) {
        HorizontalAdapter adapter = new HorizontalAdapter(context,getHorizontalData());
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        HorizontalViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.inner_recyclerView);
        }
    }


}
