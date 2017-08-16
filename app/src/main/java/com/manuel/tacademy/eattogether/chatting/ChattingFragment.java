package com.manuel.tacademy.eattogether.chatting;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.manuel.tacademy.eattogether.R;
import com.manuel.tacademy.eattogether.home.NewPostActivity;
import com.manuel.tacademy.eattogether.util.itemDecorator.ItemTouchHelperCallback;
import com.manuel.tacademy.eattogether.util.itemDecorator.ItemTouchHelperListener;
import com.manuel.tacademy.eattogether.util.S;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyChattingRecyclerViewAdapter myChattingRecyclerViewAdapter;
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
        myChattingRecyclerViewAdapter = new MyChattingRecyclerViewAdapter(data);
        recyclerView.setAdapter(myChattingRecyclerViewAdapter);
        for(int i=0; i<2;i++) {
            data.add(new ChattingModel());
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(myChattingRecyclerViewAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        
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

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    S.getInstance().showPopup3(getContext(), "수정", "글을 수정 하시겠습니까?",
                            "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    startActivity(new Intent(getContext(), NewPostActivity.class));
                                }
                            }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                }
                            });

                    return false;
                }
            });
        }
    }

    //어댑터터

    private class MyChattingRecyclerViewAdapter extends RecyclerView.Adapter implements ItemTouchHelperListener{

        ArrayList<ChattingModel> data;

        public MyChattingRecyclerViewAdapter() {
        }

        public MyChattingRecyclerViewAdapter(ArrayList<ChattingModel> data) {
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
            item.ownerName.setText("이름 : " + chattingModel.getOwnerName());
            item.foodType.setText("음식 종류 : " + chattingModel.getFoodType());
            item.peopleCnt.setText("인원 : "+chattingModel.getPeopleCnt()); //총인원, 참여인원 나누기
            item.comment.setText("하고 싶은 말 : " + chattingModel.getComment());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public boolean onItemMoved(int fromPosition, int toPosition) {
            if(fromPosition <0 || toPosition >= data.size() || toPosition <0 || toPosition >= data.size()) {
                return false;
            }
            ChattingModel fromItem = data.get(fromPosition);
            data.remove(fromPosition);
            data.add(toPosition, fromItem);

            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        @Override
        public void onItemRemove(int position) {
            data.remove(position);
            notifyItemRemoved(position);
        }
    }

}
