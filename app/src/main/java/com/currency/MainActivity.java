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
    public RequestSaveHelper saveHelper;
    private List<Currency> currencyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveHelper = new RequestSaveHelper(this, queue, currencyList);
        saveHelper.getRequest();
    }

    @Override
    protected void onResume() {
        super.onResume();
        saveHelper.dbManager.openDb();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveHelper.dbManager.closeDb();
    }
}