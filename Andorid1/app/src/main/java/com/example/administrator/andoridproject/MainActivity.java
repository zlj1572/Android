package com.example.administrator.andoridproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.View.OnClickListener;




public class MainActivity extends AppCompatActivity implements OnClickListener{
    Button button_0 = null;
    Button button_1 = null;
    Button button_2 = null;
    Button button_3 = null;
    Button button_4 = null;
    Button button_5 = null;
    Button button_6 = null;
    Button button_7 = null;
    Button button_8 = null;
    Button button_9 = null;
    Button button_plus = null;
    Button button_sub = null;
    Button button_multy = null;
    Button button_dev = null;
    Button button_point = null;
    Button button_equ = null;
    Button button_ac = null;
    Button button_oppo = null;
    Button button_percent = null;
   TextView print_num = null;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            button_0 = (Button) findViewById(R.id.button42);
            button_1 = (Button) findViewById(R.id.button23);
            button_2 = (Button) findViewById(R.id.button26);
            button_3 = (Button) findViewById(R.id.button31);
            button_4 = (Button) findViewById(R.id.button32);
            button_5 = (Button) findViewById(R.id.button27);
            button_6 = (Button) findViewById(R.id.button30);
            button_7 = (Button) findViewById(R.id.button29);
            button_8 = (Button) findViewById(R.id.button28);
            button_9 = (Button) findViewById(R.id.button25);
            button_plus = (Button) findViewById(R.id.button37);
            button_sub = (Button) findViewById(R.id.button35);
            button_multy = (Button) findViewById(R.id.button34);
            button_dev = (Button) findViewById(R.id.button33);
            button_point = (Button) findViewById(R.id.button36);
            button_equ = (Button) findViewById(R.id.button24);
            button_ac = (Button) findViewById(R.id.button38);
            button_oppo = (Button) findViewById(R.id.button39);
            button_percent = (Button) findViewById(R.id.button40);
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
        }
    @Override
    public void onClick(View view) {
        TextView print_num = (TextView) findViewById((R.id.textView));
       /* switch (view.getId()) {
            case R.id.button38:print_num.setText("0");
            case R.id.button42:print_num.setText("1");
        }*/
    print_num.setText("");
        Log.d("MainActivity","this");
    }
}
