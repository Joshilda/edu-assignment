package com.designpatterns;
import com.designpatterns.behavioral.observer.DisplayDevice;
import com.designpatterns.behavioral.observer.WeatherStation;
import com.designpatterns.behavioral.strategy.CreditCardPayment;
import com.designpatterns.behavioral.strategy.PayPalPayment;
import com.designpatterns.behavioral.strategy.ShoppingCart;
import com.designpatterns.creational.factory.Shape;
import com.designpatterns.creational.factory.ShapeFactory;
import com.designpatterns.creational.singleton.AppLogger;
import com.designpatterns.structural.adapter.NewPrinter;
import com.designpatterns.structural.adapter.OldPrinter;
import com.designpatterns.structural.adapter.PrinterAdapter;
import com.designpatterns.structural.proxy.Image;
import com.designpatterns.structural.proxy.ProxyImage;
public class MainLauncher {
    public static void main(String[] args) {
        runObserverDemo();
        runStrategyDemo();
        runSingletonFactoryDemos();
        runAdapterDemo();
        runProxyDemo();
    }
    private static void runObserverDemo() {
        AppLogger.getInstance().info("Starting Observer Demo");
        WeatherStation station = new WeatherStation();
        station.addObserver(new DisplayDevice("Phone"));
        station.addObserver(new DisplayDevice("TV"));
        station.setWeather("Sunny");
        station.setWeather("Rainy");
    }
    private static void runStrategyDemo() {
        AppLogger.getInstance().info("Starting Strategy Demo");
        ShoppingCart cart = new ShoppingCart(new CreditCardPayment("4111222233334444"));
        cart.checkout(500);
        cart.setStrategy(new PayPalPayment("user@example.com"));
        cart.checkout(300);
    }
    private static void runSingletonFactoryDemos() {
        AppLogger.getInstance().info("Starting Singleton and Factory Demos");
        Shape circle = ShapeFactory.createShape("circle");
        Shape rectangle = ShapeFactory.createShape("rectangle");
        circle.draw();
        rectangle.draw();
    }
    private static void runAdapterDemo() {
        AppLogger.getInstance().info("Starting Adapter Demo");
        OldPrinter old = new OldPrinter();
        NewPrinter adapter = new PrinterAdapter(old);
        adapter.print("Hello from Adapter");
    }
    private static void runProxyDemo() {
        AppLogger.getInstance().info("Starting Proxy Demo");
        Image image = new ProxyImage("photo.jpg");
        image.display();
        image.display();
    }
}
