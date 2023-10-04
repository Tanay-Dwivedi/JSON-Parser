package com.example.jsonview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EmpAdapter extends RecyclerView.Adapter<EmpAdapter.CustomViewHolder> {

    // Context to be used in the adapter
    private final Context context;

    // List to store employee data
    private List<EmpModal> em;

    // Constructor to initialize the adapter with data and context
    public EmpAdapter(Context context, List<EmpModal> em) {
        this.context = context;
        this.em = em;
    }

    // Method to update the data in the adapter
    public void DataChanged(List<EmpModal> em) {
        this.em = em;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the layout for each item in the RecyclerView
        View mv = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        return new CustomViewHolder(mv);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int pos) {
        // Bind data to the views in each item
        EmpModal emPm = em.get(pos);
        customViewHolder.emp_name.setText(String.format("Name: %s", emPm.getName()));
        customViewHolder.emp_age.setText(String.format("Age: %s", emPm.getAge()));
        customViewHolder.emp_city.setText(String.format("City: %s", emPm.getCity()));
    }

    @Override
    public int getItemCount() {
        // Return the number of items in the data list
        return em == null ? 0 : em.size();
    }

    // Custom ViewHolder class to hold references to the item views
    static class CustomViewHolder extends RecyclerView.ViewHolder {
        private final TextView emp_name;
        private final TextView emp_age;
        private final TextView emp_city;

        public CustomViewHolder(@NonNull View mv) {
            super(mv);
            // Initialize views within the item layout
            emp_name = mv.findViewById(R.id.empName);
            emp_age = mv.findViewById(R.id.empAge);
            emp_city = mv.findViewById(R.id.empCity);
        }
    }
}