package com.ghayrat_samiev.mobile_com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //hello world
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText = findViewById(R.id.editTextNumber);
        TextView textViewCorrectAnswer = findViewById(R.id.correctAnswer);
        TextView textViewIncorrect = findViewById(R.id.textViewIncorrectAnswer);
        View answerButton = findViewById(R.id.buttonAnswer);


        answerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String text = editText.getText().toString();
                int number = Integer.parseInt(text);
                if(number == 15){
                    textViewCorrectAnswer.setVisibility(View.VISIBLE);
                    textViewIncorrect.setVisibility(View.GONE);

                }else{
                    textViewCorrectAnswer.setVisibility(View.GONE);
                    textViewIncorrect.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}