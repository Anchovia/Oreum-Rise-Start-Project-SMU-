package com.example.smuproject;

import androidx.appcompat.app.AppCompatActivity;

// 안드로이드 스튜디오 기본 뷰 import
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

// 카카오맵 뷰 import
import net.daum.mf.map.api.MapView;

// 파이어베이스 관련 import
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 맵 뷰 선언
        MapView mapView = new MapView(this);

        ViewGroup mapViewContainer = (ViewGroup) findViewById(R.id.map_view);
        mapViewContainer.addView(mapView);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){
            startSignupActivity();

        }

        // 이동 카드뷰 코드 부분
        {
            // 핫 게시판 아이콘 선언
            ImageButton Button_gotoHotBoard = findViewById(R.id.Button_gotoHotBoard);
            Button_gotoHotBoard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 셋팅 아이콘을 클릭했을때 SettingActivity로 이동
                    Intent intent = new Intent(MainActivity.this, HotBoardActivity.class);
                    startActivity(intent);
                }
            });

            // 공유 게시판 아이콘 선언
            ImageButton Button_gotoShareBoard = findViewById(R.id.Button_gotoShareBoard);
            Button_gotoShareBoard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 셋팅 아이콘을 클릭했을때 SettingActivity로 이동
                    Intent intent = new Intent(MainActivity.this, ShareBoardActivity.class);
                    startActivity(intent);
                }
            });
        }

        // 상단바 코드 부분
        {
            // 셋팅 아이콘 선언
            ImageButton button_gotoSetting = findViewById(R.id.button_gotoSetting);
            button_gotoSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 셋팅 아이콘을 클릭했을때 SettingActivity로 이동
                    Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
            });

            // 내정보 아이콘 선언
            ImageButton button_gotoMyInfo = findViewById(R.id.button_gotoMyInfo);
            button_gotoMyInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 내정보 아이콘을 클릭했을때 MyInfoActivity로 이동
                    Intent intent = new Intent(MainActivity.this, MyInfoActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

    private void startSignupActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
