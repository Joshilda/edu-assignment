package com.designpatterns.creational.factory;
import com.designpatterns.creational.singleton.AppLogger;
public final class ShapeFactory {
    private ShapeFactory() {}
    public static Shape createShape(String type) {
        if (type == null || type.isBlank()) {
            AppLogger.getInstance().warning("ShapeFactory received invalid type");
            throw new IllegalArgumentException("Shape type required");
        }
        switch(type.toLowerCase()) {
            case "circle": return new Circle();
            case "rectangle": return new Rectangle();
            default: AppLogger.getInstance().warning("Unknown shape requested: " + type); throw new IllegalArgumentException("Unknown shape " + type);
        }
    }
}
