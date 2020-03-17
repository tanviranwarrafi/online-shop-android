package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

import static com.example.onlineshop.DeleveryActivity.SELECT_ADDRESS;

public class MyAddressesActivity extends AppCompatActivity {

    private RecyclerView myAddressesRecyclerView;
    private Button deleverHereBtn;
    private static AddressesAdapter addressesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_addresses_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("My Addresses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myAddressesRecyclerView = findViewById(R.id.address_recyclerview);
        deleverHereBtn = findViewById(R.id.deliver_here_btn);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAddressesRecyclerView.setLayoutManager(layoutManager);

        List<AddressesModel> addressesModelList = new ArrayList<>();
        addressesModelList.add(new AddressesModel("Rafyee Tanvir", "25/16, Pallabi, Mirpur 12","1216", true));
        addressesModelList.add(new AddressesModel("Rafyee Tanvir", "25/16, Pallabi, Mirpur 12","1216", false));
        addressesModelList.add(new AddressesModel("Rafyee Tanvir", "25/16, Pallabi, Mirpur 12","1216", false));
        addressesModelList.add(new AddressesModel("Rafyee Tanvir", "25/16, Pallabi, Mirpur 12","1216", false));
        addressesModelList.add(new AddressesModel("Rafyee Tanvir", "25/16, Pallabi, Mirpur 12","1216", false));

        int mode = getIntent().getIntExtra("MODE", -1);
        if (mode == SELECT_ADDRESS){
            deleverHereBtn.setVisibility(View.VISIBLE);
        }else {
            deleverHereBtn.setVisibility(View.GONE);
        }

        addressesAdapter = new AddressesAdapter(addressesModelList, mode);
        myAddressesRecyclerView.setAdapter(addressesAdapter);
        ((SimpleItemAnimator)myAddressesRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        addressesAdapter.notifyDataSetChanged();
    }

    public static void refreshItem(int deselect, int select){
        addressesAdapter.notifyItemChanged(deselect);
        addressesAdapter.notifyItemChanged(select);

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
