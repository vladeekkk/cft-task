package com.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private RequestQueue queue;

    public static final String TAG = "MY_TAG";

    private List<Currency> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);
        getRequest();
    }

    public void getRequest() {
        String URL = "https://www.cbr-xml-daily.ru/daily_json.js";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.i(TAG, "onResponse: " + response.getJSONObject("Valute"));
                            JSONObject jsonArray = response.getJSONObject("Valute");
                            Iterator iterator = jsonArray.keys();
                            while (iterator.hasNext()) {
                                String currentKey = iterator.next().toString();
                                JSONObject currencyItem = jsonArray.getJSONObject(currentKey);
                                currencyList.add(new Currency(
                                        currencyItem.getString("CharCode"),
                                        currencyItem.getInt("Nominal"),
                                        currencyItem.getString("Name"),
                                        currencyItem.getDouble("Value"),
                                        currencyItem.getDouble("Previous")
                                ));
                                Log.i(TAG, "onResponse: " + currentKey);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

}