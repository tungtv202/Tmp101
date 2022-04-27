package me.tungexplorer.study.pattern.command;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

// Receiver class - is an object that performs a set of cohesive actions.
@RequiredArgsConstructor
class TextFile {
    private final String name;

    public String open() {
        String result = "Opening file " + name;
        System.out.println(result);
        return result;
    }

    public String save() {
        String result = "Saving file " + name;
        System.out.println(result);
        return result;
    }
}

// Command class - o store all the information required for executing an action
@FunctionalInterface
interface TextFileOperation {
    String execute();
}

@RequiredArgsConstructor
class OpenTextFileOperation implements TextFileOperation {

    private final TextFile textFile;

    @Override
    public String execute() {
        return textFile.open();
    }
}

@RequiredArgsConstructor
class SaveTextFileOperation implements TextFileOperation {

    private final TextFile textFile;

    @Override
    public String execute() {
        return textFile.save();
    }
}

// Invoker class - knows how to execute a given command but doesn't know how the command has been implemented
class TextFileOperationExecutor {
    private final List<TextFileOperation> textFileOperations = new ArrayList<>();

    public String executeOperation(TextFileOperation textFileOperation) {
        textFileOperations.add(textFileOperation);
        return textFileOperation.execute();
    }
}

// Client class -  controls the command execution process
public class CommandDemo {
    public static void main(String[] args) {
        TextFileOperationExecutor textFileOperationExecutor = new TextFileOperationExecutor();
        textFileOperationExecutor.executeOperation(new OpenTextFileOperation(new TextFile("file1.txt")));
        textFileOperationExecutor.executeOperation(new SaveTextFileOperation(new TextFile("file2.txt")));
    }
}