package com.virtualclassroom.commands;

import java.util.List;

public class HelpCommand implements Command {
    @Override
    public void execute(List<String> args) {
        System.out.println("Available commands:");
        System.out.println(" - add_classroom <ClassName>");
        System.out.println(" - remove_classroom <ClassName>");
        System.out.println(" - list_classrooms");
        System.out.println(" - add_student <StudentID> <ClassName> [StudentName]");
        System.out.println(" - list_students <ClassName>");
        System.out.println(" - schedule_assignment <ClassName> \"<AssignmentTitle>\" [\"<Description>\"]");
        System.out.println(" - submit_assignment <StudentID> <ClassName> \"<AssignmentTitle>\"");
        System.out.println(" - help");
        System.out.println(" - exit");
    }
}
