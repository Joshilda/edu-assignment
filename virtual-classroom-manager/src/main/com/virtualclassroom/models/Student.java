package com.virtualclassroom.models;

import java.util.Objects;

public class Student {
    private final String id;
    private final String name;

    public Student(String id, String name) {
        this.id = Objects.requireNonNull(id, "student id cannot be null");
        this.name = (name == null || name.isBlank()) ? id : name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void notify(String message) {
        System.out.printf("[Notification -> %s]: %s%n", id, message);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id.equals(student.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
