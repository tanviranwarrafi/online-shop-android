package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_all_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Deals of the day");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.recycler_view);
        gridView = findViewById(R.id.grid_view);

        int layout_code = getIntent().getIntExtra("layout_code", -1);

        if (layout_code == 0){
            recyclerView.setVisibility(View.VISIBLE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);

            List<WishListModel> wishListModelList = new ArrayList<>();
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 0, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 2, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 4, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 0, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 2, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 4, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
            wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));

            WishListAdapter adapter = new WishListAdapter(wishListModelList,false);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else if (layout_code == 1){

            gridView.setVisibility(View.VISIBLE);
            List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

            /*horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
            horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));*/

            GridProductLayoutAdapter gridProductLayoutAdapter = new GridProductLayoutAdapter(horizontalProductScrollModelList);
            gridView.setAdapter(gridProductLayoutAdapter);
            gridProductLayoutAdapter.notifyDataSetChanged();
        }
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
