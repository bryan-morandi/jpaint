package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.ShapeList;
import view.gui.PaintCanvas;
import view.Shapes.Shape;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class Move implements ICommand, IUndoable {
    private int deltaX, deltaY;
    private IShape shape;
    private final ShapeList movedShapes = new ShapeList();
    private final ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();
    //private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();

    public Move(int deltaX, int deltaY, IShape shape) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.shape = shape;
    }

    public IShape move(IShape shape) {
        shape.setX(shape.getX() + deltaX);
        shape.setY(shape.getY() + deltaY);
        return shape;
    }

    public void undoMove(IShape shape) {
        shape.setX(shape.getX() - deltaX);
        shape.setY(shape.getY() - deltaY);
    }

    @Override
    public void run() {
        IShape movedShape = move(shape);
        movedShapes.add(movedShape);
        if (clipBoard.contains(shape)) {
            Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.getPastedCount());
            copiedShape.setX(movedShape.getX() - deltaX);
            copiedShape.setY(movedShape.getY() - deltaY);
            clipBoard.set(clipBoard.indexOf(shape),copiedShape);
        }
        //CommandHistory.add(this);
        System.out.println("Shape(s) moved");
    }

    @Override
    public void undo() {
        undoMove(shape);
        if (clipBoard.contains(shape)) {
            Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.getPastedCount());
            copiedShape.setX(shape.getX() - deltaX * shape.getPastedCount());
            copiedShape.setY(shape.getY() - deltaY * shape.getPastedCount());
            clipBoard.set(clipBoard.indexOf(shape), move(copiedShape));
        }
    }

    @Override
    public void redo() {
//        for (IShape shape : movedShapes.getShapeList()) {
            move(shape);
//        }
    }
}
