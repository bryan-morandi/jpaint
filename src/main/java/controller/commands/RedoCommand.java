package controller.commands;

import controller.commands.interfaces.ICommand;

public class RedoCommand implements ICommand {

    @Override
    public void run() { CommandHistory.redo(); }
}
