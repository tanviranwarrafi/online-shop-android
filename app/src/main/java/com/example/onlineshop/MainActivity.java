package com.example.onlineshop;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import static com.example.onlineshop.RegisterActivity.setSignUpFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final int HOME_FRAGMENT = 0;
    private static final int CART_FRAGMENT = 1;
    private static final int ORDERS_FRAGMENT = 2;
    private static final int WISHLIST_FRAGMENT = 3;
    private static final int REWARDS_FRAGMENT = 4;
    private static final int ACCOUNT_FRAGMENT = 5;

    public static Boolean showCart = false;

    private int currentFragment = -1;

    private FrameLayout frameLayout;
    private NavigationView navigationView;
    private ImageView actionBarLogo;

    private Window window;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        actionBarLogo = findViewById(R.id.actionbar_logo);
        setSupportActionBar(toolbar);

        window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);

        frameLayout = (FrameLayout) findViewById(R.id.main_frameLayout);

        if (showCart){
            drawer.setDrawerLockMode(1);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            gotoFragment("My Cart", new MyCartFragment(), -2);
        }else {
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (currentFragment == HOME_FRAGMENT){
                currentFragment = -1;
                super.onBackPressed();
            } else {
                if (showCart){
                    showCart = false;
                    finish();
                }else {
                    actionBarLogo.setVisibility(View.VISIBLE);
                    invalidateOptionsMenu();
                    setFragment(new HomeFragment(), HOME_FRAGMENT);
                    navigationView.getMenu().getItem(0).setChecked(true);
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        if (currentFragment == HOME_FRAGMENT){
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getMenuInflater().inflate(R.menu.main, menu);
        }
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
        }else if (id == R.id.main_notification_icon) {

            return true;
        }else if (id == R.id.main_cart_icon) {
            final Dialog signInDialog = new Dialog(MainActivity.this);
            signInDialog.setContentView(R.layout.sign_in_dialog);
            signInDialog.setCancelable(true);

            signInDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Button dialogSignInBtn = signInDialog.findViewById(R.id.sign_in_btn);
            Button dialogSignUpBtn = signInDialog.findViewById(R.id.sign_up_btn);

            final Intent registerIntent = new Intent(MainActivity.this, RegisterActivity.class);

            dialogSignInBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
                    setSignUpFragment = false;
                    startActivity(registerIntent);
                }
            });

            dialogSignUpBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signInDialog.dismiss();
                    setSignUpFragment = true;
                    startActivity(registerIntent);
                }
            });
            signInDialog.show();

            //gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
            return true;
        }else if (id == android.R.id.home){
            if (showCart){
                showCart = false;
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private void gotoFragment(String title, Fragment fragment, int fragmentNo) {
        actionBarLogo.setVisibility(View.GONE);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle(title);
        invalidateOptionsMenu();
        setFragment(fragment, fragmentNo);
        if (fragmentNo == CART_FRAGMENT){
            navigationView.getMenu().getItem(3).setChecked(true);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_myShop){
            actionBarLogo.setVisibility(View.VISIBLE);
            invalidateOptionsMenu();
            setFragment(new HomeFragment(), HOME_FRAGMENT);
        }else if (id == R.id.nav_myOrders) {
            gotoFragment("My Orders",new MyOrdersFragment(), ORDERS_FRAGMENT);
        }else if(id == R.id.nav_myRewords){
            gotoFragment("My Rewards", new MyRewardsFragment(), REWARDS_FRAGMENT);
        }else if(id == R.id.nav_mycart){
            gotoFragment("My Cart", new MyCartFragment(), CART_FRAGMENT);
        }else if(id == R.id.nav_myWishlist){
            gotoFragment("My Wishlist", new MyWishListFragment(), WISHLIST_FRAGMENT);
        }else if(id == R.id.nav_myAccount){
            gotoFragment("My Account", new MyAccountFragment(), ACCOUNT_FRAGMENT);
        }else if(id == R.id.nav_signOut){

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragment(Fragment fragment, int fragmentNo) {
        if (fragmentNo != currentFragment){
            if (fragmentNo == REWARDS_FRAGMENT){
                window.setStatusBarColor(Color.parseColor("#5B04B1"));
                toolbar.setBackgroundColor(Color.parseColor("#5B04B1"));
            }else {
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
            }
            currentFragment = fragmentNo;
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.setCustomAnimations(R.anim.frade_in, R.anim.frade_out);
            fragmentTransaction.replace(frameLayout.getId(),fragment);
            fragmentTransaction.commit();
        }
    }
}
