package com.company;

public class Main {

    public static void main(String[] args) {
        Walking walking = new Walking();
        try {
            walking.buildRoute();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
