package com.ghayrat_samiev.mobile_com;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Dialog dialog;
    private View ShowDialog;
    ImageView image_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_view = findViewById(R.id.imageView);
        View imagePicker_button = findViewById(R.id.upload_map_button);

        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
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

            }
        });


        ShowDialog = findViewById(R.id.wardriving_button);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_ip_dialog_screen);
        dialog.setCancelable(true); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });

    }
}