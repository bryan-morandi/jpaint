package controller.commands;

import model.persistence.ApplicationState;
import view.Shapes.*;
import view.gui.PaintCanvas;

import java.awt.*;

public class CreateAShapeCommand implements ICommand, IUndoable {
    private final PaintCanvas paintCanvas;

    private final ShapeBuilder shapeBuilder;

    private IShape shape;

    public CreateAShapeCommand(Point pressedPoint, Point releasedPoint, PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;


        this.shapeBuilder = new ShapeBuilder(pressedPoint, releasedPoint);
        // builder automatically finds X, Y, height, width
        shapeBuilder
                .setPrimaryColor(appState.getActivePrimaryColor().getColor())
                .setShapeType(appState.getActiveShapeType())
                .setShadingType(appState.getActiveShapeShadingType());
        System.out.println("X: " + shapeBuilder.getX() + ", Y: " + shapeBuilder.getY() + ", W: " + shapeBuilder.getWidth() + ", H: " + shapeBuilder.getHeight());

    }

    @Override
    public void run() {
        shape = shapeBuilder;
        switch (shape.getShapeType()) {
            case RECTANGLE:
                shape.add(); //adds shape to MasterShapeList
                shape.draw(paintCanvas.getGraphics2D());
                CommandHistory.add(this);
                System.out.println(MasterShapeList.masterShapeList.size());
                break;
            case TRIANGLE:
                System.out.println("Triangle not implemented yet");
                break;
            case ELLIPSE:
                System.out.println("Ellipse not implemented yet");
                break;
            default: System.out.println("Not a shape");
        }

    }

    @Override
    public void undo() {
        shape.remove();
        paintCanvas.repaint();
        System.out.println("Undo\n" + MasterShapeList.masterShapeList.size());

        }

    @Override
    public void redo() {
        shape.add();
        paintCanvas.repaint();
        System.out.println("Redo\n" + MasterShapeList.masterShapeList.size());

    }
}
