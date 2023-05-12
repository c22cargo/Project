package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private Gson gson = new Gson();

    private ArrayList<Snake> items = new ArrayList<>();

    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new RecyclerViewAdapter(this, items, new RecyclerViewAdapter.OnClickListener() {
            @Override
            public void onClick(Snake item) {
                Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                intent.putExtra("image", item.getauxdata());
                intent.putExtra("name", item.getName());
                startActivity(intent);
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

        Button aboutButton = findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        Button filterButton = findViewById(R.id.filterButton);

        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu filterPopUp = new PopupMenu(MainActivity.this, filterButton);
                filterPopUp.getMenuInflater().inflate(R.menu.popup_menu, filterPopUp.getMenu());
                filterPopUp.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        ArrayList<Snake> filteredItems = new ArrayList<>();
                        switch (menuItem.getItemId()) {
                            case R.id.noFilter:
                                adapter.setItems(items);
                                adapter.notifyDataSetChanged();
                                System.out.println("TESTEST");
                                return true;
                            case R.id.avgOver4:
                                for (Snake snake : items){
                                    if (snake.getAverageLength() > 4) {
                                        filteredItems.add(snake);
                                    };
                                }
                                adapter.setItems(filteredItems);
                                adapter.notifyDataSetChanged();
                                return true;
                            case R.id.maxOver6:
                                for (Snake snake : items){
                                    if (snake.getMaximumLength() > 6) {
                                        filteredItems.add(snake);
                                    };
                                }
                                adapter.setItems(filteredItems);
                                adapter.notifyDataSetChanged();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                filterPopUp.show();
            }
        });

        new JsonTask(this).execute("https://mobprog.webug.se/json-api?login=c22cargo");
    }

    @Override
    public void onPostExecute(String json) {
        Type type = new TypeToken<List<Snake>>() {
        }.getType();
        items = gson.fromJson(json, type);

        adapter.setItems(items);
        adapter.notifyDataSetChanged();
    }
}