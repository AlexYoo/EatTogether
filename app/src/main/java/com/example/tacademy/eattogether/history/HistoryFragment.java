package com.example.tacademy.eattogether.history;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.util.itemDecorator.ItemTouchHelperCallback;
import com.example.tacademy.eattogether.util.itemDecorator.ItemTouchHelperListener;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyHistoryRecyclerViewAdapter myHistoryRecyclerViewAdapter;
    ArrayList<HistoryModel> data = new ArrayList<>();

    public HistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = view.findViewById(R.id.historyRecyclerView);
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        myHistoryRecyclerViewAdapter = new MyHistoryRecyclerViewAdapter(data);
        recyclerView.setAdapter(myHistoryRecyclerViewAdapter);

        data.add(new HistoryModel("부산", "한식", "3명", "안녕하세요. 같이 먹고 싶습니다.", "2017.8.8", R.drawable.food_default0));
        data.add(new HistoryModel("부산", "일식", "4명", "안녕하세요. 같이 먹고 싶습니다.", "2017.8.9", R.drawable.food_default1));



        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(myHistoryRecyclerViewAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }


    //뷰 홀더
    private class ListItemViewHolder extends RecyclerView.ViewHolder{

        TextView name, foodType, peopleCnt, comment, date;
        ImageView image;
        public ListItemViewHolder(View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.historyDate);
            image = itemView.findViewById(R.id.historyBackground);
            name = itemView.findViewById(R.id.name);
            foodType = itemView.findViewById(R.id.foodType);
            peopleCnt = itemView.findViewById(R.id.peopleCnt);
            comment = itemView.findViewById(R.id.comment);
        }
    }

    //어댑터터

    private class MyHistoryRecyclerViewAdapter extends RecyclerView.Adapter
    implements ItemTouchHelperListener{

        ArrayList<HistoryModel> data;

        public MyHistoryRecyclerViewAdapter() {
        }

        public MyHistoryRecyclerViewAdapter(ArrayList<HistoryModel> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_history_list, parent, false);
            return new ListItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HistoryModel historyModel = data.get(position);
            ListItemViewHolder item = (ListItemViewHolder) holder;

            item.date.setText("" + historyModel.getDate());
            item.image.setImageResource(historyModel.getImage());
            item.name.setText("" + historyModel.getName());
            item.foodType.setText("" + historyModel.getFoodType());
            item.peopleCnt.setText(""+historyModel.getPeopleCnt());
            item.comment.setText("" + historyModel.getComment());
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
            HistoryModel fromItem = data.get(fromPosition);
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
