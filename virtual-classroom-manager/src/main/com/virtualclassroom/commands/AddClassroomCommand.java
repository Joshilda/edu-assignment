package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.ValidationException;

import java.util.List;

public class AddClassroomCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.isEmpty()) {
            System.out.println("Usage: add_classroom <ClassroomName>");
            return;
        }
        String name = args.get(0);
        try {
            ClassroomManager.getInstance().addClassroom(name);
            System.out.printf("Classroom %s has been created.%n", name);
        } catch (ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
