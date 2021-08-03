package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import model.persistence.ApplicationState;
import view.Shapes.*;
import view.Shapes.Shape;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;

public class CreateAShapeCommand implements ICommand, IUndoable {
    private final PaintCanvas paintCanvas;
    private final Shape shape;

    public CreateAShapeCommand(Point pressedPoint, Point releasedPoint, PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;

        /* builder automatically sets (X, Y, height, width) from
         pressedPoint and releasedPoint*/
        shape = new ShapeBuilder()
                .pressedPoint(pressedPoint)
                .releasedPoint(releasedPoint)
                .shapeType(appState.getActiveShapeType())
                .shadingType(appState.getActiveShapeShadingType())
                .primaryColor(appState.getActivePrimaryColor().getColor())
                .secondaryColor(appState.getActiveSecondaryColor().getColor())
                .selectedStatus(false)
                .buildShape();
    }

    @Override
    public void run() {
        System.out.println("X" + shape.getX() + " Y" + shape.getY() + " W" + shape.getWidth() + " H" + shape.getHeight());
        MasterShapeList.masterShapeList.add(shape);
        paintCanvas.repaint();
        CommandHistory.add(this);
        System.out.println("Shape created");

        //only upon mouse click will the selected status of the MasterShapeList reset
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            shape.setSelected(false);
        }
    }

    @Override
    public void undo() {
        MasterShapeList.masterShapeList.remove(shape);
        paintCanvas.repaint();
        }

    @Override
    public void redo() {
        MasterShapeList.masterShapeList.add(shape);
        paintCanvas.repaint();

    }
}
