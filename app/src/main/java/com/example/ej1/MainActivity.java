package com.example.ej1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button plus;
    private  Button minus;
    private TextView output1;
    private EditText modNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INIT
        minus = findViewById(R.id.suma);
        plus = findViewById(R.id.resta);
        output1 = findViewById(R.id.outputnumber1);
        modNumber = findViewById(R.id.modnum);
        //CONFIG CLICKS
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        modNumber.setOnClickListener(this);

    }
    private int actualNum=0;
    public void updateOutput(boolean b){
        int num=1;
        String text=modNumber.getText().toString();
        if(!text.isEmpty()){//Makes sure that the number isn t null
            num=Integer.valueOf(text);
        }
        if(b){
            actualNum+=num;
        }else {
            actualNum -= num;
        }
        output1.setText(String.valueOf(actualNum));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.suma:
                updateOutput(true);
                break;
            case R.id.resta:
                updateOutput(false);
                break;
            case R.id.modnum:
                modNumber.requestFocus();
                break;
        }
    }
}
