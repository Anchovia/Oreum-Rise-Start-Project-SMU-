package com.example.smuproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

// widget 관련 import
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.gms.tasks.OnSuccessListener;

// Firebase 관련 import
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

// Picasso.lib 관련 import
import com.squareup.picasso.Picasso;

public class MyInfoActivity extends AppCompatActivity {

    StorageReference storageReference;
    FirebaseAuth fAuth;
    ImageView profileImage;

    FirebaseFirestore fStore;
    String userId;

    TextView Name, Address;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        
        profileImage = findViewById(R.id.ImageView_profile); // 프로필 이미지 id 불러오기
        
        // Firebase 스토리지 접근 인스턴스 선언
        fAuth = FirebaseAuth.getInstance();
        
        storageReference = FirebaseStorage.getInstance().getReference(); // Firestore Reference 불러오기
        fStore = FirebaseFirestore.getInstance(); // Firestore Instance 불러오기
        
        // 이름 및 주소 id 불러오기
        Name = findViewById(R.id.TextView_profileName);
        Address = findViewById(R.id.TextView_profileAddress);
        
        userId = fAuth.getCurrentUser().getUid(); // UID 불러오기
        
        // 유저 정보 데이터 불러오기 관련
        {
            DocumentReference documentReference = fStore.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                    Name.setText(documentSnapshot.getString("name"));
                    Address.setText(documentSnapshot.getString("address"));
                }
            });
        }


        // 내정보 수정 버튼 선언
        {
            Button button_gotoMyInfoSetting = findViewById(R.id.button_gotoMyInfoSetting);
            button_gotoMyInfoSetting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 내정보 수정 버튼을 클릭했을때 MyInfoSettingActivity로 이동
                    Intent intent = new Intent(MyInfoActivity.this, MyInfoSettingActivity.class);
                    startActivity(intent);
                }
            });
        }
        
        // 프로필 이미지 불러오기 관련
        {
            StorageReference profileRef = storageReference.child("users/" + fAuth.getCurrentUser().getUid() + "profile.jpg");
            profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Picasso.get().load(uri).into(profileImage);
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
                    Intent intent = new Intent(MyInfoActivity.this, SettingActivity.class);
                    startActivity(intent);
                }
            });

            // 내정보 아이콘 선언
            ImageButton button_gotoMyInfo = (ImageButton)findViewById(R.id.button_gotoMyInfo);
            button_gotoMyInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 내정보 아이콘을 클릭했을때 MyInfoActivity로 이동
                    Intent intent = new Intent(MyInfoActivity.this, MyInfoActivity.class);
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
                    Intent intent = new Intent(MyInfoActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
