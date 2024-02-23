package com.example.moagym;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText Email, Password;
    Button Login, Signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Email = findViewById(R.id.idInput);
        Password = findViewById(R.id.pwInput);
        Login = findViewById(R.id.loginButton);
        Signup = findViewById(R.id.signupButton);

        //로그인 버튼 이벤트
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                String pw = Password.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try
                        {
                            response = response.replace("\n", "\\n");
                            response = response.replace("\r", "\\r");
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success)
                            {
                                String msg = jsonObject.getString("Email");
                                String msg1 = jsonObject.getString("Add");
                                String msg2 = jsonObject.getString("ID");
                                String msg3 = jsonObject.getString("Birth");
                                String msg4 = jsonObject.getString("Gender");
                                String msg6 = jsonObject.getString("Height");
                                String msg5 = jsonObject.getString("Weight");
                                //Toast.makeText(getApplicationContext(), "로그인 성공. ID :" + email, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                intent.putExtra("Email", msg);
                                intent.putExtra("Add", msg1);
                                intent.putExtra("ID", msg2);
                                intent.putExtra("Birth", msg3);
                                intent.putExtra("Gender", msg4);
                                intent.putExtra("Height", msg5);
                                intent.putExtra("Weight", msg6);
                                startActivity(intent);
                            }

                            else
                            {
                                Toast.makeText(getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        catch (JSONException e)
                        {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "예외 1", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequestActivity loginRequestActivity = new LoginRequestActivity(email, pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                queue.add(loginRequestActivity);
            }
        });


        //회원가입 버튼 이벤트
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
