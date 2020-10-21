package com.example.primenumbers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private int maxDigits;
    private EditText textX;
    private EditText textY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout scrollView = (LinearLayout) findViewById(R.id.linear_inside_scroll);
        TextView tx = new TextView(MainActivity.this);
        tx.setText("holaaaa");
        scrollView.addView(tx);
        textX = (EditText) findViewById(R.id.number_x);
        textY = (EditText) findViewById(R.id.number_x);
        findViewById(R.id.calc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(textX.getText());
            }
        });

    }

    public static boolean isPrimeNumber (long n) {
        boolean answer = true;
        if (n%2==0) {
            answer = false;
        }
        else {
            long factor=3;
            double limit = Math.sqrt(n)+0.0001;
            while (factor<limit) {
                if (n%factor==0) {
                    answer = false;
                    break;
                }
                factor+=2;
            }
        }
        return answer;
    }
}