package com.virtualclassroom.core;

import com.virtualclassroom.models.Assignment;
import com.virtualclassroom.models.Classroom;
import com.virtualclassroom.models.Student;

public class EntityFactory {

    private EntityFactory() {}

    public static Classroom createClassroom(String name) {
        return new Classroom(name);
    }

    public static Student createStudent(String id, String name) {
        return new Student(id, name);
    }

    public static Assignment createAssignment(String title, String description) {
        return new Assignment(title, description);
    }
}
