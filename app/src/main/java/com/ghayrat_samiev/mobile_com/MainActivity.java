package com.ghayrat_samiev.mobile_com;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import  android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Dialog dialog;
    private View ShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ShowDialog = findViewById(R.id.wardriving_button);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_ip_dialog_screen);
        dialog.setCancelable(true); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog
//
//        View Okay = dialog.findViewById(R.id.btn_okay);
//        View Cancel = dialog.findViewById(R.id.btn_cancel);

//        Okay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "Okay", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });

//        Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Toast.makeText(MainActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
//                dialog.dismiss();
//            }
//        });


        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });


    }
}