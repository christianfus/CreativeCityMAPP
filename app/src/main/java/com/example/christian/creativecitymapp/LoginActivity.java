package com.example.christian.creativecitymapp;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LoginActivity extends AppCompatActivity {

    ImageButton share;
    ImageButton twitter;
    ImageButton instagram;
    Intent shareIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        final String shareBody = "This is a great APP to know what's " +
                "going on in Manchester! You should try it!" +
                "https://www.creativecity.org.uk/";
        Button log = (Button)findViewById(R.id.btnsignin);

        share = (ImageButton)findViewById(R.id.google);
        twitter = (ImageButton)findViewById(R.id.twitter);
        instagram = (ImageButton)findViewById(R.id.instagram);

        //TWITTER SHARE BUTTON

        twitter.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "https://twitter.com/creativecitygm";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //INSTAGRAM SHARE BUTTON

        instagram.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "https://www.instagram.com/creativecitygm/";

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        //GOOGLE SHARE BUTTON

        share.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/pain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "CreativeCityMAPP");
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(shareIntent, "Share via"));

            }
        });

        //BUTTON TO OPEN THE MAP

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginActivity.this,MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}

