package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.ShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;

public class MoveCommand implements ICommand, IUndoable {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas;
    private int deltaX, deltaY;
    private final ShapeList movedShapes = new ShapeList();

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
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            if (shape.getSelected()) {
                movedShapes.add(move(shape));
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
