package com.designpatterns.behavioral.observer;
import java.util.ArrayList;
import java.util.List;
import com.designpatterns.creational.singleton.AppLogger;
public class WeatherStation implements Subject {
    private final List<Observer> observers = new ArrayList<>();
    private String weather;
    @Override
    public void addObserver(Observer o) {
        if (o == null) {
            AppLogger.getInstance().warning("Attempted to add null observer");
            return;
        }
        observers.add(o);
    }
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
    @Override
    public void notifyObservers() {
        for (Observer o : new ArrayList<>(observers)) {
            try {
                o.update(weather);
            } catch (Exception e) {
                AppLogger.getInstance().severe("Observer update failed: " + e.getMessage());
            }
        }
    }
    public void setWeather(String weather) {
        if (weather == null || weather.isBlank()) {
            AppLogger.getInstance().warning("Invalid weather update attempted");
            return;
        }
        this.weather = weather;
        AppLogger.getInstance().info("Weather updated to " + weather);
        notifyObservers();
    }
}
