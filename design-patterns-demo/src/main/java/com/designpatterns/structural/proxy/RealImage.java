package com.designpatterns.structural.proxy;
import com.designpatterns.creational.singleton.AppLogger;
public class RealImage implements Image {
    private final String fileName;
    public RealImage(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName required");
        }
        this.fileName = fileName;
        loadFromDisk();
    }
    private void loadFromDisk() {
        AppLogger.getInstance().info("Loading image from disk: " + fileName);
        System.out.println("Loading image: " + fileName);
    }
    @Override
    public void display() {
        System.out.println("Displaying image: " + fileName);
    }
}
