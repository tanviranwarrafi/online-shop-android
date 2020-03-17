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
public class MyOrdersFragment extends Fragment {


    public MyOrdersFragment() {
        // Required empty public constructor
    }

    private RecyclerView myOrdersRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_orders, container, false);

        myOrdersRecyclerView = view.findViewById(R.id.my_orders_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myOrdersRecyclerView.setLayoutManager(layoutManager);

        List<MyOrderItemModel> myOrderItemModelList = new ArrayList<>();

        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile,2, "Ridmi Note 4 (Black)", "Delevered on Mondey, 15th January, 2030"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.banner1,1, "Ridmi Note 4 (White)", "Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile,0, "Ridmi Note 4 (Golden)", "Delevered on Mondey, 15th January, 2030"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.banner1,4, "Ridmi Note 4 (Black)", "Cancelled"));
        myOrderItemModelList.add(new MyOrderItemModel(R.drawable.mobile,2, "Ridmi Note 4 (Golden)", "Delevered on Mondey, 15th January, 2030"));

        MyOrderAdapter myOrderAdapter = new MyOrderAdapter(myOrderItemModelList);
        myOrdersRecyclerView.setAdapter(myOrderAdapter);
        myOrderAdapter.notifyDataSetChanged();

        return view;
    }

}
