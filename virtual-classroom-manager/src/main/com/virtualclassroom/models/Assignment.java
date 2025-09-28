package com.virtualclassroom.models;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public class Assignment {
    private final String id;
    private final String title;
    private final String description;
    private final Instant createdAt;

    public Assignment(String title, String description) {
        this.id = UUID.randomUUID().toString();
        this.title = Objects.requireNonNull(title, "title cannot be null");
        this.description = description == null ? "" : description;
        this.createdAt = Instant.now();
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("%s (id=%s)", title, id);
    }
}
