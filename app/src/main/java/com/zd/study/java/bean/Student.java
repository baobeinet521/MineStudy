package com.zd.study.java.bean;

public class Student<T> extends Human {
    private int age;
    private T score;

    public void setAge(int newAge) {
        this.age = newAge;
    }

    public int getAge() {
        return this.age;
    }

    public T getScore() {
        return score;
    }

    public void setScore(T score) {
        this.score = score;
    }
}
