package com.example.smuproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity  extends LoginActivity {
    @Override
    protected void onCreate(Bundle savedInstanceStare) {
        super.onCreate(savedInstanceStare);
        setContentView(R.layout.activity_loading);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class); // 로딩 엑티비티 출력 후 로그인 엑티비티로 이동
                startActivity(intent);
                finish();
            }
        },2000); // 2초 후 페이지 넘김
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
