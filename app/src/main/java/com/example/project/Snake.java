package com.example.project;

public class Snake {

    private double maximumLength;
    private double averageLength;

    private String name;

    public Snake(double maximumLength, double averageLength, String name) {
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
