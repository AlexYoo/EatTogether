package com.example.tacademy.eattogether.mainFragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tacademy.eattogether.Model.GoodRestModel;
import com.example.tacademy.eattogether.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class GoodRestFragment extends Fragment {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListAdapter listAdapter;
    ArrayList<GoodRestModel> data = new ArrayList<>();

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
        for(int i=0; i<4;i++) {
            data.add(new GoodRestModel());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    //뷰 홀더
    private class ListItemViewHolder extends RecyclerView.ViewHolder{

        TextView restaurantName,foodName,foodType,foodPrice, restaurantGrade;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            restaurantName = itemView.findViewById(R.id.restaurantName);
            foodName = itemView.findViewById(R.id.foodName);
            foodType = itemView.findViewById(R.id.foodType);
            foodPrice = itemView.findViewById(R.id.foodPrice);
            restaurantGrade = itemView.findViewById(R.id.restaurantGrade);
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
            item.restaurantName.setText("음식점 이름 : " + goodRestModel.getRestaurantName());
            item.foodName.setText("음식 이름 : " + goodRestModel.getFoodName());
            item.foodType.setText("음식 종류 : " + goodRestModel.getFoodType());
            item.restaurantGrade.setText("평점 : "+goodRestModel.getRestaurantGrade());
            item.foodPrice.setText("음식 가격 : " + goodRestModel.getFoodPrice());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
