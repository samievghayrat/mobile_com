package com.ghayrat_samiev.mobile_com;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import static com.ghayrat_samiev.mobile_com.R.layout.add_wifi_pop_up;
import static com.ghayrat_samiev.mobile_com.R.layout.would_you_like_to_scan;

public class MainActivity extends AppCompatActivity {
    //private  Dialog wold_you_lie_to_scan;
    //buttons
    ImageButton image_view;
    View delete_button;
    View _wardriving_button;
    View localization;

    View see_the_data_;
    //pages
    Intent scanned_aps_page;
    private Intent would_you_page;

    private void addDot(float x, float y) {
        View dotView = new View(this);
        dotView.setLayoutParams(new ViewGroup.LayoutParams(16, 16));
        dotView.setBackgroundResource(R.drawable.circle);
        dotView.setX(x - 8);
        dotView.setY(y - 8);
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(dotView);
    }

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


        View inflatedLayout = getLayoutInflater().inflate(add_wifi_pop_up, null);

// Find the button within the inflated layout
         see_the_data_ = inflatedLayout.findViewById(R.id.see_the_data);

         see_the_data_.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

             }
         });
// Now you can use the button object to set listeners or perform other actions
        see_the_data_.setOnClickListener(new View.OnClickListener() {
            Intent savedAps = new Intent(MainActivity.this, SavedAps.class);
            @Override
            public void onClick(View v) {
                startActivity(savedAps);
                // Do something when the button is clicked
            }
        });

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

        //iamge picker
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

        //Activities page
        scanned_aps_page = new Intent(MainActivity.this, Scanned_Aps.class);
        would_you_page = new Intent(MainActivity.this, popUp_would_you_like.class);

        //delete image
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

        //would you like to scan popUp
        View inflatedView = getLayoutInflater().inflate(would_you_like_to_scan, null);


        //wardriving page
        _wardriving_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startActivity(would_you_page); // Showing the dialog here
            }
        });

      //localization
       localization.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
//               startActivity(scanned_aps_page);


           }
       });

      View mDotView = findViewById(R.id.imageView);

        image_view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                dialog.show();
                float x = v.getX() + v.getWidth() / 2;
                float y = v.getY() + v.getHeight() / 2;

                // Create a new view to represent the dot
                View dotView = new View(MainActivity.this);
                dotView.setBackgroundResource(R.drawable.circle);
                dotView.setLayoutParams(new ViewGroup.LayoutParams(15, 15));
                dotView.setX(x - 15 / 2);
                dotView.setY(y - 15 / 2);
                addDot(x, y);

                // Add the dot view to the layout
                //mDotView.addView(dotView);

            }
        });

//
//        ImageButton dotView = findViewById(R.id.imageView);
//        dotView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Add your logic here to handle the click
//                // For example, you can add another dot at the clicked position
//                // by creating a new View and setting its position
//                float x = v.getX() + v.getWidth() / 2;
//                float y = v.getY() + v.getHeight() / 2;
//                addDot(x, y);
//            }
//        });



    }}