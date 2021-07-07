package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.Shapes.*;
import view.Shapes.Shape;
import view.gui.PaintCanvas;

import java.awt.*;

public class CreateAShapeCommand implements ICommand, IUndoable {
    private final PaintCanvas paintCanvas;
    private final Shape shape;

    public CreateAShapeCommand(Point pressedPoint, Point releasedPoint, PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;

        /* builder automatically sets (X, Y, height, width) from
         pressedPoint and releasedPoint*/
        shape = new ShapeBuilder(pressedPoint, releasedPoint)
                .primaryColor(appState.getActivePrimaryColor().getColor())
                .secondaryColor(appState.getActiveSecondaryColor().getColor())
                .shapeType(appState.getActiveShapeType())
                .shadingType(appState.getActiveShapeShadingType())
                .buildShape();
    }

    @Override
    public void run() {
        shape.add();
        paintCanvas.repaint();
        CommandHistory.add(this);
        /*note because we have not implemented Triangle/Ellipses yet, choosing
        them to draw will mess up the CommandHistory print statements*/
    }

    @Override
    public void undo() {
        shape.remove();
        paintCanvas.repaint();
        System.out.println("Undo");

        }

    @Override
    public void redo() {
        shape.add();
        paintCanvas.repaint();
        System.out.println("Redo");

    }
}
