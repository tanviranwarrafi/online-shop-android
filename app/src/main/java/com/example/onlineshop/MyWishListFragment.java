package com.example.onlineshop;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyWishListFragment extends Fragment {


    public MyWishListFragment() {
        // Required empty public constructor
    }

    private RecyclerView wishListRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_wish_list, container, false);

        wishListRecyclerView = view.findViewById(R.id.my_wish_list_recyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        wishListRecyclerView.setLayoutManager(linearLayoutManager);

        List<WishListModel> wishListModelList = new ArrayList<>();
        wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
        wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 0, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
        wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 2, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
        wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 4, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));
        wishListModelList.add(new WishListModel(R.drawable.mobile, "Ridmi Note 4", 1, "3", 145, "BDT. 13000/=", "BDT. 15000/=", "Cash on delevery"));

        WishListAdapter wishListAdapter = new WishListAdapter(wishListModelList, true);
        wishListRecyclerView.setAdapter(wishListAdapter);
        wishListAdapter.notifyDataSetChanged();

        return view;
    }

}
