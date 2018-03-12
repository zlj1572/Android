package com.example.administrator.andoridproject;

import android.icu.math.BigDecimal;
import android.icu.text.DecimalFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;




public class MainActivity extends AppCompatActivity implements OnClickListener{
    private String text="";
    private int flag;
    private boolean P_num,Continue;
    private double num1,num2,res;
    private Button button_0 = null;
    private Button button_1 = null;
    private Button button_2 = null;
    private Button button_3 = null;
    private Button button_4 = null;
    private Button button_5 = null;
    private Button button_6 = null;
    private Button button_7 = null;
    private Button button_8 = null;
    private Button button_9 = null;
    private Button button_plus = null;
    private Button button_sub = null;
    private Button button_multy = null;
    private Button button_dev = null;
    private Button button_point = null;
    private Button button_equ = null;
    private Button button_ac = null;
    private Button button_oppo = null;
    private Button button_percent = null;
    private TextView print_num = null;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.percent);
            button_0 = (Button) findViewById(R.id.button0);
            button_1 = (Button) findViewById(R.id.button1);
            button_2 = (Button) findViewById(R.id.button2);
            button_3 = (Button) findViewById(R.id.button3);
            button_4 = (Button) findViewById(R.id.button4);
            button_5 = (Button) findViewById(R.id.button5);
            button_6 = (Button) findViewById(R.id.button6);
            button_7 = (Button) findViewById(R.id.button7);
            button_8 = (Button) findViewById(R.id.button8);
            button_9 = (Button) findViewById(R.id.button9);
            button_plus = (Button) findViewById(R.id.buttonplus);
            button_sub = (Button) findViewById(R.id.buttonsub);
            button_multy = (Button) findViewById(R.id.buttonmulty);
            button_dev = (Button) findViewById(R.id.buttondev);
            button_point = (Button) findViewById(R.id.buttonpoint);
            button_equ = (Button) findViewById(R.id.buttonequ);
            button_ac = (Button) findViewById(R.id.buttonAC);
            button_oppo = (Button) findViewById(R.id.buttonoppo);
            button_percent = (Button) findViewById(R.id.buttonpercent);
            button_0.setOnClickListener(this);
            button_1.setOnClickListener(this);
            button_2.setOnClickListener(this);
            button_3.setOnClickListener(this);
            button_4.setOnClickListener(this);
            button_5.setOnClickListener(this);
            button_6.setOnClickListener(this);
            button_7.setOnClickListener(this);
            button_8.setOnClickListener(this);
            button_9.setOnClickListener(this);
            button_plus.setOnClickListener(this);
            button_sub.setOnClickListener(this);
            button_multy.setOnClickListener(this);
            button_dev.setOnClickListener(this);
            button_percent.setOnClickListener(this);
            button_point.setOnClickListener(this);
            button_equ.setOnClickListener(this);
            button_ac.setOnClickListener(this);
            button_oppo.setOnClickListener(this);
            print_num = (TextView) findViewById((R.id.text));
            P_num = false;
            flag=5;
            Continue=false;
        }


    @Override
    public void onClick(View view) {
       // TextView print_num = (TextView) findViewById((R.id.text));
        if(text.length()<6)
        switch (view.getId()) {
            case R.id.button0: {
                if (text.equals("")) {
                    print_num.setText("0");
                } else {
                    text = text + "0";
                    print_num.setText(text);
                }
                break;
            }
            case R.id.button1:text=text+"1";Continue=false;print_num.setText(text);break;
            case R.id.button2:text=text+"2";Continue=false;print_num.setText(text);break;
            case R.id.button3:text=text+"3";Continue=false;print_num.setText(text);break;
            case R.id.button4:text=text+"4";Continue=false;print_num.setText(text);break;
            case R.id.button5:text=text+"5";Continue=false;print_num.setText(text);break;
            case R.id.button6:text=text+"6";Continue=false;print_num.setText(text);break;
            case R.id.button7:text=text+"7";Continue=false;print_num.setText(text);break;
            case R.id.button8:text=text+"8";Continue=false;print_num.setText(text);break;
            case R.id.button9:text=text+"9";Continue=false;print_num.setText(text);break;
            case R.id.buttonpoint:{
                if(!P_num){
                    P_num=true;
                    Continue=false;
                    if(text.equals(""))
                    text=text+"0.";
                    else
                        text=text+".";
                    print_num.setText(text);
                    break;
                }
            }
        }
        switch (view.getId()) {
            case R.id.buttonAC:text="";num1=0;num2=0;P_num=false;print_num.setText("0");break;
            case R.id.buttonpercent:{
                if(!text.equals("")) {
                    String num_string;
                    Double temp;
                    if (text.equals("") && Continue) {
                        num1 = num1 / 100;
                        num_string = Double.toString(num1);
                        temp = num1;
                    } else {
                        temp = Double.parseDouble(text) / 100;
                        num_string = Double.toString(temp);
                        text = num_string;
                    }
                    if (num_string.length() > 8) {
                        //print_num.setText("0");
                        //String Round_string=Double.toString(temp);
                        //String Round_string= String.format("%.5f",temp);
                        //BigDecimal Round_num=new BigDecimal(text);
                        //String Round_string=Round_num.setScale(6,BigDecimal.ROUND_UP).toString();
                        //print_num.setText(Round_string);
                        print_num.setText(new DecimalFormat("#.###E0").format(temp));
                        //print_num.setText(num_string);
                    } else
                        print_num.setText(num_string);
                }
                    break;
            }
            case R.id.buttondev: {
                flag=1;
                if(!Continue&&flag==1&&!text.equals("")) {
                    num1 = Double.parseDouble(text);
                    text = "";
                }
                P_num=false;
                break;
            }
            case R.id.buttonmulty: {
                flag=2;
                if(!Continue&&flag==2&&!text.equals("")) {
                    num1 = Double.parseDouble(text);
                    text = "";
                }
                P_num=false;
                break;
            }
            case R.id.buttonsub: {
                flag=3;
                if(!Continue&&flag==3&&!text.equals("")) {
                    num1 = Double.parseDouble(text);
                    text = "";
                }
                P_num=false;
                break;
            }
            case R.id.buttonplus: {
                flag=4;
                if(!Continue&&flag==4&&!text.equals("")) {
                    num1 = Double.parseDouble(text);
                    text = "";
                }
                P_num=false;
                break;
            }
            case R.id.buttonoppo: {
                if(!text.equals(""))
                if(!Continue) {
                    int len = text.length();
                    num1 = Double.parseDouble(text);
                    num1 = 0 - num1;
                    text = Double.toString(num1);
                    if ((Math.floor(num1) - num1) == 0 && text.length() == len) {
                        BigDecimal Round_num = new BigDecimal(text);
                        text="";
                        text = Round_num.setScale(0, BigDecimal.ROUND_UP).toString();
                    }
                    print_num.setText(text);
                }
                else{
                    num1=0-num1;
                    String num_String = Double.toString(num1);
                    if(num_String.length()>7)
                        print_num.setText(new DecimalFormat("#.##E0").format(num1));
                    else {
                        if ((Math.floor(num1) - num1) == 0) {
                            BigDecimal Round_num = new BigDecimal(num_String);
                            num_String = Round_num.setScale(0, BigDecimal.ROUND_UP).toString();
                            print_num.setText(num_String);
                        } else {
                            print_num.setText(num_String);
                        }
                    }
                }
                break;
            }
            case R.id.buttonequ: {
                P_num=false;
                if(text.equals(""))
                    num2=0.0;
                else
                    num2 = Double.parseDouble(text);
                if(flag==1) {
                    if(num2!=0.0)
                        res = num1 / num2;
                    else {
                        print_num.setText("Infinity");
                        break;
                    }
                }
                else if(flag==2){
                   res=num1*num2;
                }
                else if(flag==3){
                    res=num1-num2;
                }
                else if(flag==4) {
                    res=num1+num2;
                }
                else {
                    if(!text.equals(""))
                        res=Double.parseDouble(text);
                }
                text="";
                text=Double.toString(res);
                if(text.length()>7) {
                    //text=new DecimalFormat("#.##E0").format(res);
                    print_num.setText(new DecimalFormat("#.##E0").format(res));
                }
                else {
                        if ((Math.floor(res) - res) != 0) {
                            //BigDecimal Round_num = new BigDecimal(text);
                            //String Round_string = Round_num.setScale(6, BigDecimal.ROUND_UP).toString();
                            //print_num.setText(Round_string);
                            print_num.setText(text);
                        } else {
                            BigDecimal Round_num = new BigDecimal(text);
                            text = Round_num.setScale(0, BigDecimal.ROUND_UP).toString();
                            print_num.setText(text);
                    }
                }
                num1=res;
                text="";
                Continue=true;
                flag=5;
                break;
            }
        }
        }
    }
