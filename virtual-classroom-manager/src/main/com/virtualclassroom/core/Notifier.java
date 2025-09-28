package com.virtualclassroom.core;

import com.virtualclassroom.models.Classroom;
import com.virtualclassroom.models.Student;

import java.util.Objects;

public class Notifier {

    private static final Notifier INSTANCE = new Notifier();

    private Notifier() {}

    public static Notifier getInstance() {
        return INSTANCE;
    }

    public void notifyStudentsOfAssignment(Classroom classroom, String assignmentTitle) {
        Objects.requireNonNull(classroom);
        String message = String.format("New assignment '%s' has been scheduled for classroom '%s'.", assignmentTitle, classroom.getName());
        classroom.getStudents().forEach(s -> s.notify(message));
    }
}
