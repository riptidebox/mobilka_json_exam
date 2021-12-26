package com.example.zadanie.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zadanie.Models.Product;
import com.example.zadanie.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<Product> Products = new ArrayList<>();

    public void setData(ArrayList<Product> Products){
        this.Products.clear();
        this.Products.addAll(Products);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.item_of_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(Products.get(position));
    }

    @Override
    public int getItemCount() {
        return Products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView tv_articul;
        final TextView tv_name;
        final TextView tv_price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_articul = itemView.findViewById(R.id.tv_Articul);
            tv_name = itemView.findViewById(R.id.tv_Name);
            tv_price = itemView.findViewById(R.id.tv_Price);
        }

        public void bind(Product product) {
            tv_articul.setText(product.getArticul());
            tv_name.setText(product.getName());
            tv_price.setText(Double.toString(product.getPrice()));
        }
    }
}
