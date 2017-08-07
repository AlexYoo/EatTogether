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

import com.example.tacademy.eattogether.Model.ChattingModel;
import com.example.tacademy.eattogether.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListAdapter listAdapter;
    ArrayList<ChattingModel> data = new ArrayList<>();

    public ChattingFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chatting, container, false);
        recyclerView = view.findViewById(R.id.chattingRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        listAdapter = new ListAdapter(data);
        recyclerView.setAdapter(listAdapter);
        for(int i=0; i<4;i++) {
            data.add(new ChattingModel());
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    //뷰 홀더
    private class ListItemViewHolder extends RecyclerView.ViewHolder{

        TextView ownerName, foodType, peopleCnt, comment;

        public ListItemViewHolder(View itemView) {
            super(itemView);

            ownerName = itemView.findViewById(R.id.chattingName);
            foodType = itemView.findViewById(R.id.chattingFoodType);
            peopleCnt = itemView.findViewById(R.id.chattingPeopleCnt);
            comment = itemView.findViewById(R.id.chattingComment);
        }
    }

    //어댑터터

    private class ListAdapter extends RecyclerView.Adapter{

        ArrayList<ChattingModel> data;

        public ListAdapter() {
        }

        public ListAdapter(ArrayList<ChattingModel> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_chatting_list, parent, false);
            return new ListItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ChattingModel chattingModel = data.get(position);
            ListItemViewHolder item = (ListItemViewHolder) holder;
            item.ownerName.setText(chattingModel.getOwnerName());
            item.foodType.setText(chattingModel.getFoodType());
            item.peopleCnt.setText(""+chattingModel.getPeopleCnt());
            item.comment.setText(chattingModel.getComment());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }

}
