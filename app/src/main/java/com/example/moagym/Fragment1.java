package com.example.moagym;


import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Fragment1 extends Fragment implements OnMapReadyCallback {
    private static final int LOCATION_PERMISSION_REQUST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;

    private double lat, lon;
    private double prevLat = 0.0;
    private double prevLon = 0.0;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_page_1, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);
        locationSource = new FusedLocationSource(Fragment1.this, LOCATION_PERMISSION_REQUST_CODE);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
    if(locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)){
        if(!locationSource.isActivated()){
            naverMap.setLocationTrackingMode(LocationTrackingMode.None);
        }
        return;
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
    this.naverMap = naverMap;
    naverMap.setLocationSource(locationSource);
    naverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
    UiSettings uiSettings = naverMap.getUiSettings();





    uiSettings.setLocationButtonEnabled(true); //현위치
    uiSettings.setScaleBarEnabled(true); //축척바
    uiSettings.setZoomControlEnabled(true); //줌버튼
    uiSettings.setCompassEnabled(true);//나침반
    naverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
        public void onLocationChange(@NonNull Location location) {
            double newLat = location.getLatitude();
            double newLon = location.getLongitude();
            lat = location.getLatitude();
            lon = location.getLongitude();
            if (newLat != prevLat || newLon != prevLon){
                // 이전 위치를 업데이트합니다.
                prevLat = newLat;
                prevLon = newLon;
                String latString = String.valueOf(lat);
                String lonString = String.valueOf(lon);
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");
                            if (success) {
                                JSONArray locationsArray = jsonObject.getJSONArray("locations");

                                for (int i = 0; i < locationsArray.length(); i++) {
                                    JSONObject locationObject = locationsArray.getJSONObject(i);

                                    Double Lat = locationObject.getDouble("Lat");
                                    Double Lon = locationObject.getDouble("Lon");
                                    String Road_add = locationObject.getString("Road_address");
                                    String Jibun_address = locationObject.getString("Jibun_address");
                                    String Name = locationObject.getString("Name");


                                    Marker[] markers = new Marker[locationsArray.length()];
                                    markers[i] = new Marker();
                                    markers[i].setPosition(new LatLng(Lat, Lon));
                                    markers[i].setIcon(OverlayImage.fromResource(R.drawable.imagebutton));
                                    markers[i].setWidth(100);
                                    markers[i].setHeight(100);
                                    markers[i].setMap(naverMap);

                                    int finalI = i;
                                    markers[i].setOnClickListener(new Overlay.OnClickListener() {
                                        @Override
                                        public boolean onClick(@NonNull Overlay overlay) {
                                            // 새로운 Fragment 트랜잭션 시작
                                            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                                            // 새로운 Fragment를 추가하고 표시
                                            Bundle bundle1 = getArguments();
                                            String ID = bundle1.getString("ID");
                                            Fragment6 fragment6 = new Fragment6();
                                            Bundle bundle = new Bundle();
                                            bundle.putString("Name", Name);
                                            bundle.putString("Road_add", Road_add);
                                            bundle.putString("Jibun_address", Jibun_address);
                                            bundle.putString("ID", ID);
                                            fragment6.setArguments(bundle);
                                            transaction.replace(R.id.fragment_container, fragment6); // R.id.fragment_container는 새 창을 표시할 레이아웃 컨테이너의 ID입니다.
                                            transaction.addToBackStack(null);
                                            transaction.commit();
                                            return true;
                                        }
                                    });


                                    //marker.setPosition(new LatLng(Lat, Lon));
                                    //marker.setMap(naverMap);

                                    // 여기서 생성한 마커를 지도에 추가하거나 다른 작업을 수행할 수 있습니다.
                                    // marker.setMap(naverMap);
                                }

                            } else {
                                //Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // JSON 파싱 오류에 대한 예외 처리
                        }
                    }


                };

                LatlonRequestActivity latlonRequestActivity = new LatlonRequestActivity(latString, lonString, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(latlonRequestActivity);
            }
        }
    });
    }
}

