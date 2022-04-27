package me.tungexplorer.study.pattern.command;

import java.util.Stack;

// Receiver class - is an object that performs a set of cohesive actions.
class Document {
    private final Stack<String> lines = new Stack<>();

    public void write(String text) {
        lines.push(text);
    }

    public void eraseLast() {
        if (!lines.isEmpty()) {
            lines.pop();
        }
    }

    public void readDocument() {
        System.out.println("---Start reading document");
        lines.forEach(System.out::println);
        System.out.println("---Finish reading document");
    }
}

// Command class - o store all the information required for executing an action
interface Command {
    void undo();

    void redo();
}

class DocumentEditorCommand implements Command {
    private final Document document;
    private final String text;

    public DocumentEditorCommand(Document document, String text) {
        this.document = document;
        this.text = text;
        this.document.write(text);
    }

    @Override
    public void undo() {
        document.eraseLast();
    }

    @Override
    public void redo() {
        document.write(text);
    }
}

// Invoker class - knows how to execute a given command but doesn't know how the command has been implemented
class DocumentInvoker {
    private final Stack<Command> undoCommands = new Stack<>();
    private final Stack<Command> redoCommands = new Stack<>();
    private final Document document = new Document();

    public void undo() {
        if (!undoCommands.isEmpty()) {
            Command cmd = undoCommands.pop();
            cmd.undo();
            redoCommands.push(cmd);
        } else {
            System.out.println("Nothing to undo");
        }
    }

    public void redo() {
        if (!redoCommands.isEmpty()) {
            Command cmd = redoCommands.pop();
            cmd.redo();
            undoCommands.push(cmd);
        } else {
            System.out.println("Nothing to redo");
        }
    }

    public void write(String text) {
        Command cmd = new DocumentEditorCommand(document, text);
        undoCommands.push(cmd);
        redoCommands.clear();
    }

    public void read() {
        document.readDocument();
    }
}

// Client class -  controls the command execution process
public class UndoRedoExample {
    public static void main(String[] args) {
        DocumentInvoker instance = new DocumentInvoker();
        instance.write("The 1st text. ");
        instance.undo();
        instance.read(); // EMPTY

        instance.redo();
        instance.read(); // The 1st text.

        instance.write("The 2nd text. ");
        instance.write("The 3rd text. ");
        instance.read(); // The 1st text. The 2nd text. The 3rd text.
        instance.undo(); // The 1st text. The 2nd text.
        instance.undo(); // The 1st text.
        instance.undo(); // EMPTY
        instance.undo(); // Nothing to undo
    }
}