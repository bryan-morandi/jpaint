package controller.commands;

import controller.commands.interfaces.ICommand;

public class UndoCommand implements ICommand {

    @Override
    public void run() {
        CommandHistory.undo();
    }
}
