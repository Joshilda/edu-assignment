package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.EntityNotFoundException;
import com.virtualclassroom.exceptions.ValidationException;

import java.util.List;

public class SubmitAssignmentCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.size() < 3) {
            System.out.println("Usage: submit_assignment <StudentID> <ClassName> \"<AssignmentTitle>\"");
            return;
        }
        String studentId = args.get(0);
        String className = args.get(1);
        String assignmentTitle = args.get(2);
        try {
            ClassroomManager.getInstance().submitAssignment(studentId, className, assignmentTitle);
        } catch (EntityNotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
