package com.example.project;

public class Snake {

    private int maximumLength;
    private int averageLength;

    private String name;

    public Snake(int maximumLength, int averageLength, String name) {
        this.maximumLength = maximumLength;
        this.averageLength = averageLength;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Snake{" +
                "maximumLength=" + maximumLength +
                ", averageLength=" + averageLength +
                ", name='" + name + '\'' +
                '}';
    }
}
