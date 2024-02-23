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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Fragment7 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_page_7, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = view.findViewById(R.id.button);
        EditText id = view.findViewById(R.id.myid);
        EditText height = view.findViewById(R.id.myheight);
        EditText weight = view.findViewById(R.id.mywate);
        EditText add = view.findViewById(R.id.myadd);

        Bundle bundle = getArguments();
        String Email = bundle.getString("Email");
        String Id = bundle.getString("ID");
        String Weight = bundle.getString("Weight");
        String Height = bundle.getString("Height");
        String Add = bundle.getString("Add");

        id.setText(Id);
        height.setText(Height);
        weight.setText(Weight);
        add.setText(Add);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mod_id = id.getText().toString();
                String mod_height = height.getText().toString();
                String mod_weight = weight.getText().toString();
                String mod_add = add.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success)
                            {
                                Toast.makeText(getContext(), "회원정보 수정에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                Toast.makeText(getContext(), "다시 로그인해주세요.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }

                            else
                            {
                                Toast.makeText(getContext(), "회원등록에 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        catch(JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "JSON 오류"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                            return;
                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "예외", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                MyInfo myInfo = new MyInfo(Id, Email, mod_id, mod_height, mod_weight, mod_add, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getContext());
                queue.add(myInfo);

            }
        });
    }

    //id.setText("값 전달 안됨");
    //pass.setText("값 전달 안됨");
}

