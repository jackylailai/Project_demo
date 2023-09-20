package org.example.model;

public class UserDataResponse {
    private String name;
    private Long grade;
    private int level;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getGrade() {
        return grade;
    }
    public void setGrade(Long grade) {
        this.grade = grade;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
}