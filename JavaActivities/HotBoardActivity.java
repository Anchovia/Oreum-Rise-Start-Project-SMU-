package com.example.smuproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class HotBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_board);

        // 상단바 코드 부분
        {
            // 셋팅 아이콘 선언
            ImageButton button_gotoSetting = findViewById(R.id.button_gotoSetting);
            button_gotoSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 셋팅 아이콘을 클릭했을때 SettingActivity로 이동
                    Intent intent = new Intent(HotBoardActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
            });

            // 내정보 아이콘 선언
            ImageButton button_gotoMyInfo = findViewById(R.id.button_gotoMyInfo);
            button_gotoMyInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 내정보 아이콘을 클릭했을때 MyInfoActivity로 이동
                    Intent intent = new Intent(HotBoardActivity.this, MyInfoActivity.class);
                    startActivity(intent);
                }
            });
        }

        // 하단바 코드 부분
        {
            // 홈 버튼 선언
            ImageButton Button_gotoHome = findViewById(R.id.Button_gotoHome);
            Button_gotoHome.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 홈 버튼을 클릭했을때 MainActivity으로 이동
                    Intent intent = new Intent(HotBoardActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }

    }
}
