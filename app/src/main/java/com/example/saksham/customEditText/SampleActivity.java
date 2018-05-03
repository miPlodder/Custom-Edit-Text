package com.example.saksham.customEditText;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.library.customEditText.BetterVisualizerEditText;
import com.example.library.customEditText.DynamicAmountEditText;

public class SampleActivity extends AppCompatActivity {

    //functionality for limiting 'n' integer places
    //functionality for limiting 'n' decimal places
    DynamicAmountEditText dynamicAmountEditText;
    Button btnShow1;

    //functionality for space after 'n' characters
    //functionality for limiting to 'n' characters
    BetterVisualizerEditText betterVisualizerEditText;
    Button btnShow2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
        init();

        //For DynamicAccountEditText
        dynamicAmountEditText = findViewById(R.id.da_et);
        btnShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SampleActivity.this, dynamicAmountEditText.getActualText(), Toast.LENGTH_SHORT).show();
            }
        });

        //For BetterVisualizerEditText
        betterVisualizerEditText = findViewById(R.id.bv_et);
        btnShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SampleActivity.this, betterVisualizerEditText.getActualText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        btnShow1 = findViewById(R.id.btn_show_1);
        btnShow2 = findViewById(R.id.btn_show_2);
    }
}
