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

public class MoveCommand implements ICommand, IUndoable {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas;
    private int deltaX, deltaY;
    private final ShapeList movedShapes = new ShapeList();
    private final ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();

    public MoveCommand(Point PressedPoint, Point ReleasedPoint, PaintCanvas PaintCanvas) {
        this.pressedPoint = PressedPoint;
        this.releasedPoint = ReleasedPoint;
        this.paintCanvas = PaintCanvas;
        findDelta();
    }

    public void findDelta() {
        deltaX = releasedPoint.x - pressedPoint.x;
        deltaY = releasedPoint.y - pressedPoint.y;
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
        for (IShape shape : masterList) {
            if (shape.getSelected()) {
                movedShapes.add(move(shape));
                if (clipBoard.contains(shape)) {
                    Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.getPasted());
                    copiedShape.resetPasted();
                    clipBoard.set(clipBoard.indexOf(shape),copiedShape);
                }
            }
        }
        CommandHistory.add(this);
        paintCanvas.repaint();
        System.out.println("Shape(s) moved");
    }

    @Override
    public void undo() {
        for (IShape shape : movedShapes.getShapeList()) {
            undoMove(shape);
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        for (IShape shape : movedShapes.getShapeList()) {
            move(shape);
        }
        paintCanvas.repaint();
    }
}
