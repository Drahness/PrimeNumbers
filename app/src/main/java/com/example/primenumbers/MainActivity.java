package com.example.primenumbers;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private int maxDigits;
    private EditText textX;
    private EditText textY;
    private LinearLayout linear;
    private List<LinearLayout> verticalLayouts = new ArrayList<>();
    private static final int ROW_LIMIT = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear = (LinearLayout) findViewById(R.id.linear_inside_scroll);
        textX = (EditText) findViewById(R.id.number_x);
        textY = (EditText) findViewById(R.id.number_y);
        System.out.println("holaaaa");
        findViewById(R.id.calc_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linear.removeAllViews();
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                System.out.println("clic");
                boolean errorX = false;
                boolean errorY = false;
                if(textX.getText().toString().equals("")) {
                    errorX = true;
                }
                if(textY.getText().toString().equals("")) {
                    errorY = true;
                }
                if(!errorX && !errorY) {
                    String str_x = textX.getText().toString();
                    String str_y = textY.getText().toString();

                    int x = Integer.parseInt(textX.getText().toString());
                    int y = Integer.parseInt(textY.getText().toString());

                    if (x > y) {
                        for (int i = y;i < x ; i++) {
                            setRow(i, i-y);
                        }
                    }
                    else if( x < y) {
                        for (int i = x; i < y; i++) {
                            setRow(i,i-x);
                        }
                    }
                    else {

                    }
                    for (LinearLayout l : verticalLayouts) {
                        l.setVisibility(View.VISIBLE);
                    }
                } else {{

                }
                    builder.setTitle("Error en la introduccion de parametros.");
                    StringBuilder xy = new StringBuilder();
                    if(errorX) {
                        xy.append("Campo X").append("\n");
                    }
                    if(errorY) {
                        xy.append("Campo Y");
                    }
                    builder.setMessage(xy.toString());
                    builder.create().show();
                }
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
    private void setTextViewForNumber(int number, LinearLayout linear) {
        TextView txt = new TextView(MainActivity.this);
        txt.setText(String.valueOf(number));
        txt.setWidth(8);
        if(isPrimeNumber(number)) {
            txt.setBackgroundColor(getResources().getColor(R.color.red));
        } else {

        }
        linear.addView(txt);
    }
    private void setRow(int number,int index) {
        int layout = index / ROW_LIMIT;
        int size = verticalLayouts.size();
        LinearLayout row;
        if(verticalLayouts.size() < layout+1) {
            row = new LinearLayout(MainActivity.this);
            row.setOrientation(LinearLayout.VERTICAL);
            verticalLayouts.add(row);
        }
        else {
            row = verticalLayouts.get(layout);
        }
        setTextViewForNumber(number,row);
    }
}