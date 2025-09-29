package com.designpatterns.structural.adapter;
import com.designpatterns.creational.singleton.AppLogger;
public class PrinterAdapter implements NewPrinter {
    private final OldPrinter oldPrinter;
    public PrinterAdapter(OldPrinter oldPrinter) {
        if (oldPrinter == null) {
            throw new IllegalArgumentException("OldPrinter required");
        }
        this.oldPrinter = oldPrinter;
    }
    @Override
    public void print(String message) {
        if (message == null) {
            AppLogger.getInstance().warning("PrinterAdapter received null message");
            return;
        }
        oldPrinter.printText(message);
    }
}
