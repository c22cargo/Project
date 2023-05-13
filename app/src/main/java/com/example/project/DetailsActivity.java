package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class DetailsActivity extends AppCompatActivity {

    private ImageView snakeImage;
    private TextView snakeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        snakeImage = findViewById(R.id.snakeImage);
        snakeInfo = findViewById(R.id.snakeInfo);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String image = extras.getString("image");

            try {
                Picasso.get().load(image).into(snakeImage);
            }
            catch(Exception e) {
            }

            String name = extras.getString("name");
            snakeInfo.setText(name);
        }
    }
}