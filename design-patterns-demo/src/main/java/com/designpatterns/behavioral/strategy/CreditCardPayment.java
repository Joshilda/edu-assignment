package com.designpatterns.behavioral.strategy;
import com.designpatterns.creational.singleton.AppLogger;
public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    public CreditCardPayment(String cardNumber) {
        if (cardNumber == null || cardNumber.length() < 13) {
            throw new IllegalArgumentException("Invalid card number");
        }
        this.cardNumber = cardNumber;
    }
    @Override
    public void pay(int amount) throws Exception {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        AppLogger.getInstance().info("Processing credit card payment for amount " + amount);
        System.out.println("Paid " + amount + " using Credit Card ending " + cardNumber.substring(cardNumber.length()-4));
    }
}
