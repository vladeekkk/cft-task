package com.currency;

import android.content.Context;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RequestSaveHelper {
    private Context context;
    private RequestQueue queue;
    public static final String TAG = "MY_TAG";
    private static final String URL = "https://www.cbr-xml-daily.ru/daily_json.js";


    public static DbManager dbManager;

    private List<Currency> currencyList = new ArrayList<>();

    public RequestSaveHelper(Context context) {
        this.context = context;
        this.queue = Volley.newRequestQueue(context);
        dbManager = new DbManager(context);
    }

    public List<Currency> getCurrencyList() {
        return dbManager.getAllListFromDb();
    }


    public void getRequest() {

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
                            for (Currency currency : currencyList) {
                                dbManager.insertToDb(currency);
                            }
                            currencyList.clear();
//                            MainActivity.setRecyclerView(context);
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
