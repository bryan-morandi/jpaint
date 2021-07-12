package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;

public class MoveCommand implements ICommand, IUndoable {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas;
    private int deltaX, deltaY;

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

    public IShape redoMove(IShape shape) {
        shape.setX(shape.getX() - deltaX);
        shape.setY(shape.getY() - deltaY);
        return shape;
    }

    @Override
    public void run() {
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            if (shape.getSelected()) {
                MasterShapeList.movedShapeList.add(move(shape));
                CommandHistory.add(this);
            }
        }
        paintCanvas.repaint();
    }

    @Override
    public void undo() {
        for (IShape shape : MasterShapeList.movedShapeList.getShapeList()) {
            MasterShapeList.movedShapeList.add(redoMove(shape));
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        for (IShape shape : MasterShapeList.movedShapeList.getShapeList()) {
            move(shape);
        }
        paintCanvas.repaint();
    }
}
