package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.Shape;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.util.ArrayList;

public class Paste implements ICommand, IUndoable {
    private ArrayList<IShape> pastedShapes;
    private IShape shape;
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
    private final ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();

    public Paste(ArrayList<IShape> pastedShapes, IShape shape) {
        this.pastedShapes = pastedShapes;
        this.shape = shape;
    }

    @Override
    public void run() {
        Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.incrementPastedCount());
        copiedShape.setX(shape.getX() + 50 * shape.getPastedCount());
        copiedShape.setY(shape.getY() + 50 * shape.getPastedCount());
        //masterList.add(copiedShape);
        pastedShapes.add(copiedShape);
    }

    @Override
    public void undo() {
        //masterList.removeAll(pastedShapes);
        if (shape.getPastedCount() > 0) {
            shape.decrementPastedCount();
        }

    }

    @Override
    public void redo() {
        //masterList.addAll(pastedShapes);
        if (shape.getPastedCount() > 0) {
            shape.incrementPastedCount();
        }

    }
}