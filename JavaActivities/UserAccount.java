package com.example.smuproject;

// 사용자 계정 정보 모델 클래스
public class UserAccount {
    private String idToken; // Firebase Uid (고유 토큰정보)
    private String emailId; // 이메일 아이디
    private String password; // 비밀번호

    // 클래스가 생성될 때 가장 먼저 호출되는 지점
    public UserAccount(){ } // Firebase 에서 빈 생성자를 만들지 않으면 데이터를 조회해 올 때 오류가 뜨니 주의

    // 데이터를 설정하고, 가져오고 (getter and setter)

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
