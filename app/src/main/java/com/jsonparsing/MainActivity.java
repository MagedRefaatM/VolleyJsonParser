package com.jsonparsing;

import android.os.Bundle;

import android.widget.Toast;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTxtView1;

    private String url = "https://httpbin.org/get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTxtView1 = (TextView) findViewById(R.id.DisplayerTv);

        StringRequest request = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String string) {
                parseJsonData(string);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getApplicationContext(), "Some error occurred!!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue rQueue = Volley.newRequestQueue(MainActivity.this);
        rQueue.add(request);
    }

    void parseJsonData(String jsonString) {

        JSONObject jObject;
        JSONObject headers;

        String origin;
        String url;
        String Accept;
        String Accept_Encoding;
        String Accept_Language;
        String Host;
        String Upgrade_Insecure_Requests;
        String User_Agent;

        try {
            jObject = new JSONObject(jsonString);

            headers = jObject.getJSONObject("headers");

            //Accept = headers.getString("Accept");
            Accept_Encoding = headers.getString("Accept-Encoding");
            //Accept_Language = headers.getString("Accept-Language");
            Host = headers.getString("Host");
            //Upgrade_Insecure_Requests = headers.getString("Upgrade-Insecure-Requests");
            User_Agent = headers.getString("User-Agent");

            origin = jObject.getString("origin");
            url = jObject.getString("url");

            mTxtView1.setText("Encoding is : "+Accept_Encoding+" .");
            mTxtView1.append("\n");
            mTxtView1.append("\n");
            mTxtView1.append("Host is : "+Host+" .");
            mTxtView1.append("\n");
            mTxtView1.append("\n");
            mTxtView1.append("Agent is : "+User_Agent+" .");
            mTxtView1.append("\n");
            mTxtView1.append("\n");
            mTxtView1.append("Origin is : "+origin+" .");
            mTxtView1.append("\n");
            mTxtView1.append("\n");
            mTxtView1.append("URL is : "+url+" .");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}