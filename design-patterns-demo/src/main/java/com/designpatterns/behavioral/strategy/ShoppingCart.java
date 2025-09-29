package com.designpatterns.behavioral.strategy;
import com.designpatterns.creational.singleton.AppLogger;
public class ShoppingCart {
    private PaymentStrategy strategy;
    public ShoppingCart(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("PaymentStrategy required");
        }
        this.strategy = strategy;
    }
    public void setStrategy(PaymentStrategy strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("PaymentStrategy required");
        }
        this.strategy = strategy;
    }
    public void checkout(int amount) {
        try {
            strategy.pay(amount);
        } catch (Exception e) {
            AppLogger.getInstance().severe("Payment failed: " + e.getMessage());
            System.err.println("Payment failed: " + e.getMessage());
        }
    }
}
