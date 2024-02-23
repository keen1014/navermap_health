package com.example.moagym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class Fragment4Detail2 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_4_detail_2, container, false);

        // 하이퍼링크 버튼을 찾습니다.
        Button linkButton = view.findViewById(R.id.linkButton);

        // 하이퍼링크 버튼에 클릭 리스너를 추가하여 원하는 링크를 여기에 설정합니다.
        linkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 여기에 하이퍼링크를 처리하는 코드를 추가합니다.
                // 예를 들어, 웹 페이지를 열도록 할 수 있습니다.
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://masitdak.com/"));
                startActivity(browserIntent);
            }
        });
        // 닫기 버튼을 찾습니다.
        Button closeButton = view.findViewById(R.id.closeButton);

        // 닫기 버튼에 클릭 리스너를 추가합니다.
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 닫기 버튼을 클릭하면 현재 프래그먼트를 종료합니다.
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.remove(Fragment4Detail2.this).commit();
            }
        });

        // 나머지 뷰 초기화 및 데이터 설정 등의 코드

        return view;
    }
}