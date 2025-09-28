package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.EntityNotFoundException;
import com.virtualclassroom.models.Student;

import java.util.List;

public class ListStudentsCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            System.out.println("Usage: list_students <ClassName>");
            return;
        }
        String className = args.get(0);
        try {
            var students = ClassroomManager.getInstance().listStudents(className);
            if (students.isEmpty()) {
                System.out.println("No students enrolled in " + className);
                return;
            }
            System.out.println("Students in " + className + ":");
            for (Student s : students) {
                System.out.printf(" - %s (%s)%n", s.getId(), s.getName());
            }
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
