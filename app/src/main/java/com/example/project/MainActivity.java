package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {

    private Gson gson = new Gson();

    private ArrayList<Snake> items = new ArrayList<>();

    private RecyclerViewAdapter adapter;
    private SharedPreferences sharedPreferences;
    private TextView filterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("filter", MODE_PRIVATE);
        filterText = findViewById(R.id.filterText);

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
                        filterItems(menuItem.toString());
                        return false;
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

        filterItems(sharedPreferences.getString("filter", "No Filter"));
    }

    private void updateItems(ArrayList list){
        adapter.setItems(list);
        adapter.notifyDataSetChanged();
    }

    private void filterItems(String filterType){
        ArrayList<Snake> filteredItems = new ArrayList<>();
        switch (filterType) {
            case "No Filter":
                sharedPreferences.edit().putString("filter", "No Filter").apply();
                updateItems(items);
                break;
            case "Average length over 4 M":
                sharedPreferences.edit().putString("filter", "Average length over 4 M").apply();
                for (Snake snake : items){
                    if (snake.getAverageLength() > 4) {
                        filteredItems.add(snake);
                    };
                }
                updateItems(filteredItems);
                break;
            case "Maximum length over 6 M":
                sharedPreferences.edit().putString("filter", "Maximum length over 6 M").apply();
                for (Snake snake : items){
                    if (snake.getMaximumLength() > 6) {
                        filteredItems.add(snake);
                    };
                }
                updateItems(filteredItems);
                break;
        }
        filterText.setText("Filter: " + filterType);
    }
}