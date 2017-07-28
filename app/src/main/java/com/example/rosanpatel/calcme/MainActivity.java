package com.example.rosanpatel.calcme;

import android.graphics.Color;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    TextToSpeech texttospeech;

    //Add = 1, Subtract = 2, Multiply = 3, Divide = 4
    int operation = -1;


    // Instantiate the cache
    Cache cache;
    // Set up the network to use HttpURLConnection as the HTTP client.
    Network network;
// Instantiate the RequestQueue with the cache and network.

    RequestQueue queue;
    String url = "http://numbersapi.com/";

    private void initializeQueue() {

        cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

        network = new BasicNetwork(new HurlStack());


        queue = new RequestQueue(cache, network);

        queue.start();

        texttospeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                Log.d("Log", "Text to speech initialized status"+i);}
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initializeQueue();
        setContentView(R.layout.activity_main);

        final View add = findViewById(R.id.add);
        final View subtract = findViewById(R.id.subtract);
        final View multiply = findViewById(R.id.multiply);
        final View divide = findViewById(R.id.divide);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (operation == 1) {
                    operation = -1;
                    add.setBackgroundColor(0);
                } else {
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
                if (operation == 2) {
                    operation = -1;
                    subtract.setBackgroundColor(0);
                } else {
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
                if (operation == 3) {
                    operation = -1;
                    multiply.setBackgroundColor(0);
                } else {
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
                if (operation == 4) {
                    operation = -1;
                    divide.setBackgroundColor(0);
                } else {
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
                if (left.getText().length() == 0) {
                    Toast.makeText(MainActivity.this, "Please enter a number on the left", Toast.LENGTH_LONG).show();
                    return;
                }
                if (right.getText().length() == 0) {
                    Toast.makeText(getBaseContext(), "Please enter a number on the right", Toast.LENGTH_LONG).show();
                    return;
                }
                if (operation == -1) {
                    Toast.makeText(getBaseContext(), "What operation would you like to perform?", Toast.LENGTH_LONG).show();
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

        if (operation == 1) {
            val = add(leftValue, rightValue);
        } else if (operation == 2) {
            val = subtract(leftValue, rightValue);
        } else if (operation == 3) {
            val = multiply(leftValue, rightValue);
        } else if (operation == 4) {
            val = divide(leftValue, rightValue);
        }

        result.setText("Result is: " + val);
getfactfornumber(val);
    }

    public int add(int a, int b) {
        return a + b;
    }

    public float divide(float a, float b) {
        return a / b;
    }

    public int subtract(int a, int b) {
        return a - b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    public void getfactfornumber(float number) {
/**
 * A method that gives a fun fact for the result
 * @param number
 */
        int numberValue = (int) number;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url + numberValue,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Did You Know..." + response, Toast.LENGTH_LONG).show();
                        texttospeech.speak(response,TextToSpeech.QUEUE_FLUSH,null);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });
// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
