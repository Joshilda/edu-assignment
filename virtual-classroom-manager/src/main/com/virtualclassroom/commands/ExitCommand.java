package com.virtualclassroom.commands;

import java.util.List;

public class ExitCommand implements Command {
    @Override
    public void execute(List<String> args) {
        System.out.println("Exiting application. Goodbye!");
    }
}
