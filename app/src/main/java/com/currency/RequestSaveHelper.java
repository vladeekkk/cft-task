package com.currency;

import android.content.Context;

import com.android.volley.RequestQueue;

import java.util.ArrayList;
import java.util.List;

public class RequestSaveHelper {
    private Context context;
    private RequestQueue queue;

    private List<Currency> currencyList = new ArrayList<>();

    public RequestSaveHelper(Context context, RequestQueue queue, List<Currency> currencyList) {
        this.context = context;
        this.queue = queue;
        this.currencyList = currencyList;
    }
}
