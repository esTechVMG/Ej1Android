package com.example.ej1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button plus;
    private  Button minus;
    private TextView output1;
    private EditText modNumber;
    private ToggleButton theme;
    private int darkColor,lightColor,lightTint,darkTint;
    private LinearLayout[] layouts=new LinearLayout[2];
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INIT

        resultText=findViewById(R.id.result);
        layouts[0]=findViewById(R.id.firstContainer);
        layouts[1]=findViewById(R.id.themeContainer);
        darkColor=getResources().getColor(R.color.darkTheme);
        lightColor=getResources().getColor(R.color.whiteTheme);
        lightTint=getResources().getColor(R.color.tint_light);
        darkTint=getResources().getColor(R.color.tint_dark);
        theme=findViewById(R.id.themeChoose);
        minus = findViewById(R.id.suma);
        plus = findViewById(R.id.resta);
        output1 = findViewById(R.id.outputnumber1);
        modNumber = findViewById(R.id.modnum);
        //CONFIG CLICKS
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        modNumber.setOnClickListener(this);
        theme.setOnClickListener(this);
        getResources().getColor(R.color.whiteTheme);

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
    public void updateTheme(){
        if(theme.isChecked()){
            setTheme(lightColor,darkColor,darkTint,lightTint,R.drawable.round_corner_bg_dark);//DARK THEME
        }else{
            setTheme(darkColor,lightColor,lightTint,darkTint,R.drawable.round_corner_bg_light);//LIGHT THEME
        }

    }
    public void setTheme(int textColor,int backColor,int tintColor,int hintColor,int drawBG){
        colorButtonUpdate(plus,textColor,backColor);
        colorButtonUpdate(minus,textColor,backColor);
        resultText.setTextColor(textColor);
        modNumber.setTextColor(textColor);
        modNumber.setHintTextColor(hintColor);
        layouts[1].setBackgroundColor(backColor);
        theme.setTextColor(textColor);
        int pL = layouts[0].getPaddingLeft();
        int pT = layouts[0].getPaddingTop();
        int pR = layouts[0].getPaddingRight();
        int pB = layouts[0].getPaddingBottom();
        layouts[0].setBackgroundResource(drawBG);
        layouts[0].setPadding(pL,pT,pR,pB);
    }
    public void colorButtonUpdate(Button b,int textColor,int backColor){
        b.setTextColor(textColor);
        b.getBackground().setTint(backColor);
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
            case R.id.themeChoose:
                updateTheme();
        }
    }
}
