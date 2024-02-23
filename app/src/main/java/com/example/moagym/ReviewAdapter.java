package com.example.moagym;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Fragment6.Review> {
    public ReviewAdapter(Context context, List<Fragment6.Review> reviews) {
        super(context, 0, reviews);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_review_adapter, parent, false);
        }

        // 현재 위치의 리뷰 데이터 가져오기
        Fragment6.Review review = getItem(position);

        // 뷰에 데이터 설정
        TextView usernameTextView = convertView.findViewById(R.id.usernameTextView);
        TextView commentTextView = convertView.findViewById(R.id.commentTextView);

        if (review != null) {
            usernameTextView.setText(review.getUsername());
            commentTextView.setText(review.getComment());
        }

        return convertView;
    }
}