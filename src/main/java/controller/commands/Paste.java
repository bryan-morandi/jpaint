package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.Shape;
import view.interfaces.IShape;

import java.util.ArrayList;

public class Paste implements ICommand, IUndoable {
    private final ArrayList<IShape> pastedShapes;
    private final IShape shape;

    public Paste(ArrayList<IShape> pastedShapes, IShape shape) {
        this.pastedShapes = pastedShapes;
        this.shape = shape;
    }

    @Override
    public void run() {
        Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.incrementPastedCount());
        copiedShape.setX(shape.getX() + 50 * shape.getPastedCount());
        copiedShape.setY(shape.getY() + 50 * shape.getPastedCount());
        pastedShapes.add(copiedShape);
    }

    @Override
    public void undo() {
        if (shape.getPastedCount() > 0) {
            shape.decrementPastedCount();
        }

    }

    @Override
    public void redo() {
        if (shape.getPastedCount() > 0) {
            shape.incrementPastedCount();
        }

    }
}