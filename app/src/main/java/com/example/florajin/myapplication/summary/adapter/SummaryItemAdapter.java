package com.example.florajin.myapplication.summary.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.florajin.myapplication.R;

import java.util.List;

public class SummaryItemAdapter extends RecyclerView.Adapter<SummaryItemAdapter.ItemViewHolder>{
    private List<String> summaryItemList;
    private List<Integer> ringColors;
    private Activity activity;
    private static final String TAG = "SummaryItemAdapter";
    public SummaryItemAdapter(List<String> itemList, Activity activity) {
        this.summaryItemList = itemList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SummaryItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ItemViewHolder holder = new SummaryItemAdapter.ItemViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(R.layout.summary_item, viewGroup,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int position) {

        String item = summaryItemList.get(position);

        Log.d(TAG, "onBindViewHolder: position===="+position);
        switch (position){
            case 0:
                itemViewHolder.subcategory.setBackgroundResource(R.drawable.ring_shape_green);
                break;
            case 1:
                itemViewHolder.subcategory.setBackgroundResource(R.drawable.ring_shape_blue);
                break;
            //case 2: itemViewHolder.subcategory.setBackgroundResource(R.drawable.ring_shape_green);
        }

        itemViewHolder.subcategory.setText(item);
    }

    @Override
    public int getItemCount() {
        return summaryItemList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        //CardView summaryCardView;
        TextView subcategory;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            subcategory = (TextView) itemView.findViewById(R.id.subcategory);
        }
    }
}
