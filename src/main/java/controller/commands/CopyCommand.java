package controller.commands;

import controller.commands.interfaces.ICommand;
import view.Shapes.MasterShapeList;
import view.Shapes.Shape;
import view.interfaces.IShape;

import java.util.ArrayList;

public class CopyCommand implements ICommand {
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
    private final ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();

    @Override
    public void run() {
        clipBoard.clear();
        for (IShape shape: masterList) {
            if (shape.getSelected()) {
                //Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.getPasted());
                shape.resetPasted();
                clipBoard.add(shape);
                }
        }
        System.out.println(clipBoard.size() + " Shapes copied");
    }
}
