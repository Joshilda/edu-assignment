package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;
import com.virtualclassroom.exceptions.EntityNotFoundException;
import com.virtualclassroom.exceptions.ValidationException;

import java.util.List;

public class ScheduleAssignmentCommand implements Command {
    @Override
    public void execute(List<String> args) {
        if (args.size() < 2) {
            System.out.println("Usage: schedule_assignment <ClassName> \"<AssignmentTitle>\" [\"<Description>\"]");
            return;
        }
        String className = args.get(0);
        String title = args.get(1);
        String desc = args.size() >= 3 ? args.get(2) : "";
        try {
            ClassroomManager.getInstance().scheduleAssignment(className, title, desc);
            System.out.printf("Assignment for %s has been scheduled.%n", className);
        } catch (EntityNotFoundException | ValidationException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
