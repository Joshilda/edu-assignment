package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.EntityNotFoundException;

import java.util.List;

public class RemoveClassroomCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            System.out.println("Usage: remove_classroom <ClassName>");
            return;
        }
        String className = args.get(0);
        try {
            ClassroomManager.getInstance().removeClassroom(className);
            System.out.printf("Classroom %s removed.%n", className);
        } catch (EntityNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
