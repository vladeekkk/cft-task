package com.currency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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

    public static RequestSaveHelper saveHelper;
    private static List<Currency> currencyList = new ArrayList<>();

    public static RecyclerView recycler;
    public static RecyclerViewAdapter recyclerAdapter;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler = findViewById(R.id.currency_recycler_view);
        saveHelper = new RequestSaveHelper(this);
        btn = findViewById(R.id.update_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestSaveHelper.dbManager.openDb();
                RequestSaveHelper.dbManager.deleteFromDb();
                saveHelper.getRequest();
                recyclerAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RequestSaveHelper.dbManager.openDb();
        currencyList = saveHelper.getCurrencyList();
        if (currencyList.size() == 0) {
            saveHelper.getRequest();
        } else {
            setRecyclerView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestSaveHelper.dbManager.closeDb();
    }

    public static void setRecyclerView(Context context) {
        recyclerAdapter = new RecyclerViewAdapter(context, saveHelper.getCurrencyList());
        recycler.setLayoutManager(new LinearLayoutManager(context));
        recycler.setAdapter(recyclerAdapter);
    }
}