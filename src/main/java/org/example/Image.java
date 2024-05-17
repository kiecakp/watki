package org.example;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    private BufferedImage image;

    public void loadImage(String path) throws IOException {
        File file = new File(path);
        image = ImageIO.read(file);
    }
    public void saveImagePNG(String path) throws IOException {
        File file = new File(path);
        ImageIO.write(image, "png", file);
    }
    public void saveImageJPG(String path) throws IOException {
        File file = new File(path);
        ImageIO.write(image, "jpg", file);
    }

    public void changeBrightness(int factor){
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                int pixel = image.getRGB(x, y);
                pixel = changeBrightnessPixel(pixel, factor);
                image.setRGB(x, y, pixel);
            }
        }
    }

    public void changeBrightnessWithThreads(int factor){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        Thread threads[] = new Thread[availableProcessors];

        for(int i = 0; i < threads.length; i++){
            final int temp = i;

            threads[i] = new Thread(() -> {
                int start = image.getHeight() * temp / availableProcessors;
                int end = start + image.getHeight() / availableProcessors;


            });
        }
    }

    private int changeBrightnessPixel(int pixel, int factor){
        int mask = 255;
        int blue = pixel & mask;
        int green = (pixel >> 8) & mask;
        int red = (pixel >> 16) & mask;
        blue = brightnessPixelPart(blue, factor);
        green = brightnessPixelPart(green, factor);
        red = brightnessPixelPart(red, factor);
        return (red << 16) + (green << 8) + blue;
    }
    private int brightnessPixelPart(int color, int factor){
        color += factor;
        if(color > 255){
            return 255;
        } else{
            return color;
        }
    }
}
