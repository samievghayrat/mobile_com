package com.ghayrat_samiev.mobile_com;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class popUp_would_you_like extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.would_you_like_to_scan);

        View yes_button = findViewById(R.id.scan_yes);
        View no_button = findViewById(R.id.scan_no);
        yes_button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(popUp_would_you_like.this, Scanned_Aps.class);

            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        no_button.setOnClickListener(new View.OnClickListener() {
            Intent intent = new Intent(popUp_would_you_like.this, MainActivity.class);

            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });


    }


}
