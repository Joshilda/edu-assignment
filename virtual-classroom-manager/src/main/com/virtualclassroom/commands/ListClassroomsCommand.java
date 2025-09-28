package com.virtualclassroom.commands;

import com.virtualclassroom.core.ClassroomManager;

import java.util.List;

public class ListClassroomsCommand implements Command {
    @Override
    public void execute(List<String> args) {
        var list = ClassroomManager.getInstance().listClassrooms();
        if (list.isEmpty()) {
            System.out.println("No classrooms available.");
            return;
        }
        System.out.println("Classrooms:");
        list.forEach(c -> System.out.println(" - " + c));
    }
}
