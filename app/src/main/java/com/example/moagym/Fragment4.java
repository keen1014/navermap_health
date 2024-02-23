package com.example.moagym;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class Fragment4 extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_page_4, container, false);

        ListView listView = v.findViewById(R.id.listView);


// 예시 데이터
        List<ItemData> itemList = new ArrayList<>();
        itemList.add(new ItemData(R.drawable.heodak, "개그맨 허경환 이름 걸고 위풍 당당 허닭!! 특수 공법으로 퍼퍽함 없이 부드러운 목 넘김", 1));
        itemList.add(new ItemData(R.drawable.dedak, "[맛있닭] 건강하게 시작하는 저염식단! 저염·프로 닭가슴살 단백질이 가능하고 3% 밖에 안되는 염분! ", 2));
        itemList.add(new ItemData(R.drawable.deakstak, "[맛있닭] 닭으로 스테이크 썰고 싶을 때 맛있닭 스테이크! 고함량 단백질로 만나보세요!", 3));
        itemList.add(new ItemData(R.drawable.steam, "[맛있닭] 스팀 닭가슴살 마늘맛!! 맛도 챙기고 건강도 챙기고 19g 대용량 스팀 닭가슴살! ", 4));
        itemList.add(new ItemData(R.drawable.bbukbak, "[인생닭] 닭가슴살 곤약 갈릭 볶음밥 고함류 단백질과 영양듬뿍 볶음밥! 다이어트에 매우 특화된 밥!", 5));
// 추가적인 데이터 필요에 따라 계속 추가

        CustomAdapter adapter = new CustomAdapter(requireContext(), itemList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 클릭된 항목의 상세 정보를 표시하는 Fragment로 이동
                navigateToDetailFragment(itemList.get(position).getItemId());
            }
        });
        return v;
    }

    private void navigateToDetailFragment(int itemId) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("itemId", itemId);

        // itemId에 따라 다른 Fragment 호출
        Fragment detailFragment;
        switch (itemId) {
            case 1:
                detailFragment = new Fragment4Detail1();
                break;
            case 2:
                detailFragment = new Fragment4Detail2();
                break;
            case 3:
                detailFragment = new Fragment4Detail3();
                break;
            case 4:
                detailFragment = new Fragment4Detail4();
                break;
            case 5:
                detailFragment = new Fragment4Detail5();
                break;
            default:
                // 기본값으로 첫 번째 Fragment를 호출
                detailFragment = new Fragment4Detail1();
                break;
        }

        // 새로운 Fragment로 이동하면서 Bundle을 전달
        detailFragment.setArguments(bundle);

        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
