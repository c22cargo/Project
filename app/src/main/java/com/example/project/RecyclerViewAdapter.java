package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Snake> items;
    private LayoutInflater layoutInflater;


    public RecyclerViewAdapter(Context context, List<Snake> items) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
    }

    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(layoutInflater.inflate(R.layout.snake_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText("Name: " + items.get(position).getName());
        holder.avgLength.setText("Average Length: " + String.valueOf(items.get(position).getAverageLength()) + " Meters");
        holder.maxLength.setText("Maximum Length: " + String.valueOf(items.get(position).getMaximumLength()) + " Meters");
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView avgLength;
        private TextView maxLength;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.snakeName);
            avgLength = itemView.findViewById(R.id.snakeAvgLength);
            maxLength = itemView.findViewById(R.id.snakeMaxLength);
        }
    }

    public void setItems(List<Snake> items) {
        this.items = items;
    }
}
