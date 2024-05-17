package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Image image = new Image();
        try {
            image.loadImage("image.jpg");
            image.changeBrightness(100);
            image.saveImagePNG("image2.png");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Runtime.getRuntime().availableProcessors());

    }
}