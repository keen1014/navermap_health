package com.example.moagym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.overlay.OverlayImage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Fragment6 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_page_6, container, false);

        // 하이퍼링크 버튼을 찾습니다.
        Button reviewsave1 = view.findViewById(R.id.reviewsave);
        Button linkButton = view.findViewById(R.id.linkButton);
        TextView markerTitle = view.findViewById(R.id.markerTitle);
        TextView markerAddress = view.findViewById(R.id.markerAddress);
        EditText reviewEditText = view.findViewById(R.id.review);


        //reviewEditText.requestFocus();
        Bundle bundle = getArguments();
            String value1 = bundle.getString("Name");
            String value2 = bundle.getString("Road_add");
            String value3 = bundle.getString("Jibun_address");
            String value4 = bundle.getString("ID");
            value3 = value3.substring(0, value3.length() - 1); //데이터베이스의 '복'값 제거
            markerTitle.setText(value1);
            markerAddress.setText("지번 주소: "+value3+"\n"+"도로명 주소: "+value2);
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = getArguments();
                String value1 = bundle.getString("Name");


                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://map.naver.com/p/search/"+value1+"?c=15.00,0,0,0,dh"));
                startActivity(browserIntent);
            }
        });
        // 닫기 버튼을 찾습니다.
        Button closeButton = view.findViewById(R.id.closeButton);

        // 닫기 버튼에 클릭 리스너를 추가합니다.
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.remove(Fragment6.this).commit();
            }
        });


        List<Review> reviewList = new ArrayList<>();
        ReviewAdapter adapter = new ReviewAdapter(getContext(), reviewList);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean success = jsonObject.getBoolean("success");
                    if (success) {
                        JSONArray locationsArray = jsonObject.getJSONArray("locations");
                        ListView listView = view.findViewById(R.id.reviews);
                        listView.setAdapter(adapter);
                        // 반복문 안에서 새로운 어댑터 및 리뷰 리스트를 생성하지 않음
                        for (int i = 0; i < locationsArray.length(); i++) {
                            JSONObject locationObject = locationsArray.getJSONObject(i);
                            String ID = locationObject.getString("ID");
                            String livew = locationObject.getString("livew");

                            Review newReview = new Review(ID, livew);
                            // 리뷰를 리스트에 바로 추가
                            reviewList.add(newReview);
                            adapter.notifyDataSetChanged();
                        }


                    } else {
                        List<Review> reviewList = new ArrayList<>();
                        ReviewAdapter adapter = new ReviewAdapter(getContext(), reviewList);

// 리스트뷰 초기화

                        ListView listView = view.findViewById(R.id.reviews);
                        listView.setAdapter(adapter);

// 리뷰 추가 예시
                        Review newReview = new Review(" ", "댓글이 없습니다.");
                        reviewList.add(newReview);
// 어댑터 갱신
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();

                    // JSON 파싱 오류에 대한 예외 처리
                }
            }
        };

        GymlivewRequestActivity gymlivewRequestActivity = new GymlivewRequestActivity(value1, responseListener);
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(gymlivewRequestActivity);
/*

*/

        reviewsave1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                        String gymname = value1;
                        String id = value4;
                        String reivew = reviewEditText.getText().toString();
                        Response.Listener<String> responseListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try
                                {
                                    JSONObject jsonObject = new JSONObject(response);
                                    boolean success = jsonObject.getBoolean("success");

                                    if (success)
                                    {
                                        Toast.makeText(getContext(), "개시 성공:", Toast.LENGTH_SHORT).show();
                                    }

                                    else
                                    {
                                        Toast.makeText(getContext(), "실패", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                }

                                catch (JSONException e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "댓글을 작성해주세요.", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                catch (Exception e)
                                {
                                    e.printStackTrace();
                                    Toast.makeText(getContext(), "예외 발생: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        };
                        TextintoRequestActivity textintoRequestActivity = new TextintoRequestActivity(reivew, id, gymname, responseListener);
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        queue.add(textintoRequestActivity);
                    }
                });

        return view;
    }

    public class Review {
        private String username;
        private String comment;

        public Review(String username, String comment) {
            this.username = username;
            this.comment = comment;
        }

        public String getUsername() {
            return username;
        }

        public String getComment() {
            return comment;
        }
    }
}

