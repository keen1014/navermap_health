package com.example.moagym;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Fragment3 extends Fragment {

    private TextView editTextWeight;
    private TextView editTextHeight;
    private TextView textViewResult;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_page_3, container, false);

    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextWeight = view.findViewById(R.id.editTextWeight);
        editTextHeight = view.findViewById(R.id.editTextHeight);
        textViewResult = view.findViewById(R.id.textViewResult);




        Button calculateButton = view.findViewById(R.id.calculateButton);
        Bundle bundle = getArguments();
        String Weight = bundle.getString("Weight");
        String Height = bundle.getString("Height");
        editTextHeight.setText(Height);
        editTextWeight.setText(Weight);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateBMI();
            }
        });

    }
    private void calculateBMI() {
        String weightStr = editTextWeight.getText().toString();
        String heightStr = editTextHeight.getText().toString();

        if (!weightStr.isEmpty() && !heightStr.isEmpty()) {
            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);

            float bmi = calculateBMIValue(weight, height);
            int roundedBMI = Math.round(bmi); // 소수점 이하 자릿수를 반올림하여 정수로 변환

            String result = interpretBMI(roundedBMI);

            textViewResult.setText("BMI 값: " + roundedBMI + " - " + result);
        } else {
            textViewResult.setText("BMI 값: 체중과 신장을 입력해주세요.");
        }
    }

    private float calculateBMIValue(float weight, float height) {
        // BMI 계산식 (체중(kg) / (신장(m) * 신장(m)))
        return weight / ((height / 100) * (height / 100));
    }

    private String interpretBMI(float bmi) {
        if (bmi < 18.5) {
            return "저체중입니다.";
        } else if (bmi < 23) {
            return "정상 체중입니다.";
        } else if (bmi < 25) {
            return "과체중입니다.";
        } else if (bmi < 30) {
            return "비만 입니다.";
        } else if (bmi < 35) {
            return "중도 비만입니다.";
        } else {
            return "고도 비만입니다.";
        }
    }
}