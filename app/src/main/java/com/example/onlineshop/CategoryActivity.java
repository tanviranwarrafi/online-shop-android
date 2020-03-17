package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView categoryRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String title = getIntent().getStringExtra("categoryName");
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        categoryRecyclerView = findViewById(R.id.category_recyclerView);


        //Start Banner Slider
        /*List<SliderModel>sliderModelList = new ArrayList<SliderModel>();

        sliderModelList.add(new SliderModel(R.drawable.banner7, "#FFFFFF"));
        sliderModelList.add(new SliderModel(R.drawable.banner8, "#00B89D"));
        sliderModelList.add(new SliderModel(R.drawable.banner1, "#1293D5"));

        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F1CB66"));
        sliderModelList.add(new SliderModel(R.drawable.banner3, "#C6DEDE"));
        sliderModelList.add(new SliderModel(R.drawable.banner4, "#F7F7F7"));
        sliderModelList.add(new SliderModel(R.drawable.banner5, "#DCDCDC"));
        sliderModelList.add(new SliderModel(R.drawable.banner6, "#CCCCCC"));
        sliderModelList.add(new SliderModel(R.drawable.banner7, "#FFFFFF"));

        sliderModelList.add(new SliderModel(R.drawable.banner8, "#00B89D"));
        sliderModelList.add(new SliderModel(R.drawable.banner1, "#1293D5"));
        sliderModelList.add(new SliderModel(R.drawable.banner2, "#F1CB66"));*/
        //End Banner Slider

        //Start Horizontal Product Layout
        /*List<HorizontalProductScrollModel> horizontalProductScrollModelList = new ArrayList<>();

        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.bag, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.thinking_man, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));
        horizontalProductScrollModelList.add(new HorizontalProductScrollModel(R.drawable.mobile, "Ridmi Note $", "SD 625 Processor", "BDT 13000/="));*/
        //End Horizontal Product Layout


        //////////start Testing ReciclerView
        LinearLayoutManager testingLayoutManager = new LinearLayoutManager(this);
        testingLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoryRecyclerView.setLayoutManager(testingLayoutManager);

        List<HomePageModel> homePageModelList = new ArrayList<>();
        /*homePageModelList.add(new HomePageModel(0,sliderModelList));
        homePageModelList.add(new HomePageModel(1, R.drawable.strip_add, "#DFC7CF"));
        homePageModelList.add(new HomePageModel(2,"Deals of the Horizontal Day!", horizontalProductScrollModelList));
        homePageModelList.add(new HomePageModel(3,"Deals of the Grid Day!", horizontalProductScrollModelList));*/

        HomePageAdapter adapter = new HomePageAdapter(homePageModelList);
        categoryRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.main_search_icon) {

            return true;
        } else if (id == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
