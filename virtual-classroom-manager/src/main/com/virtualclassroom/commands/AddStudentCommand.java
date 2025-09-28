package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.EntityNotFoundException;
import com.virtualclassroom.exceptions.ValidationException;

import java.util.List;

public class AddStudentCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.size() < 2) {
            System.out.println("Usage: add_student <StudentID> <ClassName> [StudentName]");
            return;
        }
        String studentId = args.get(0);
        String classroomName = args.get(1);
        String studentName = args.size() >= 3 ? args.get(2) : studentId;
        try {
            ClassroomManager.getInstance().addStudentToClassroom(studentId, studentName, classroomName);
            System.out.printf("Student %s has been enrolled in %s.%n", studentId, classroomName);
        } catch (EntityNotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
