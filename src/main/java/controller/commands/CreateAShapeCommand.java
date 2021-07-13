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
    private Shape shape;

    public CreateAShapeCommand(Point pressedPoint, Point releasedPoint, PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;

        /* builder automatically sets (X, Y, height, width) from
         pressedPoint and releasedPoint*/
        shape = new Shape(pressedPoint, releasedPoint, appState.getActiveShapeType(), appState.getActiveShapeShadingType(),
                appState.getActivePrimaryColor().getColor(), appState.getActiveSecondaryColor().getColor(),false);
        shape = new ShapeBuilder()
                .pressedPoint(shape.getPressedPoint())
                .releasedPoint(shape.getReleasedPoint())
                .shapeType(shape.getShapeType())
                .shadingType(shape.getShadingType())
                .primaryColor(shape.getPrimaryColor())
                .secondaryColor(shape.getSecondaryColor())
                .selectedStatus(shape.getSelected())
                .buildShape();
    }

    @Override
    public void run() {
        System.out.println("X" + shape.getX() + " Y" + shape.getY() + " W" + shape.getWidth() + " H" + shape.getHeight());
        MasterShapeList.masterShapeList.add(shape);
        paintCanvas.repaint();
        CommandHistory.add(this);

        //only upon mouse click will the selected status of the MasterShapeList reset
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            shape.setSelected(false);
        }
    }

    @Override
    public void undo() {
        MasterShapeList.masterShapeList.remove(shape);
        paintCanvas.repaint();
        System.out.println("Undo");

        }

    @Override
    public void redo() {
        MasterShapeList.masterShapeList.add(shape);
        paintCanvas.repaint();
        System.out.println("Redo");

    }
}
