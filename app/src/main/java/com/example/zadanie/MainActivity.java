package com.example.zadanie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.zadanie.Adapters.RecyclerAdapter;
import com.example.zadanie.Models.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList products = new ArrayList<Object>();
    private RecyclerView recyclerView;
    private Spinner spinner;
    RecyclerAdapter recyclerAdapter;
    private TextView summ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_products);
        summ = findViewById(R.id.tv_summ);
        spinner = findViewById(R.id.spinner_category);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                products.clear();
                new GetProducts().execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onClick(View view){
        recyclerAdapter = new RecyclerAdapter();
        recyclerAdapter.setData(products);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(recyclerAdapter);
        double allPrice = 0;
        for (int i=0;i<products.size();i++){
           Product product = (Product)products.get(i);
           allPrice += product.getPrice();
        }
        summ.setText("Сумма:" + " " + Double.toString(allPrice));
    }

    private class GetProducts extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open("json.json")));
                StringBuilder result = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } catch (Exception exception) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONArray tempArray = new JSONArray(s);
                JSONObject JSproduct = tempArray.getJSONObject((int)spinner.getSelectedItemId());
                JSONArray tempArray2 = JSproduct.getJSONArray("Products");
                for (int i=0;i<tempArray2.length();i++){
                    JSONObject json = tempArray2.getJSONObject(i);
                    Product product = new Product(
                            json.getString("Articul"),
                            json.getString("Name"),
                            json.getDouble("Price")){
                        @Override
                        public int describeContents() {
                            return 0;
                        }

                        @Override
                        public void writeToParcel(Parcel parcel, int i) {

                        }
                    };
                    products.add(product);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}