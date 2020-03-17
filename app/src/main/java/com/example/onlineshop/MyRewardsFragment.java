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
public class MyRewardsFragment extends Fragment {


    public MyRewardsFragment() {
        // Required empty public constructor
    }

    private RecyclerView rewardsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_rewards, container, false);

        rewardsRecyclerView = view.findViewById(R.id.my_rewards_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rewardsRecyclerView.setLayoutManager(layoutManager);

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

        MyRewardsAdapter myRewardsAdapter = new MyRewardsAdapter(rewardModelList, false);
        rewardsRecyclerView.setAdapter(myRewardsAdapter);
        myRewardsAdapter.notifyDataSetChanged();

        return view;
    }

}
