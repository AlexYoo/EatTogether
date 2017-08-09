package com.example.tacademy.eattogether.mainFragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tacademy.eattogether.Model.HomeModel;
import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.S;
import com.example.tacademy.eattogether.Ui.temp2Activity;
import com.example.tacademy.eattogether.Ui.tempActivity;
import com.example.tacademy.eattogether.itemDecorator.ItemTouchHelperCallback;
import com.example.tacademy.eattogether.itemDecorator.ItemTouchHelperListener;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import cn.pedant.SweetAlert.SweetAlertDialog;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ListAdapter listAdapter;
    ArrayList<HomeModel> data = new ArrayList<>();
    ImageLoader imageLoader;
    DisplayImageOptions options;
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

        FloatingActionButton fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), temp2Activity.class));
            }
        });


        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(listAdapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);
        initImageLoader();
        return view;
    }

//    public void setRecyclerViewLongClicked(RecyclerView recyclerView){
//        recyclerView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                S.getInstance().showPopup3(getContext(), "수정", "글을 수정 하시겠습니까?",
//                        "다음화면", new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                sweetAlertDialog.dismissWithAnimation();
//                                startActivity(new Intent(getContext(), tempActivity.class));
//                            }
//                        }, "끄기", new SweetAlertDialog.OnSweetClickListener() {
//                            @Override
//                            public void onClick(SweetAlertDialog sweetAlertDialog) {
//                                sweetAlertDialog.dismissWithAnimation();
//                            }
//                        });
//                return false;
//            }
//
//        });
//    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }

    public void initImageLoader()
    {
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                //.bitmapConfig(Bitmap.Config.ARGB_8888)
                .bitmapConfig(Bitmap.Config.RGB_565)
                //.bitmapConfig(Bitmap.Config.ARGB_4444)
                .build();
        imageLoader = ImageLoader.getInstance();
        // 컨피그 구성
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getContext()).build();
        //ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).build();
        imageLoader.init(configuration);
    }

    //뷰 홀더
    private class ListItemViewHolder extends RecyclerView.ViewHolder{

        TextView viewName,viewFoodType,viewPeopleCnt,viewNotice;
        CircleImageView image;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.thumbnail);
            viewName = itemView.findViewById(R.id.viewName);
            viewFoodType = itemView.findViewById(R.id.viewFoodType);
            viewPeopleCnt = itemView.findViewById(R.id.viewPeopleCnt);
            viewNotice = itemView.findViewById(R.id.viewNotice);

            //수정 필요
            // imageLoader.displayImage("http://13.124.108.134:3000/images/" + );

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    S.getInstance().showPopup3(getContext(), "수정", "글을 수정 하시겠습니까?",
                            "다음화면", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    sweetAlertDialog.dismissWithAnimation();
                                    startActivity(new Intent(getContext(), tempActivity.class));
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

    private class ListAdapter extends RecyclerView.Adapter implements ItemTouchHelperListener{

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

        @Override
        public boolean onItemMoved(int fromPosition, int toPosition) {
            if(fromPosition <0 || toPosition >= data.size() || toPosition <0 || toPosition >= data.size()) {
                return false;
            }
            HomeModel fromItem = data.get(fromPosition);
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
