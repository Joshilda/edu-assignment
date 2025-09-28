package com.virtualclassroom.models;

import java.util.*;

public class Classroom {
    private final String name;
    private final Map<String, Student> students = new LinkedHashMap<>();
    private final Map<String, Assignment> assignments = new LinkedHashMap<>();

    public Classroom(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Classroom name cannot be blank");
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }

    public void addStudent(Student student) {
        students.putIfAbsent(student.getId(), student);
    }

    public boolean removeStudent(String studentId) {
        return students.remove(studentId) != null;
    }

    public Collection<Student> getStudents() {
        return Collections.unmodifiableCollection(students.values());
    }

    public boolean hasStudent(String studentId) {
        return students.containsKey(studentId);
    }

    public void addAssignment(Assignment assignment) {
        assignments.put(assignment.getId(), assignment);
    }

    public Collection<Assignment> getAssignments() {
        return Collections.unmodifiableCollection(assignments.values());
    }

    public Optional<Assignment> findAssignmentByTitle(String title) {
        return assignments.values().stream()
                .filter(a -> a.getTitle().equalsIgnoreCase(title))
                .findFirst();
    }

    public Optional<Assignment> findAssignmentById(String id) {
        return Optional.ofNullable(assignments.get(id));
    }
}
