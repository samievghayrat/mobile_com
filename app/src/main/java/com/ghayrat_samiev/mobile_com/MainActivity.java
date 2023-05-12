package com.ghayrat_samiev.mobile_com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static com.ghayrat_samiev.mobile_com.R.layout.would_you_like_to_scan;

public class MainActivity extends AppCompatActivity {
    //private  Dialog wold_you_lie_to_scan;
    //buttons
    ImageButton image_view;
    View delete_button;
    View _wardriving_button;
    View localization;


    //pages
    Intent scanned_aps_page;
    private Intent would_you_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // LayoutInflater inflater = LayoutInflater.from(); // or use your activity context
       // View would_you_like_to_scan_layout = inflater.inflate(would_you_like_to_scan, null);

        image_view = findViewById(R.id.imageView);
        View imagePicker_button = findViewById(R.id.upload_map_button);
        delete_button = findViewById(R.id.delete_button);
        _wardriving_button = findViewById(R.id.wardriving_button);
        localization = findViewById(R.id.localization_button);

        //Dialogs
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_wifi_pop_up);
        dialog.setCancelable(true); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

//        wold_you_lie_to_scan = new Dialog(this);
//        wold_you_lie_to_scan.setContentView(would_you_like_to_scan);
//        wold_you_lie_to_scan.setCancelable(true); //Optional
//        wold_you_lie_to_scan.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {

                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        image_view.setImageURI(uri);
                        image_view.setVisibility(View.VISIBLE);

                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
        imagePicker_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
                imagePicker_button.setVisibility(View.GONE);
                _wardriving_button.setVisibility(View.VISIBLE);
                delete_button.setVisibility(View.VISIBLE);
                localization.setVisibility(View.VISIBLE);

            }
        });

        //Scanning wifi
        scanned_aps_page = new Intent(MainActivity.this, Scanned_Aps.class);
        would_you_page = new Intent(MainActivity.this, popUp_would_you_like.class);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                image_view.setVisibility(View.GONE);
                imagePicker_button.setVisibility(View.VISIBLE);
                _wardriving_button.setVisibility(View.GONE);
                delete_button.setVisibility(View.GONE);
                localization.setVisibility(View.GONE);

            }
        });

        View inflatedView = getLayoutInflater().inflate(would_you_like_to_scan, null);
        View scan_yes = inflatedView.findViewById(R.id.scan_yes);
        scan_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(scanned_aps_page);
            }
        });

        _wardriving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(scanned_aps_page); // Showing the dialog here
            }
        });

       image_view.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v) {
                dialog.show();
           }
       });
       localization.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(scanned_aps_page);

           }
       });
}}