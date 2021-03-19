package com.currency;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.CurrencyViewHolder> {

    Context context;
    private static List<Currency> currencyList;

    public RecyclerViewAdapter(Context context, List<Currency> stockList) {
        this.context = context;
        this.currencyList = stockList;
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i("MY_TAG", "onCreateViewHolder invoked ");
        View v = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new CurrencyViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        final int maxWordLen = 21;
        Log.i("MY_TAG", "onBindViewHolder invoked ");
        Currency currency = currencyList.get(position);
        double value = Double.parseDouble(currency.getValue());
        double previous = Double.parseDouble(currency.getPrevious());
        double nominal = currency.getNominal();
        double priceNominal = value / nominal;

        priceNominal = (double) Math.round(priceNominal * 100) / 100.0;
        holder.priceTextView.setText(String.valueOf(priceNominal));

        double delta = previous - value;
        delta = (double) Math.round(delta * 100) / 100.0;

        if (delta > 0) {
            holder.priceChangeTextView.setText("+++" + delta);
            holder.priceChangeTextView.setTextColor(Color.parseColor("#00ff00"));
        } else {
            holder.priceChangeTextView.setText("---" + Math.abs(delta));
            holder.priceChangeTextView.setTextColor(Color.parseColor("#FF0000"));

        }

        if (currency.getName().length() > maxWordLen) {
            holder.nameTextView.setText(currency.getName().substring(0, maxWordLen) + "...");
        } else {
            holder.nameTextView.setText(currency.getName());
        }
        holder.shortNameTextView.setText(currency.getCharCode());
    }

    @Override
    public int getItemCount() {
        return currencyList.size();
    }

    public static class CurrencyViewHolder extends RecyclerView.ViewHolder {
        private TextView nameTextView;
        private TextView priceTextView;
        private TextView priceChangeTextView;
        private TextView shortNameTextView;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);
            Context context = itemView.getContext();

            nameTextView = itemView.findViewById(R.id.cur_name_text_view);
            priceTextView = itemView.findViewById(R.id.cur_price_text_view);
            priceChangeTextView = itemView.findViewById(R.id.price_change_text_view);
            shortNameTextView = itemView.findViewById(R.id.short_name_text_view);
        }
    }
}
