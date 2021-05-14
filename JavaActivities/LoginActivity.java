// 로그아웃: 홍드로이드 #47 - 46:50

package com.example.smuproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    // FirebaseAuth 선언(변수명: mFirebaseAuth)
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; // 로그인 입력필드


    // 버튼 'button_login' 선언
    private Button button_login;
    // 버튼 'button_gotoSignup' 선언
    private Button button_gotoSignup;

    // 최초 실행 파트
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance(); // FirebaseAuth 사용 준비 완료
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("App Name"); // ***

        // id find
        mEtEmail = findViewById(R.id.editText_email); // R: res 폴더, .: 파일(하위 폴더)로 들어가서, id: id를 찾아달라
        mEtPwd = findViewById(R.id.editText_password);

        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그인 요청
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // 로그인 성공 시
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish(); // 현재 액티비티 파괴
                        }else{
                            Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        // id를 찾아오기
        button_gotoSignup = findViewById(R.id.button_gotoSignup);
        button_gotoSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent 설정
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class); // LoginActivity에서 SignupActiviy로 이동 방향 설정
                startActivity(intent); // Activity 이동 실행
            }
        });

    }
}
