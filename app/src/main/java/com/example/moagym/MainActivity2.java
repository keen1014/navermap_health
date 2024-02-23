package com.example.moagym;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity2 extends AppCompatActivity {
    private BottomNavigationView mBottomNV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mBottomNV = findViewById(R.id.nav_view);


        // 이전 Activity에서 전달된 데이터 가져오기
        Intent intent = getIntent();
        String msg = intent.getStringExtra("Email");
        String msg1 = intent.getStringExtra("Add");
        String msg2 = intent.getStringExtra("ID");
        String msg3 = intent.getStringExtra("Birth");
        String msg4 = intent.getStringExtra("Gender");
        String msg5 = intent.getStringExtra("Height");
        String msg6 = intent.getStringExtra("Weight");
        mBottomNV.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                switch (menuItem.getItemId()) {
                    case R.id.navigation_1:
                        selectedFragment = new Fragment1();
                        break;
                    case R.id.navigation_2:
                        selectedFragment = new Fragment2();
                        break;
                    case R.id.navigation_3:
                        selectedFragment = new Fragment3();
                        break;
                    case R.id.navigation_4:
                        selectedFragment = new Fragment4();
                        break;
                    case R.id.navigation_5:
                        selectedFragment = new Fragment5();
                        break;
                }

                if (selectedFragment != null) {
                    Bundle bundle = new Bundle();
                    bundle.putString("Email", msg);
                    bundle.putString("ID", msg2);
                    bundle.putString("Weight", msg6);
                    bundle.putString("Height", msg5);
                    bundle.putString("Birth", msg3);
                    bundle.putString("Gender", msg4);
                    bundle.putString("Add", msg1);
                    selectedFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, selectedFragment)
                            .commit();
                    return true;
                }

                return false;
            }
        });
        mBottomNV.setSelectedItemId(R.id.navigation_5);

    }
}