package com.example.project;

import com.google.gson.annotations.SerializedName;

public class Snake {
    @SerializedName("cost")
    private int maximumLength;
    @SerializedName("size")
    private int averageLength;

    private String name;

    private String auxdata;

    public Snake(int maximumLength, int averageLength, String name, String auxdata) {
        this.maximumLength = maximumLength;
        this.averageLength = averageLength;
        this.name = name;
        this.auxdata = auxdata;
    }

    public int getMaximumLength() {
        return maximumLength;
    }

    public int getAverageLength() {
        return averageLength;
    }

    public String getName() {
        return name;
    }

    public String getauxdata() {
        return auxdata;
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
