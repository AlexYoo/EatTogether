package com.example.tacademy.eattogether.home;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.example.tacademy.eattogether.sign_up.profile.ScheduleActivity;
import com.example.tacademy.eattogether.util.S;
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

    public final static int REQUEST_MY_ACTIVITY = 0;

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MyRecyclerViewAdapter myRecyclerViewAdapter;
    ArrayList<HomeModel> data = new ArrayList<>();
    Button selectRegion, selectDate, selectFood;

    ImageLoader imageLoader;

    ImageView imageView0,imageView1,imageView2,imageView3,imageView4;
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
        selectDate   = view.findViewById(R.id.selectDate);
        selectFood   = view.findViewById(R.id.selectFood);
        selectRegion = view.findViewById(R.id.selectRegion);
        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), NewPostActivity.class));
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ScheduleActivity.class));
            }
        });

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

        myRecyclerViewAdapter = new MyRecyclerViewAdapter(data);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        ImageView imageView = view.findViewById(R.id.homeThumbnail);

//
//        data.add(new HomeModel("부산", "양식", "3명", "안녕하세요 같이 먹고싶습니다", imageView0));
//        imageView0.setImageDrawable(getResources().getDrawable(R.drawable.food_default0));
//
//        data.add(new HomeModel("부산", "한식", "2명", "안녕하세요 같이 먹고싶습니다", imageView1));
//        imageView1.setImageDrawable(getResources().getDrawable(R.drawable.food_default1));
//
//        data.add(new HomeModel("부산", "중식", "4명", "안녕하세요 같이 먹고싶습니다", imageView2));
//        imageView2.setImageDrawable(getResources().getDrawable(R.drawable.food_default2));
//
//        data.add(new HomeModel("부산", "양식", "5명", "안녕하세요 같이 먹고싶습니다", imageView3));
//        imageView3.setImageDrawable(getResources().getDrawable(R.drawable.food_default3));
//
//        data.add(new HomeModel("부산", "중식", "8명", "안녕하세요 같이 먹고싶습니다", imageView4));
//        imageView4.setImageDrawable(getResources().getDrawable(R.drawable.food_default4));



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
//                                startActivity(new Intent(getContext(), NewPostActivity.class));
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

    private class MyRecyclerViewAdapter extends RecyclerView.Adapter{

        ArrayList<HomeModel> data;

        public MyRecyclerViewAdapter() {
        }

        public MyRecyclerViewAdapter(ArrayList<HomeModel> data) {
            this.data = data;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.cell_main_home_list, parent, false);
            return new ListItemViewHolder(item);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            HomeModel homeModel = data.get(position);
            ListItemViewHolder item = (ListItemViewHolder) holder;
            item.viewName.setText("이름 : " + homeModel.getViewName());
            item.viewFoodType.setText("음식 종류 : " + homeModel.getViewFoodType());
            item.viewPeopleCnt.setText("인원 : " + homeModel.getViewPeopleCnt());
            item.viewNotice.setText("하고 싶은 말 : " + homeModel.getViewNotice());
        }

        @Override
        public int getItemCount() {
            return data.size();
        }




    }
}
