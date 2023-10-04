package com.example.jsonview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.*;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.CustomViewHolder> {

    private final Context context;
    private List<EmpModal> em;

    public EmpAdapter(Context context, List<EmpModal> em) {
        this.context = context;
        this.em = em;
    }

    public void DataChanged(List<EmpModal> em) {
        this.em = em;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mv = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);

        return new CustomViewHolder(mv);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int pos) {
        EmpModal emPm = em.get(pos);
        customViewHolder.emp_name.setText(String.format("Name: %s", emPm.getName()));
        customViewHolder.emp_age.setText(String.format("Name: %s", emPm.getAge()));
        customViewHolder.emp_city.setText(String.format("Name: %s", emPm.getCity()));
    }

    @Override
    public int getItemCount() {
        return em == null ? 0 : em.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView emp_name;
        private final TextView emp_age;
        private final TextView emp_city;

        public CustomViewHolder(@NonNull View mv) {
            super(mv);
            emp_name = mv.findViewById(R.id.empName);
            emp_age = mv.findViewById(R.id.empAge);
            emp_city = mv.findViewById(R.id.empCity);
        }
    }
}
