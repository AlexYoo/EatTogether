package com.manuel.tacademy.eattogether.maps;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.manuel.tacademy.eattogether.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainMapActivity extends AppCompatActivity
        implements OnMapReadyCallback{

    private GoogleMap mMap;
    private RecyclerView recyclerView;
    private RestaurantAdapter restaurantAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<RestaurantModel> rests = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        restaurantAdapter = new RestaurantAdapter();
        linearLayoutManager = new LinearLayoutManager(this);


        rests.add(new RestaurantModel("11","12","13"));
        rests.add(new RestaurantModel("21","22","23"));
        rests.add(new RestaurantModel("31","32","33"));
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setAdapter(restaurantAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);


        // 지도를 소유하고 있는 플레그먼트 획득
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // 지도 비동기화진행
        mapFragment.getMapAsync(this);




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder>{

        @Override
        public RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(MainMapActivity.this).inflate(R.layout.cell_goodrest_detail, parent, false);
            return new RestaurantViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RestaurantViewHolder holder, int position) {
            holder.toBind(rests.get(position));
        }

        @Override
        public int getItemCount() {
            return rests.size();
        }
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder{
        CircleImageView restaurantImage;
        TextView txt1, txt2, txt3;
        public RestaurantViewHolder(View itemView) {
            super(itemView);
            restaurantImage = itemView.findViewById(R.id.restaurantImage);
            txt1 = itemView.findViewById(R.id.txt1);
            txt2 = itemView.findViewById(R.id.txt2);
            txt3 = itemView.findViewById(R.id.txt3);

        }

        public void toBind(RestaurantModel rm){
            restaurantImage.setImageDrawable(getResources().getDrawable(R.drawable.food_default3));
            txt1.setText(rm.getTxt1());
            txt2.setText(rm.getTxt2());
            txt3.setText(rm.getTxt3());
        }


    }
}