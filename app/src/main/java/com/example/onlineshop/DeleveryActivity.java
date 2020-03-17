package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class DeleveryActivity extends AppCompatActivity {

    private RecyclerView deleveryRecyclerview;
    private Button changeOrAddNewAddressBtn;

    public static final int SELECT_ADDRESS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delevery);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Delevery");

        deleveryRecyclerview = findViewById(R.id.delevery_recyclerview) ;
        changeOrAddNewAddressBtn = findViewById(R.id.change_or_add_address_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        deleveryRecyclerview.setLayoutManager(layoutManager);

        List<CartItemModel> cartItemModelList = new ArrayList<>();
        cartItemModelList.add(new CartItemModel(0, R.drawable.mobile, "Pixel 2", 2, "BDT. 13000/=", "BDT. 15000/=", 1, 0, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.mobile, "Pixel 2", 0, "BDT. 13000/=", "BDT. 15000/=", 1, 1, 0));
        cartItemModelList.add(new CartItemModel(0, R.drawable.mobile, "Pixel 2", 2, "BDT. 13000/=", "BDT. 15000/=", 1, 2, 0));

        cartItemModelList.add(new CartItemModel(1, "Price (3 Items)", "BDT. 50000/=", "Free", "BDT. 50000/=", "BDT. 5000/="));

        CartAdapter cartAdapter = new CartAdapter(cartItemModelList);
        deleveryRecyclerview.setAdapter(cartAdapter);
        cartAdapter.notifyDataSetChanged();

        changeOrAddNewAddressBtn.setVisibility(View.VISIBLE);

        changeOrAddNewAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myAddressesIntent = new Intent(DeleveryActivity.this, MyAddressesActivity.class);
                myAddressesIntent.putExtra("MODE", SELECT_ADDRESS);
                startActivity(myAddressesIntent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
