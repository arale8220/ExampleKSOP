package com.example.cgv.ex;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class ContactsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    private TextView mFirstNumber, mSecondNumber, mThirdNumber, mFourthNumber, mFifthNumber, mPowerBall;
    private BottomNavigationView navigationView;
    private MapView mapView;
    private GoogleMap map;
    private TextView inform_text;
    private Location lastKnownLocation;
    private HashMap<String,LatLng> mapPoints = new HashMap<>();

    private static final String TAG = "MainActivity";
    private static final LatLng DEFAULT_LOCATION= new LatLng(36.374259, 127.365544);
    private static final int DEFAULT_ZOOM = 15;
    private static final long INTERVAL_TIME = 5000;
    private static final long FASTEST_INTERVAL_TIME = 2000;
    static final int PERMISSION_FOR_TAB3 = 333;
    private ListView map_chosen;
    private ArrayList<HashMap<String,String>> priceData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
        navigationView.setOnNavigationItemSelectedListener(this);

        FragmentManager fragmentManager = getFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap map) {

        LatLng SEOUL = new LatLng(37.56, 126.97);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(SEOUL);
        markerOptions.title("서울");
        markerOptions.snippet("한국의 수도");
        map.addMarker(markerOptions);

        map.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        map.animateCamera(CameraUpdateFactory.zoomTo(10));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        navigationView.postDelayed(() -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_calendar) {
                startActivity(new Intent(this, MainActivity.class));
            } else if (itemId == R.id.navigation_contacts) {
                startActivity(new Intent(this, ContactsActivity.class));
            } else if (itemId == R.id.navigation_game) {
                startActivity(new Intent(this, GameActivity.class));
            }
            finish();
        }, 300);
        return true;
    }

}
//
//
//public class ContactsActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {
//
//    private TextView mFirstNumber, mSecondNumber, mThirdNumber, mFourthNumber, mFifthNumber, mPowerBall;
//    private BottomNavigationView navigationView;
//    private MapView mapView;
//    private GoogleMap map;
//    private TextView inform_text;
//    private Location lastKnownLocation;
//    private HashMap<String,LatLng> mapPoints = new HashMap<>();
//
//    private static final String TAG = "MainActivity";
//    private static final LatLng DEFAULT_LOCATION= new LatLng(36.374259, 127.365544);
//    private static final int DEFAULT_ZOOM = 15;
//    private static final long INTERVAL_TIME = 5000;
//    private static final long FASTEST_INTERVAL_TIME = 2000;
//    static final int PERMISSION_FOR_TAB3 = 333;
//    private ListView map_chosen;
//    private ArrayList<HashMap<String,String>> priceData;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contacts);
//        navigationView = (BottomNavigationView) findViewById(R.id.navigation);
//        navigationView.setOnNavigationItemSelectedListener(this);
//
//        //맵뷰 상태정보를 번들에 넣었음
//        Bundle MapViewBundle = null;
//        if (savedInstanceState != null) MapViewBundle = savedInstanceState.getBundle(TAG);//액티비티의 상태가 저장되어 있는 경우
//
//        inform_text = (TextView) findViewById(R.id.text);
//        mapView = (MapView) findViewById(R.id.map_view);
//        mapView.onCreate(MapViewBundle);
//        //inform_text=(TextView) findViewById(R.id.map_inform_text);
//        //map_chosen=(ListView) findViewById(R.id.map_chosen);
//
//        mapView.getMapAsync(googleMap -> {
//            map = googleMap;
//            setMapPoints();
//            map.setOnMarkerClickListener(marker -> {
//                inform_text.setText("Place : Finding");
//                return false;
//            });
//
//        });
//
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        Bundle mapViewBundle = outState.getBundle(TAG);
//        if (mapViewBundle == null) {
//            mapViewBundle = new Bundle();
//            outState.putBundle(TAG, mapViewBundle);
//        }
//
//        mapView.onSaveInstanceState(mapViewBundle);
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mapView.onResume();
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        mapView.onStart();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        mapView.onStop();
//    }
//    @Override
//    protected void onPause() {
//        mapView.onPause();
//        super.onPause();
//    }
//    @Override
//    protected void onDestroy() {
//        mapView.onDestroy();
//        super.onDestroy();
//    }
//    @Override
//    public void onLowMemory() {
//        super.onLowMemory();
//        mapView.onLowMemory();
//    }
////    @Override
////    public void onMapReady(GoogleMap googleMap) {
////        map = googleMap;
////        map.setMinZoomPreference(12);
////        LatLng ny = new LatLng(40.7143528, -74.0059731);
////        map.moveCamera(CameraUpdateFactory.newLatLng(ny));
////    }
//
//
//
//
//
//    private void setMapPoints(){
//        //카페모양 아이콘 지정, 크기 조절
//        Bitmap bitmapdraw=(Bitmap) BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
//        Bitmap smallMarker = Bitmap.createScaledBitmap(bitmapdraw, 60, 60, true);
//
//        //맵에 표시할 포인트들 정하기
//        mapPoints = new HashMap<>();
//        mapPoints.put("투썸플레이스", new LatLng(36.374140, 127.365396));
//        mapPoints.put("카페 그랑", new LatLng(36.373714, 127.358998));
//        mapPoints.put("헨델과 그레텔", new LatLng(36.372565, 127.358698));
//        mapPoints.put("커피빈", new LatLng(36.367546, 127.360382));
//        mapPoints.put("스무디킹", new LatLng(36.365861, 127.361251));
//        mapPoints.put("뚜레쥬르", new LatLng(36.370215, 127.363665));
//        mapPoints.put("오가다", new LatLng(36.369095, 127.362828));
//        mapPoints.put("던킨도너츠", new LatLng(36.368573, 127.364515));
//        mapPoints.put("망고식스", new LatLng(36.368289, 127.363482));
//        mapPoints.put("드롭탑", new LatLng(36.369954, 127.360039));
//
//        //지도에 카페 장소와 이름, 카페 모양 아이콘까지 추가
//        Iterator<String> pointNames = mapPoints.keySet().iterator();
//        while(pointNames.hasNext()){
//            String key = pointNames.next();
//            LatLng value = mapPoints.get(key);
//            MarkerOptions markerOptions = new MarkerOptions();
//            markerOptions.position(value);
//            markerOptions.title(key);
//            markerOptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker));
//            map.addMarker(markerOptions);
//        }
// }
//
//
//
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//        navigationView.postDelayed(() -> {
//            int itemId = item.getItemId();
//            if (itemId == R.id.navigation_calendar) {
//                startActivity(new Intent(this, MainActivity.class));
//            } else if (itemId == R.id.navigation_contacts) {
//                startActivity(new Intent(this, ContactsActivity.class));
//            } else if (itemId == R.id.navigation_game) {
//                startActivity(new Intent(this, GameActivity.class));
//            }
//            finish();
//        }, 300);
//        return true;
//    }
//
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//    }
//}