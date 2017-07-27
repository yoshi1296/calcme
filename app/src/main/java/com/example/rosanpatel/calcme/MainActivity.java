package com.example.rosanpatel.calcme;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Add = 1, Subtract = 2, Multiply = 3, Divide = 4
    int operation = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final View add = findViewById(R.id.add);
        final View subtract = findViewById(R.id.subtract);
        final View multiply = findViewById(R.id.multiply);
        final View divide = findViewById(R.id.divide);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operation == 1) {
                    operation = -1;
                    add.setBackgroundColor(0);
                }else{
                    operation = 1;
                    add.setBackgroundColor(Color.YELLOW);
                    subtract.setBackgroundColor(0);
                    multiply.setBackgroundColor(0);
                    divide.setBackgroundColor(0);
                }
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operation == 2) {
                    operation = -1;
                    subtract.setBackgroundColor(0);
                }else{
                    operation = 2;
                    add.setBackgroundColor(0);
                    subtract.setBackgroundColor(Color.YELLOW);
                    multiply.setBackgroundColor(0);
                    divide.setBackgroundColor(0);
                }
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operation == 3) {
                    operation = -1;
                    multiply.setBackgroundColor(0);
                }else{
                    operation = 3;
                    add.setBackgroundColor(0);
                    subtract.setBackgroundColor(0);
                    multiply.setBackgroundColor(Color.YELLOW);
                    divide.setBackgroundColor(0);
                }
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(operation == 4) {
                    operation = -1;
                    divide.setBackgroundColor(0);
                }else{
                    operation = 4;
                    add.setBackgroundColor(0);
                    subtract.setBackgroundColor(0);
                    multiply.setBackgroundColor(0);
                    divide.setBackgroundColor(Color.YELLOW);
                }
            }
        });

        TextView result = (TextView) findViewById(R.id.result);

        final EditText left = (EditText) findViewById(R.id.firstnumber);
        final EditText right = (EditText) findViewById(R.id.secondnumber);

        final View calculate = findViewById(R.id.calculate);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(left.getText().length()==0) {
                    Toast.makeText(MainActivity.this,"Please enter a number on the left",Toast.LENGTH_LONG).show();
                    return;
                }
                if(right.getText().length()==0) {
                    Toast.makeText(getBaseContext(),"Please enter a number on the right",Toast.LENGTH_LONG).show();
                    return;
                }
                if(operation == -1) {
                    Toast.makeText(getBaseContext(),"What operation would you like to perform?",Toast.LENGTH_LONG).show();
                    return;
                }

                calculate();
            }
        });
    }

    public void calculate() {
        TextView result = (TextView) findViewById(R.id.result);
        final EditText left = (EditText) findViewById(R.id.firstnumber);
        final EditText right = (EditText) findViewById(R.id.secondnumber);
        float val = 0;
        int leftValue = Integer.parseInt(left.getText().toString());
        int rightValue = Integer.parseInt(right.getText().toString());

        if(operation == 1) {
            val = add(leftValue, rightValue);
        } else if(operation == 2) {
            val = subtract(leftValue, rightValue);
        } else if(operation == 3) {
            val = multiply(leftValue, rightValue);
        } else if(operation == 4) {
            val = divide(leftValue, rightValue);
        }

        result.setText("Result is: " + val);

    }
    public int add(int a, int b) {
        return a+b;
    }
    public float divide(float a, float b) {
        return a/b;
    }
    public int subtract(int a, int b) {
        return a-b;
    }
    public int multiply(int a, int b) {
        return a*b;
    }
}
