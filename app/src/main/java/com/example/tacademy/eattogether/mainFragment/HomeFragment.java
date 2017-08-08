package com.example.tacademy.eattogether.mainFragment;


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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.eattogether.Model.HomeModel;
import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.Ui.temp2Activity;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListAdapter listAdapter;
    ArrayList<HomeModel> data = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new ListAdapter(data);
        recyclerView.setAdapter(listAdapter);
        for(int i=0; i<4;i++) {
            data.add(new HomeModel());
        }
        data.get(0).setImage(R.drawable.food_default1);
        data.get(1).setImage(R.drawable.food_default2);
        data.get(2).setImage(R.drawable.food_default3);
        data.get(3).setImage(R.drawable.food_default4);
        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), temp2Activity.class));
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

        TextView viewName,viewFoodType,viewPeopleCnt,viewNotice;
        ImageView image;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            viewName = itemView.findViewById(R.id.viewName);
            viewFoodType = itemView.findViewById(R.id.viewFoodType);
            viewPeopleCnt = itemView.findViewById(R.id.viewPeopleCnt);
            viewNotice = itemView.findViewById(R.id.viewNotice);
        }
    }

    //어댑터터

    private class ListAdapter extends RecyclerView.Adapter{

        ArrayList<HomeModel> data;

        public ListAdapter() {
        }

        public ListAdapter(ArrayList<HomeModel> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_home_list, parent, false);
            return new ListItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeModel homeRecycler = data.get(position);
            ListItemViewHolder item = (ListItemViewHolder) holder;


            item.viewName.setText("이름 : " + homeRecycler.getViewName());
            item.viewFoodType.setText("음식 종류 : " + homeRecycler.getViewFoodType());
            item.viewPeopleCnt.setText("인원 : " + homeRecycler.getViewPeopleCnt());
            item.viewNotice.setText("하고 싶은 말 : " + homeRecycler.getViewNotice());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }
}
