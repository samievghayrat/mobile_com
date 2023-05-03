package com.ghayrat_samiev.mobile_com;
import androidx.appcompat.app.AppCompatActivity;
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
    View delete_button;
    View _wardriving_button;
    View localization;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image_view = findViewById(R.id.imageView);
        View imagePicker_button = findViewById(R.id.upload_map_button);
        delete_button = findViewById(R.id.delete_button);
        _wardriving_button = findViewById(R.id.wardriving_button);
        localization = findViewById(R.id.localization_button);

        ShowDialog = findViewById(R.id.wardriving_button);
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.pop_ip_dialog_screen);
        dialog.setCancelable(true); //Optional
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation; //Setting the animations to dialog

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



        ShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.show(); // Showing the dialog here
            }
        });

    }
}