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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    // FirebaseAuth 선언(변수명: mFirebaseAuth)
    private FirebaseAuth mFirebaseAuth; // 파이어베이스 인증처리
    private DatabaseReference mDatabaseRef; // 실시간 데이터베이스
    private EditText mEtEmail, mEtPwd; // 회원가입 입력필드
    private Button mBtnRegister; // 회원가입 입력버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mFirebaseAuth = FirebaseAuth.getInstance(); // FirebaseAuth 사용 준비 완료
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("App Name"); // ***

        // id find
        mEtEmail = findViewById(R.id.editText_email);
        mEtPwd = findViewById(R.id.editText_password);
        mBtnRegister = findViewById(R.id.button_signup);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 회원가입 버튼이 클릭 되었을때 처리 시작
                // 입력한 문자열 가져와 저장
                String strEmail = mEtEmail.getText().toString();
                String strPwd = mEtPwd.getText().toString();

                // Firebase Auth 진행
                mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // 회원가입 처리가 완료 되었을 때 실행
                        // 만약 가입 성공을 했으면
                        if(task.isSuccessful()){
                            FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser(); // 현재 회원가입이 된 유저의 데이터를 가져옴
                            UserAccount account = new UserAccount();
                            account.setIdToken(firebaseUser.getUid()); // 로그인된 유저의 Uid 를 정확히 가져옴
                            account.setEmailId(firebaseUser.getEmail()); // 로그인된 유저 이메일을 정확히 가져옴
                            account.setPassword(strPwd); // 로그인 시 입력 했던 Password 를 가져옴

                            // setValue: Database에 insert(삽입) 하는 행위
                            mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);

                            Toast.makeText(SignupActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show(); // 회원가입 성공 시 성공 멘트 출력

                            // Intent 설정
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);

                            // 데이터 Intent 설정
                            // ~~~
                        }else{
                            Toast.makeText(SignupActivity.this, "회원가입에 실패하였습니다.", Toast.LENGTH_SHORT).show(); // 회원가입 실패 시 성공 멘트 출력
                        }
                    }
                });

            }
        });


    }
}
