package com.example.moagym;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupActivity extends AppCompatActivity {

    EditText ID, Password, Email, Birth, Gender, address, height, Weight;
    Button Signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Signup = (Button) findViewById(R.id.signupButton);
        ID = (EditText) findViewById(R.id.signup_idInput);
        Password = (EditText) findViewById(R.id.signup_pwInput);
        Email = (EditText) findViewById(R.id.signup_emailInput);
        Birth = (EditText) findViewById(R.id.signup_birthInput);
        Gender = (EditText) findViewById(R.id.signup_genderInput);
        address = (EditText) findViewById(R.id.signup_addrInput);
        height = (EditText) findViewById(R.id.signup_height);
        Weight = (EditText) findViewById(R.id.signup_Weight);
        //회원가입 버튼 이벤트
        Signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String id = ID.getText().toString();
                String pw = Password.getText().toString();
                String email = Email.getText().toString();
                String birth = Birth.getText().toString();
                String gender = Gender.getText().toString();
                String add = address.getText().toString();
                String weight = Weight.getText().toString();
                String Height = height.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success)
                            {
                                Toast.makeText(getApplicationContext(), "회원등록에 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                            else
                            {
                                Toast.makeText(getApplicationContext(), "회원등록에 실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        catch(JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "JSON 오류", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "예외", Toast.LENGTH_SHORT).show();
                        }
                    }
                };

                SignupRequestActivity signupRequestActivity = new SignupRequestActivity(id, pw, birth, gender, email, add, Height, weight, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(signupRequestActivity);
            }
        });
    }
}
