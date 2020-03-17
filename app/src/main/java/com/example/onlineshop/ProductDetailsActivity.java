package com.example.onlineshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import static com.example.onlineshop.MainActivity.showCart;

public class ProductDetailsActivity extends AppCompatActivity {

    private ViewPager productImagesViewPager;
    private TabLayout viewPagerIndicator;

    private Button couprnRedeemBtn;

    //Start Coupen Dialog
    public static TextView coupenTitle;
    public static TextView coupenBody;
    public static TextView coupenExpirydate;
    private static RecyclerView coupensRecyclerView;
    private static LinearLayout selectedCoupen;
    //End Coupen Dialog

    private ViewPager productDetailsViewPager;
    private TabLayout productDetailsTabLayout;

    private Button buyNowBtn;

    //Start Rating Layout
    private LinearLayout rateNowContainer;
    //End Rating Layout

    private static boolean ALREADY_ADDED_TO_WISH_LIST = false;
    private FloatingActionButton addToWhishListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details_activity);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addToWhishListBtn = findViewById(R.id.add_to_wish_list_btn);

        productImagesViewPager = findViewById(R.id.product_images_viewPager);
        viewPagerIndicator = findViewById(R.id.viewpager_indicator);

        productDetailsViewPager = findViewById(R.id.product_details_view_pager);
        productDetailsTabLayout = findViewById(R.id.product_details_tabLayout);

        couprnRedeemBtn = findViewById(R.id.coupen_redemption_btn);

        buyNowBtn = findViewById(R.id.buy_now_btn);

        List<Integer> productImages = new ArrayList<>();
        productImages.add(R.drawable.mobile);
        productImages.add(R.drawable.banner1);
        productImages.add(R.drawable.banner2);
        productImages.add(R.drawable.banner3);

        ProductImagesAdapter productImagesAdapter = new ProductImagesAdapter(productImages);
        productImagesViewPager.setAdapter(productImagesAdapter);

        viewPagerIndicator.setupWithViewPager(productImagesViewPager, true);

        addToWhishListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ALREADY_ADDED_TO_WISH_LIST) {
                    ALREADY_ADDED_TO_WISH_LIST = false;
                    addToWhishListBtn.setSupportImageTintList(ColorStateList.valueOf(Color.parseColor("#9e9e9e")));

                } else {
                    ALREADY_ADDED_TO_WISH_LIST = true;
                    addToWhishListBtn.setSupportImageTintList(getResources().getColorStateList(R.color.colorPrimary));
                }
            }
        });

        productDetailsViewPager.setAdapter(new ProductDetailsAdapter(getSupportFragmentManager(), productDetailsTabLayout.getTabCount()));
        productDetailsViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailsTabLayout));
        productDetailsTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Start Rating Layout
        rateNowContainer = findViewById(R.id.rate_now_container);
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            final int starPosition = x;
            rateNowContainer.getChildAt(x).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }
        //End Rating Layout

        buyNowBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleveryIntent = new Intent(ProductDetailsActivity.this, DeleveryActivity.class);
                startActivity(deleveryIntent);
            }
        });

        //Start Coupen Dialog
        final Dialog checkCoupenPriceDialog = new Dialog(ProductDetailsActivity.this);
        checkCoupenPriceDialog.setContentView(R.layout.coupen_redeem_dialog);
        checkCoupenPriceDialog.setCancelable(true);
        checkCoupenPriceDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView toggleRecyclerView = checkCoupenPriceDialog.findViewById(R.id.toggle_recyclerview);
        coupensRecyclerView = checkCoupenPriceDialog.findViewById(R.id.coupens_recyclerview);
        selectedCoupen = checkCoupenPriceDialog.findViewById(R.id.selected_coupen);

        coupenTitle = checkCoupenPriceDialog.findViewById(R.id.coupen_title);
        coupenBody = checkCoupenPriceDialog.findViewById(R.id.coupen_body);
        coupenExpirydate = checkCoupenPriceDialog.findViewById(R.id.coupen_validity);

        TextView originalPrice = checkCoupenPriceDialog.findViewById(R.id.original_price);
        TextView discountedPrice = checkCoupenPriceDialog.findViewById(R.id.discounted_price);

        LinearLayoutManager layoutManager = new LinearLayoutManager(ProductDetailsActivity.this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        coupensRecyclerView.setLayoutManager(layoutManager);

        List<RewardModel> rewardModelList = new ArrayList<>();
        rewardModelList.add(new RewardModel("Cash Back", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Byy 1 Get 1 Free", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Cash Back", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Byy 1 Get 1 Free", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Cash Back", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Discount", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));
        rewardModelList.add(new RewardModel("Byy 1 Get 1 Free", "Till 2nd June, 2020", "GET 20% OFF on any product above BDT. 500/= and below BDT. 2000/=."));

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList, true);
        coupensRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();


        toggleRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogRecyclerView();
            }
        });
        //End Coupen Dialog

        couprnRedeemBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCoupenPriceDialog.show();
            }
        });



    }

    public static void showDialogRecyclerView() {
        if (coupensRecyclerView.getVisibility() == View.GONE) {
            coupensRecyclerView.setVisibility(View.VISIBLE);
            selectedCoupen.setVisibility(View.GONE);
        } else {
            coupensRecyclerView.setVisibility(View.GONE);
            selectedCoupen.setVisibility(View.VISIBLE);
        }
    }

    private void setRating(int starPosition) {
        for (int x = 0; x < rateNowContainer.getChildCount(); x++) {
            ImageView starBtn = (ImageView) rateNowContainer.getChildAt(x);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if (x <= starPosition) {
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_and_cart_icon, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
            return true;
        } else if (id == R.id.main_search_icon) {

            return true;
        } else if (id == R.id.main_cart_icon) {
            Intent cartintent = new Intent(ProductDetailsActivity.this, MainActivity.class);
            showCart = true;
            startActivity(cartintent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
