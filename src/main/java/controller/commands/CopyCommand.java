package controller.commands;

import controller.commands.interfaces.ICommand;
import view.Shapes.MasterShapeList;
import view.interfaces.IShape;

import java.util.ArrayList;

public class CopyCommand implements ICommand {

    @Override
    public void run() {
        ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
        ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();
        clipBoard.clear();
        for (IShape shape: masterList) {
            if (shape.getSelected())
                clipBoard.add(shape);
        }
        System.out.println(clipBoard.size() + " Shapes copied");
    }
}
