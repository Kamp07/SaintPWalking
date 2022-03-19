package com.company;

public class Place {
    private final String name;
    private final double time;
    private final int importance;

    public Place(String name, double time, int importance) {
        this.name = name;
        this.time = time;
        this.importance = importance;
    }

    public String getName() {
        return name;
    }

    public double getTime() {
        return time;
    }

    public int getImportance() {
        return importance;
    }
}
