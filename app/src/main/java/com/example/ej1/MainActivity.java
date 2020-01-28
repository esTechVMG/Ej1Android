package com.example.ej1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button plus,minus,convert,reset;
    private TextView output1,resultText,output2;
    private EditText modNumber,modPrice;
    private ToggleButton theme;
    private int darkColor,lightColor,lightTint,darkTint;
    private LinearLayout[] layouts=new LinearLayout[2];
    private Switch aSwitch;
    private RadioGroup convertGroup;
    private RadioButton dollar,euros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //INIT
        output2=findViewById(R.id.convertedMoney);
        dollar=findViewById(R.id.dollarsButton);
        euros=findViewById(R.id.eurosButton);
        reset=findViewById(R.id.resetButton);
        modPrice=findViewById(R.id.numToConvert);
        convertGroup=findViewById(R.id.convertTypeGroup);
        convert=findViewById(R.id.conversionButton);
        aSwitch=findViewById(R.id.dummySwitch);
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
        convert.setOnClickListener(this);
        reset.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);
        modNumber.setOnClickListener(this);
        theme.setOnClickListener(this);
        aSwitch.setOnClickListener(this);



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
    public void setTheme(int textColor,int backColor,int tintColor,int hintColor,int drawBG){//THIS UPDATES THE COLORS OF THE ELEMENTS
        colorButtonUpdate(plus,textColor,backColor);
        colorButtonUpdate(minus,textColor,backColor);
        resultText.setTextColor(textColor);
        modNumber.setTextColor(textColor);
        modNumber.setHintTextColor(hintColor);
        layouts[1].setBackgroundColor(backColor);
        //STORE PADDING VALUES BECAUSE setBackgroundResource() RESETS IT TO 0
        int pL = layouts[0].getPaddingLeft();
        int pT = layouts[0].getPaddingTop();
        int pR = layouts[0].getPaddingRight();
        int pB = layouts[0].getPaddingBottom();
        layouts[0].setBackgroundResource(drawBG);
        layouts[0].setPadding(pL,pT,pR,pB);
        aSwitch.setTextColor(textColor);
    }
    public void colorButtonUpdate(Button b,int textColor,int backColor){//SPECIFIC BUTTON THEME UPDATE
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
                break;
            case R.id.dummySwitch:
                toggleOutputVisibility();
                break;
            case R.id.conversionButton:
                convertMoney();
                break;
            case R.id.resetButton:
                dollar.setChecked(false);
                euros.setChecked(false);
                modPrice.setText("");
                displayToast("Restablecidas las opciones");
                break;
        }
    }
    public void displayToast(String text){
        Toast toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.show();
    }
    public void convertMoney(){
        float value=1.10f;
        String priceString=modPrice.getText().toString();
        if(priceString.isEmpty()){
            displayToast("Introduzca un valor");
        }else{
            double priceDouble=Double.valueOf(priceString);
            switch (convertGroup.getCheckedRadioButtonId()){
                case R.id.dollarsButton:
                    output2.setText(String.format("%.2f", priceDouble*value) + "$");
                    break;
                case R.id.eurosButton:
                    output2.setText(String.format("%.2f", priceDouble/value) + "€");
                    break;
                default://UNSELECTED RADIO BUTTON
                    displayToast("Seleccione una opcion");
                    break;
            }
        }

    }
    public void toggleOutputVisibility(){
        if(output1.getVisibility()==View.VISIBLE){
            output1.setVisibility(View.INVISIBLE);
        }else{
            output1.setVisibility(View.VISIBLE);
        }

    }
}
