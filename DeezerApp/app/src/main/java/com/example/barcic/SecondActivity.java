package com.example.barcic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.barcic.MainActivity.EXTRA_NAME;
import static com.example.barcic.MainActivity.EXTRA_TITLE;
import static com.example.barcic.MainActivity.EXTRA_URL;

public class SecondActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        String name = intent.getStringExtra(EXTRA_NAME);
        String title = intent.getStringExtra(EXTRA_TITLE);

        ImageView imageView = findViewById(R.id.mainImageView);
        TextView textViewName = findViewById(R.id.album);
        TextView textViewTitle = findViewById(R.id.name);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textViewName.setText(name);
        textViewTitle.setText(title);


    }



}
