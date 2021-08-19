package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.ShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements ICommand, IUndoable {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas;
    private int deltaX, deltaY;
    private final ShapeList movedShapes = new ShapeList();
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

    @Override
    public void run() {
        for (IShape shape : masterList) {
            if (shape.getSelected()) {
                shape.moveShape(deltaX,deltaY);
                movedShapes.add(shape);
            }
        }
        CommandHistory.add(this);
        paintCanvas.repaint();
        System.out.println("Shape(s) moved");
    }

    @Override
    public void undo() {
        for (IShape shape : movedShapes.getShapeList()) {
            shape.undoMove(deltaX, deltaY);
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        for (IShape shape : movedShapes.getShapeList()) {
            shape.moveShape(deltaX,deltaY);
        }
        paintCanvas.repaint();
    }
}
