package com.example.tacademy.eattogether.good_restaurant;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.home.FoodTypeActivity;
import com.example.tacademy.eattogether.home.RegionActivity;
import com.example.tacademy.eattogether.maps.Maps2Activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodRestFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListAdapter listAdapter;
    ArrayList<GoodRestModel> data = new ArrayList<>();
    Button selectRegion, selectFood;

    public GoodRestFragment() {
        // Required empty public constructor
    }
    
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_goodrest, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new ListAdapter(data);
        recyclerView.setAdapter(listAdapter);

        data.add(new GoodRestModel("미소야", "돈까스", "일식", "￦8,900", "3.3", R.drawable.misoya));
        data.add(new GoodRestModel("국밥집", "국밥", "한식", "￦7,900", "4.0", R.drawable.gookbab));
        data.add(new GoodRestModel("물회집", "물회", "한식", "9,900", "3.5", R.drawable.moolhui));



        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Maps2Activity.class)); //GoodRestMapsActivity로 가도록 바꿔야함
            }
        });

        selectFood   = view.findViewById(R.id.goodrestSelectFood);
        selectRegion = view.findViewById(R.id.goodrestSelectRegion);

        selectFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), FoodTypeActivity.class));
            }
        });

        selectRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RegionActivity.class));
            }
        });



        return view;



    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    //뷰 홀더
    private class ListItemViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName,foodName,foodType,foodPrice, restaurantGrade;
        ImageView foodImage;
        public ListItemViewHolder(View itemView) {
            super(itemView);
            foodImage = itemView.findViewById(R.id.goodrestBackground);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            foodName = itemView.findViewById(R.id.foodName);
            foodType = itemView.findViewById(R.id.foodType);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            restaurantGrade = itemView.findViewById(R.id.restaurantGrade);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(), RestaurantDetailActivity.class));
                }
            });
        }
    }

    //어댑터

    private class ListAdapter extends RecyclerView.Adapter{

        ArrayList<GoodRestModel> data;

        public ListAdapter() {
        }

        public ListAdapter(ArrayList<GoodRestModel> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_goodrest_list, parent, false);
            return new ListItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            GoodRestModel goodRestModel = data.get(position);
            ListItemViewHolder item = (ListItemViewHolder) holder;

            item.foodImage.setImageResource(goodRestModel.getFoodImage());
            item.restaurantName.setText("" + goodRestModel.getRestaurantName());
            item.foodName.setText("" + goodRestModel.getFoodName());
            item.foodType.setText("" + goodRestModel.getFoodType());
            item.restaurantGrade.setText("☆"+goodRestModel.getRestaurantGrade());
            item.foodPrice.setText("" + goodRestModel.getFoodPrice());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
