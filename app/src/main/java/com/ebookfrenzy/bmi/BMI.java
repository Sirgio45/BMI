package com.ebookfrenzy.bmi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;

public class BMI extends AppCompatActivity {
    private static final String SAVED_WEIGHT = "SAVED_WEIGHT";
    private static final String SAVED_HEIGHT = "SAVED_HEIGHT";
    private static final String SAVED_BMI = "SAVED_BMI";


    private double weight;
    private double height;
    private double BMI;

    EditText weightET;
    EditText heightET;
    EditText BMI_ET;

    private SeekBar heightSeekBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);


        if (savedInstanceState == null) {
            weight = 0;
            height = 0;
            BMI = 0;
        } else {
            weight = savedInstanceState.getDouble(SAVED_WEIGHT);
            height = savedInstanceState.getDouble(SAVED_HEIGHT);
            BMI = savedInstanceState.getDouble(SAVED_BMI);
        }

        weightET = (EditText) findViewById(R.id.weightEditText);
        heightET = (EditText) findViewById(R.id.heightEditText);
        BMI_ET = (EditText) findViewById(R.id.BMI_EditText);

        heightSeekBar = (SeekBar) findViewById(R.id.heightSeekBar);
        heightSeekBar.setOnSeekBarChangeListener(heightSeekBarListener);
        weightET.addTextChangedListener(WeightChangeListener);
        heightET.addTextChangedListener(HeightChangeListener);

    }


    private TextWatcher WeightChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

            try {
                weight = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                weight = 1;
            }
            updateCalculator();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private TextWatcher HeightChangeListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                height = Double.parseDouble(s.toString());
            } catch (NumberFormatException e) {
                height = 1;
            }
            updateCalculator();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateCalculator() {
        double BMI = (weight * 703) / (height * height);
        BMI_ET.setText(String.format("%.02f", BMI));
    }

    protected void onSaveInstanceState(Bundle onState) {
        super.onSaveInstanceState(onState);
        onState.putDouble(SAVED_WEIGHT, weight);
        onState.putDouble(SAVED_HEIGHT, height);
        onState.putDouble(SAVED_BMI, BMI);
    }

    private SeekBar.OnSeekBarChangeListener heightSeekBarListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            height = (heightSeekBar.getProgress());
            heightET.setText(String.format("%.02f", height));
            updateCalculator();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
}