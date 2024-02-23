package com.example.moagym;

import static android.content.Intent.getIntent;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Fragment5 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_page_5, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button);
        TextView email = view.findViewById(R.id.myemail);
        TextView id = view.findViewById(R.id.myid);
        TextView height = view.findViewById(R.id.myheight);
        TextView weight = view.findViewById(R.id.mywate);
        TextView birth = view.findViewById(R.id.mybirth);
        TextView gen = view.findViewById(R.id.myGen);
        TextView add = view.findViewById(R.id.myadd);

        Bundle bundle = getArguments();
        String Email = bundle.getString("Email");
        String Id = bundle.getString("ID");
        String Weight = bundle.getString("Weight");
        String Height = bundle.getString("Height");
        String Birth = bundle.getString("Birth");
        String Gen = bundle.getString("Gender");
        String Add = bundle.getString("Add");

        email.setText(Email);
        id.setText(Id);
        height.setText(Height);
        weight.setText(Weight);
        birth.setText(Birth);
        gen.setText(Gen);
        add.setText(Add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();

                // 새로운 Fragment를 추가하고 표시
                Fragment7 fragment7 = new Fragment7();
                Bundle bundle = new Bundle();
                bundle.putString("Email", Email);
                bundle.putString("ID", Id);
                bundle.putString("Weight", Weight);
                bundle.putString("Height", Height);
                bundle.putString("Birth", Birth);
                bundle.putString("Gender", Gen);
                bundle.putString("Add", Add);
                fragment7.setArguments(bundle);
                transaction.replace(R.id.fragment_container, fragment7); // R.id.fragment_container는 새 창을 표시할 레이아웃 컨테이너의 ID입니다.
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }

            //id.setText("값 전달 안됨");
            //pass.setText("값 전달 안됨");
}

