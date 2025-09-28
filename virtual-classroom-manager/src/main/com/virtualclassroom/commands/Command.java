package com.virtualclassroom.commands;

import java.util.List;

public interface Command {
    void execute(List<String> args);
}
