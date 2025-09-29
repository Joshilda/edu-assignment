package com.designpatterns.behavioral.strategy;
import com.designpatterns.creational.singleton.AppLogger;
public class PayPalPayment implements PaymentStrategy {
    private final String accountEmail;
    public PayPalPayment(String accountEmail) {
        if (accountEmail == null || accountEmail.isBlank()) {
            throw new IllegalArgumentException("Invalid PayPal account");
        }
        this.accountEmail = accountEmail;
    }
    @Override
    public void pay(int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        AppLogger.getInstance().info("Processing PayPal payment for amount " + amount);
        System.out.println("Paid " + amount + " using PayPal account " + accountEmail);
    }
}
