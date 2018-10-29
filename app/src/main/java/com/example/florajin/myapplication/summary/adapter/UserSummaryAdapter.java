package com.example.florajin.myapplication.summary.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.florajin.myapplication.R;
import com.example.florajin.myapplication.modal.Summary;

import java.util.List;

import static com.example.florajin.myapplication.R.*;

public class UserSummaryAdapter extends RecyclerView.Adapter<UserSummaryAdapter.MyViewHolder>{
    private List<Summary> employeeSummaryList;
    private Activity activity;

    public UserSummaryAdapter(List<Summary> summaryList, Activity activity) {
        this.employeeSummaryList = summaryList;
        this.activity = activity;
    }

    @NonNull
    @Override
    public UserSummaryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                viewGroup.getContext()).inflate(layout.summary_view, viewGroup,
                false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserSummaryAdapter.MyViewHolder myViewHolder, int position) {

        Summary employeeSummary = employeeSummaryList.get(position);
        myViewHolder.category.setText(employeeSummary.getCategory());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.activity, LinearLayoutManager.HORIZONTAL, false);
        myViewHolder.itemViewHolder.setLayoutManager(layoutManager);
        myViewHolder.itemViewHolder.setAdapter(new SummaryItemAdapter(employeeSummary.getSubcategory(), this.activity));
    }

    @Override
    public int getItemCount() {
        return employeeSummaryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView category;
        private RecyclerView itemViewHolder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.category);
            itemViewHolder = (RecyclerView) itemView.findViewById(R.id.secondary_recycler_view);
        }
    }
}
