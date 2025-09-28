package com.virtualclassroom;

import com.virtualclassroom.commands.*;
import com.virtualclassroom.utils.InputParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class App {
    private static final Map<String, Command> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("add_classroom", new AddClassroomCommand());
        COMMANDS.put("remove_classroom", new RemoveClassroomCommand());
        COMMANDS.put("list_classrooms", new ListClassroomsCommand());
        COMMANDS.put("add_student", new AddStudentCommand());
        COMMANDS.put("list_students", new ListStudentsCommand());
        COMMANDS.put("schedule_assignment", new ScheduleAssignmentCommand());
        COMMANDS.put("submit_assignment", new SubmitAssignmentCommand());
        COMMANDS.put("help", new HelpCommand());
        COMMANDS.put("exit", new ExitCommand());
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Virtual Classroom Manager");
        System.out.println("Type 'help' for list of commands. Type 'exit' to quit.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input;
        boolean exit = false;
        while (!exit) {
            System.out.print("> ");
            input = br.readLine();
            if (input == null) break;
            input = input.trim();
            if (input.isBlank()) continue;
            List<String> tokens = InputParser.parse(input);
            if (tokens.isEmpty()) continue;
            String cmd = tokens.get(0).toLowerCase();
            Command command = COMMANDS.get(cmd);
            if (command == null) {
                System.out.println("Unknown command. Type 'help' to see available commands.");
                continue;
            }
            command.execute(tokens.size() > 1 ? tokens.subList(1, tokens.size()) : Collections.emptyList());
            if ("exit".equals(cmd)) exit = true;
        }
    }
}
