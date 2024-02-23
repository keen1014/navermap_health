package com.example.moagym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class Fragment2 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_2, container, false);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton = v.findViewById(R.id.imageButton);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton2 = v.findViewById(R.id.imageButton2);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton3 = v.findViewById(R.id.imageButton3);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton4 = v.findViewById(R.id.imageButton4);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageButton imageButton5 = v.findViewById(R.id.imageButton5);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://moagym1.dothome.co.kr/index"));
                startActivity(intent);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://moagym1.dothome.co.kr/board/view/6/239"));
                startActivity(intent);
            }
        });
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://moagym1.dothome.co.kr/board/view/7/239"));
                startActivity(intent);
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://moagym1.dothome.co.kr/index"));
                startActivity(intent);
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://moagym1.dothome.co.kr/index"));
                startActivity(intent);
            }
        });
        return v;
    }
}