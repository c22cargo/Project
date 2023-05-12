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
    private OnClickListener onClickListener;


    public RecyclerViewAdapter(Context context, List<Snake> items, OnClickListener onClickListener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.items = items;
        this.onClickListener = onClickListener;
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView name;
        private TextView avgLength;
        private TextView maxLength;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.snakeName);
            avgLength = itemView.findViewById(R.id.snakeAvgLength);
            maxLength = itemView.findViewById(R.id.snakeMaxLength);
        }

        @Override
        public void onClick(View view) {
            onClickListener.onClick(items.get(getAdapterPosition()));
        }
    }

    public interface OnClickListener {
        void onClick(Snake item);
    }

    public void setItems(List<Snake> items) {
        this.items = items;
    }
}
