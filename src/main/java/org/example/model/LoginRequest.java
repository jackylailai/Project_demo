package org.example.model;

public class LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    // Getters and setters
    //那麼在使用 @RequestBody 標註的方法中，只有 username 和 password 屬性的值會被轉換為 LoginRequest 物件的對應屬性，而 otherField 則會被忽略。
}