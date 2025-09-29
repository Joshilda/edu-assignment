package com.designpatterns.behavioral.observer;
import com.designpatterns.creational.singleton.AppLogger;
public class DisplayDevice implements Observer {
    private final String name;
    public DisplayDevice(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("DisplayDevice name required");
        }
        this.name = name;
    }
    @Override
    public void update(String data) {
        AppLogger.getInstance().info(name + " display received weather update: " + data);
        System.out.println(name + " display: Weather is " + data);
    }
}
