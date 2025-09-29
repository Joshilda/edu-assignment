package com.designpatterns.structural.proxy;
import com.designpatterns.creational.singleton.AppLogger;
public class ProxyImage implements Image {
    private RealImage realImage;
    private final String fileName;
    public ProxyImage(String fileName) {
        if (fileName == null || fileName.isBlank()) {
            throw new IllegalArgumentException("fileName required");
        }
        this.fileName = fileName;
    }
    @Override
    public void display() {
        if (realImage == null) {
            AppLogger.getInstance().info("ProxyImage creating RealImage for " + fileName);
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}
