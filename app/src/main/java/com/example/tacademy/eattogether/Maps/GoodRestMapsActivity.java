package com.example.tacademy.eattogether.Maps;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tacademy.eattogether.R;
import com.example.tacademy.eattogether.S;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Build.VERSION_CODES.M;

public class GoodRestMapsActivity extends AppCompatActivity
    implements OnMapReadyCallback{

    private GoogleMap mMap;
    int flagGpsStatus;              // 0:그냥 액티비티 구동되면 1:gps를 설정을 타고 돌아오면
    EditText addr;
    boolean isFirstGpsLoad;         // gps가 최초로 로딩되어 좌표값을 획득했는가?
    RecyclerView recyclerView;
    // 리스트로 뿌리는 데이터 그릇
    ArrayList<CoffeeStoreModel> coffees;
    // 1. 데이터를 부분으로 계속 가져오면 통신의 결과를 담아야 한다.
    // 2. 한번에 통으로 가져오면 참조값만 가지면 된다
    CoffeeAdapter coffeeAdapter;

    Spinner cateSpinner;
    EditText dist;
    LatLng myLoc;
    String selectShop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_rest_maps);
        addr = (EditText)findViewById(R.id.addr);
        // 지도를 소유하고 있는 플레그먼트 획득
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        // 지도 비동기화진행
        mapFragment.getMapAsync(this);

        // 버스 등록
        S.getInstance().getGpsBus().register(this);
        recyclerView = (RecyclerView) findViewById(R.id.coffeeRecyclerView);
        coffeeAdapter = new CoffeeAdapter();

        // 방향 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(coffeeAdapter);

        cateSpinner = (Spinner)findViewById(R.id.cateSpinner);
        dist        = (EditText) findViewById(R.id.dist);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.cate, android.R.layout.simple_spinner_dropdown_item);
        // java 코드에서 리소스 가져오는 방법 : getResources().xxxx
        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getTextArray(R.array.cate));

        cateSpinner.setAdapter(arrayAdapter);
        cateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // 커피전문점을 선택하면 호출 => 어떤 샵을 선택했는지 획득 저장 필요
                selectShop = cateSpinner.getItemAtPosition(i).toString();
                S.getInstance().log( "[0] :: "+selectShop +"을 선택하였다.");
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // GPS Detected ============================================================================
        // 1. Network checking
        // 2. GPS On checking
        checkGpsOn();
        // 3. OS version 6.0 checking => 동의 여부 확인
        // 4. GPS detecting start (구버전, 신버전)
        // 5. geocoder( gps <-> address 변환)
        //    + OTTO Bus 이용하여 비동기적 상황의 이벤트를 전달하는 루틴으로 사용

    }

    @Override
    protected void onResume() {
        super.onResume();
        // GPS 설정을 처리하고 돌아왓을대 다음 단계로 갈수 있겠금 처리해야한다
        if( flagGpsStatus == 1){
            flagGpsStatus = 2;
            checkGpsUseOn();
        }
    }

    public void checkGpsOn()  {
        // 단말기에서 gps 사용을 on 했는지 체크한다.
        // 정확도를 높이고, gps값을 획득하기 위한 조치
        String gps = android.provider.Settings.Secure.getString(getContentResolver(),
                Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        S.getInstance().log("[gps]="+gps);
        if( !(gps.matches(".*gps*.") || gps.matches(".*network*.")) ){
            // GPS 사용 막았다 사용 설정 on 시켜라
            S.getInstance().showPopup3(this, "알림", "GSP를 사용할수 없습니다. 설정 화면으로 이동하시겠습니까?",
                    "예", new SweetAlertDialog.OnSweetClickListener(){
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            if( flagGpsStatus == 0 )
                                flagGpsStatus = 1;
                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                            startActivity(intent);
                        }
                    },
                    "아니오", new SweetAlertDialog.OnSweetClickListener(){
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sweetAlertDialog.dismissWithAnimation();
                            checkGpsUseOn();
                        }
                    }
            );
        }else{
            // GPS 켜져있다 => 3단계로 이동
            checkGpsUseOn();
        }
    }

    // 3. OS version 6.0 checking => 동의 여부 확인
    public void checkGpsUseOn() {
        // OS version 6.0 checking => 동의 여부 확인
        if( Build.VERSION.SDK_INT >= M ){
            int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            if( permissionCheck != PackageManager.PERMISSION_GRANTED )
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
                {
                    // 동의 되었다
                    gpsDetect(1);
                } else{
                    // 요청 : 1000: 요청코드 (임의로 설정)
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            1000);
                }
            }else{
                // 퍼미션을 이미 동의 했다
                gpsDetect(2);
            }
        }else{
            // 6.0 이하
            gpsDetect(3);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if( requestCode == 1000 ){
            // 동의했냐 거절했냐
            if( grantResults.length > 0 ){
                if( grantResults[0] < 0 ){ // 거부
                    gpsDetect(4);
                }else{ // 동의
                    gpsDetect(5);
                }
            }
            for(int i=0; i<permissions.length; i++)
            {
                S.getInstance().log(permissions[i]);
            }
            for(String s : permissions){
                S.getInstance().log(s);
            }

        }
    }

    // 코드별 처리
    public void gpsDetect(int code)  {
        if( code == 4 ){    // 거부했음 -> 종료!!
            S.getInstance().showPopup3(this,
                    "알림",
                    "안타깝습니다.ㅜㅜ",
                    "확인",
                    new SweetAlertDialog.OnSweetClickListener(){
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            finish();
                        }
                    },
                    null,
                    null
            );
        }else if( code == 5 || code == 2 || code == 1 ){ // 동의한것 => 6.0이상 사용자의미
            startService();
        }else if( code == 3) { // 6.0 이하 단말기이다 그냥 pass

        }
    }

    // GPS를 획득하는 서비스 가동
    public void startService()  {
        Intent intent = new Intent(this, GpsDetectService.class);
        startService(intent);
    }
    // 서비스로 부터 gps값을 받는다 (버스를 통해)
    @Subscribe
    public void receiveGPS(Location location)  {
        // TODO: React to the event somehow!
        if( location!=null) {
            addr.setText(location.getLatitude() + "," + location.getLongitude());
            getAddress(location);
            if( !isFirstGpsLoad ){
                myLocationShow(location);
                isFirstGpsLoad = true;
                startAllCoffeeStore();
            }
        }
    }

    // lat, lng => address 획득\
    public void getAddress(Location location)  {
        if(location ==null) return;
        // 기본 재료 준비
        Geocoder geocoder = new Geocoder(this, Locale.KOREA);
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        // 변환
        try {
            List<Address> addresses = geocoder.getFromLocation(lat, lng, 2);
            if( addresses!=null && addresses.size()>0 ){
                for(Address address : addresses ){
                    S.getInstance().log(address.toString());
                    S.getInstance().log(address.getThoroughfare());
                }
                addr.setText( S.getInstance().getTransferAddr(addresses.get(0) ) );
                /*
                  Address[
                    addressLines=[0:"대한민국 서울특별시 관악구 낙성대동"],
                    feature=낙성대동,
                    admin=서울특별시,
                    sub-admin=null,
                    locality=관악구,
                    thoroughfare=낙성대동,
                    postalCode=null,
                    countryCode=KR,
                    countryName=대한민국,
                    hasLatitude=true,
                    latitude=37.4762397,
                    hasLongitude=true,
                    longitude=126.9583907,
                    phone=null,
                    url=null,
                    extras=null]
                 */
            }else{
                S.getInstance().log("주소 변환한 결과가 없다");
                addr.setText("주소 변환한 결과가 없다");
            }
        } catch (IOException e) {
            e.printStackTrace();
            S.getInstance().log("주소 변환 실패");
            addr.setText("주소 변환 실패");
        }

    }

    // 지도가 준비되면 호출된다.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // 지도 자체를 가르키는 객체
        mMap = googleMap;
        // 지도에 마커 이벤트
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                final CoffeeStoreModel csm = (CoffeeStoreModel)marker.getTag();
                S.getInstance().log(csm.toString());
                // 리스트의 센터를 선택한 내용으로 choice
                //recyclerView.scrollToPosition(csm.getIndex());
                recyclerView.smoothScrollToPosition(csm.getIndex());
                // 추후 포커싱 부분(선택 부분이 도도라지게 처리 필요)

                // 하단부분에서 상위로 올라온다
                Snackbar.make(recyclerView, csm.getNM()+":"+csm.getADDRESS(), Snackbar.LENGTH_LONG)
                        .setAction("상세보기", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(GoodRestMapsActivity.this, SubDetailActivity.class);
                                intent.putExtra("csm", csm);
                                startActivity(intent);
                            }
                        }).show();
                return false;
            }
        });
        // 지도에 클릭하여 위치 이동
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                myLoc = latLng;
                aniMoveCamera(latLng);
            }
        });
        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //S.getInstance().showPopup(MainActivity.this, "추가", "일정을 입력하세요", SweetAlertDialog.NORMAL_TYPE);
                myLoc = latLng;
                aniMoveCamera(latLng);
                onSearch2(null);
            }
        });

        // 동적 퍼미션을 요구하는 경우 (6.0이하로 컴파일 하거나, 동적 퍼미션을 구현하거나) 택일
        // 지도에 진입할때 사용자의 GPS값을 획득하고 들어온다
        //mMap.setMyLocationEnabled(true);

        // 특정 위치를 마킹하여서 화면의 센터로 설정한다.
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void aniMoveCamera(LatLng myLoc)
    {
        CameraPosition ani = new CameraPosition.Builder()
                .target(myLoc)
                .zoom(13)
                .bearing(60)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ani));
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    // 현재 위치를 지도상에 표시한다(댕겨서)
    public void myLocationShow(Location location){
        // 화면에 표시
        mMap.clear(); // 마킹은 한개만 둔다 그래서 초기화
        myLoc = new LatLng(location.getLatitude(), location.getLongitude());
        //mMap.addMarker(new MarkerOptions().position(myLoc).title("내위치"));
        // 지도 줌 처리
        CameraPosition ani = new CameraPosition.Builder()
                .target(myLoc)
                .zoom(13)
                .bearing(60)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(ani));

    }
    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        //Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Bitmap bitmap = Bitmap.createBitmap(30, 30, Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    public void makeMarker()
    {
        LatLng myLoc;
        GeoPoint point;
        int imgId = 0;
        int index = 0;
        for(CoffeeStoreModel csm : coffees)
        {
            // 좌표 변환
            point = S.getInstance().transGeoToKatec(new GeoPoint(csm.getX_AXIS(), csm.getY_AXIS()));
            // GEO로 좌표 객체 생성
            myLoc = new LatLng(point.getY(), point.getX());
            // 마커의 아이콘을 업체별로 세팅하는 작업 (미완성:이미지가 커서 추가 작업 필요)
            imgId = 0;
            if(csm.getType().equals("COFFEEBEAN")) {
                imgId = R.drawable.coffeebean;
            }else{
                imgId = R.drawable.starbuck;
            }
            // 마커 생성후 지도에 추가
            //Drawable circleDrawable = this.getResources().getDrawable(R.drawable.starbuck);
            //Drawable circleDrawable = getResources().getDrawable(R.drawable.circle_shape);
            //BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);

            Marker marker = mMap.addMarker(new MarkerOptions().position(myLoc).title(csm.getNM()));
            //.icon(markerIcon) );
            // 마커에다 데이터를 세팅
            csm.setIndex(index++); // 데이터 인덱스 세팅
            marker.setTag(csm);
        }

    }
    // 커피 전문점 정보를 다가져온다
    public void startAllCoffeeStore()
    {
        Call<ResCoffeeStoresModel> res = Net.getInstance().getApiIm().coffeeAll();
        res.enqueue(new Callback<ResCoffeeStoresModel>() {
            @Override
            public void onResponse(Call<ResCoffeeStoresModel> call, Response<ResCoffeeStoresModel> response) {
                if( response!=null && response.isSuccessful() ){
                    S.getInstance().log(response.body().getCode()+":"+response.body().getBody().size());
                    coffees = response.body().getBody();
                    // 갱신 알림
                    //coffeeAdapter.notifyDataSetChanged(); // 가급적 전체 갱신은 배제, 부분 처리 요망
                    // 마커 추가
                    makeMarker();
                }else{

                }
            }
            @Override
            public void onFailure(Call<ResCoffeeStoresModel> call, Throwable t) {

            }
        });
    }
    // ViewHolder
    class CoffeeViewHolder extends RecyclerView.ViewHolder
    {
        ImageView brandImg;
        TextView NM, ADDRESS;
        public CoffeeViewHolder(View itemView) {
            super(itemView);
            brandImg    = (ImageView)itemView.findViewById(R.id.brandImg);
            NM          = (TextView)itemView.findViewById(R.id.NM);
            ADDRESS     = (TextView)itemView.findViewById(R.id.ADDRESS);
        }
        public void toBind( CoffeeStoreModel coffeeStoreModel )
        {
            brandImg.setImageDrawable(null);
            if(coffeeStoreModel.getType().equals("COFFEEBEAN")){
                Picasso.with(brandImg.getContext())
                        .load(R.drawable.coffeebean)
                        .error(R.mipmap.ic_launcher_round)
                        .into(brandImg);
            }else{
                Picasso.with(brandImg.getContext())
                        .load(R.drawable.starbuck)
                        .error(R.mipmap.ic_launcher_round)
                        .into(brandImg);
            }
            NM.setText( coffeeStoreModel.getNM());
            ADDRESS.setText( coffeeStoreModel.getADDRESS());
        }
    }
    // Adapter
    class CoffeeAdapter extends RecyclerView.Adapter<CoffeeViewHolder>
    {
        @Override
        public CoffeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(GoodRestMapsActivity.this).inflate(R.layout.cell_coffee_layout, parent, false);
            return new CoffeeViewHolder(view);
        }
        @Override
        public void onBindViewHolder(CoffeeViewHolder holder, int position) {
            holder.toBind( coffees.get(position) );
        }
        @Override
        public int getItemCount() {
            return coffees == null ? 0 : coffees.size() ;
        }
    }
    // =============================================================================================
    public void onDistanceSelect(View view)
    {
        // 팝업 => 검색 거리를 선택함
        AlertDialog.Builder ab = new AlertDialog.Builder(this)
                .setCancelable(true)
                .setSingleChoiceItems(R.array.distance, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] array = getResources().getStringArray(R.array.distance);
                        Snackbar.make(recyclerView, array[i]+"를 선택하였습니다.", Snackbar.LENGTH_LONG)
                                .show();
                        // 써클 표시
                        int dis = Integer.parseInt( array[i].replace("km", "") );
                        mMap.addCircle(new CircleOptions().center(myLoc).radius(dis*1000));
                        GoodRestMapsActivity.this.dist.setText(array[i]);
                        dialogInterface.dismiss();
                    }
                })
                .setTitle("거리 선택");
        AlertDialog alert = ab.create();
        alert.show();
    }
    public void onSearch2(View view)
    {
        // 커피숍 브랜드 + 거리 => 통신 => 결과 => 지도 갱신
        DistModel distModel = new DistModel(S.getInstance().changeBrand(selectShop),
                myLoc.latitude,
                myLoc.longitude,
                S.getInstance().getDouble( dist.getText().toString().replace("km", "") ));
        S.getInstance().log(distModel.toString());
        startBrandCoffeeStore(distModel);
    }
    public void startBrandCoffeeStore(final DistModel distModel)
    {
        Call<ResCoffeeStoresModel> res = Net.getInstance().getApiIm().coffeeDist( distModel );
        res.enqueue(new Callback<ResCoffeeStoresModel>() {
            @Override
            public void onResponse(Call<ResCoffeeStoresModel> call, Response<ResCoffeeStoresModel> response) {
                if( response!=null && response.isSuccessful() ){
                    S.getInstance().log(response.body().getCode()+":"+response.body().getBody().size());
                    coffees = response.body().getBody();
                    // 갱신 알림
                    coffeeAdapter.notifyDataSetChanged(); // 가급적 전체 갱신은 배제, 부분 처리 요망
                    // 마커 추가
                    mMap.clear();
                    makeMarker();

                    int dis = (int)distModel.getDist();
                    mMap.addCircle(new CircleOptions().center(myLoc)
                            .radius(dis*1000).fillColor(0x55ff0000).strokeColor(0xff0000));
                }else{

                }
            }
            @Override
            public void onFailure(Call<ResCoffeeStoresModel> call, Throwable t) {

            }
        });
    }
}
