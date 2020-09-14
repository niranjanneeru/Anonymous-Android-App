package com.neeru.anonymous;

public class Credential {
    private String username;
    private String password;
    private int percent;

    public Credential(String username, String password, int percent) {
        this.username = username;
        this.password = password;
        this.percent = percent;
    }

    public Credential(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", percent=" + percent +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
}
