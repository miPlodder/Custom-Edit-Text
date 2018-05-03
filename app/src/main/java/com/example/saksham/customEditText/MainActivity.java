package com.example.saksham.customEditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //functionality for space after 'n' characters
    //functionality for limiting to 'n' characters
    BetterVisualizerEditText betterVisualizerEditText;

    //functionality for limiting 'n' integer places
    //functionality for limiting 'n' decimal places
    DynamicAmountEditText dynamicAmountEditText;


    AppCompatButton btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //betterVisualizerEditText = findViewById(R.id.bvet);
        dynamicAmountEditText = findViewById(R.id.da_et);
        btnDone = findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, dynamicAmountEditText.getActualText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
