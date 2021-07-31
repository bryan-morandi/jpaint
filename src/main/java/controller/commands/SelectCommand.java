package controller.commands;

import controller.commands.interfaces.ICommand;
import view.Shapes.BoundingBox;
import view.Shapes.DetectCollision;
import view.Shapes.MasterShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;

public class SelectCommand implements ICommand {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas; // may need paint canvas later to print dotted line around shapes
    private int selectedCount = 0;

    public  SelectCommand(Point PressedPoint, Point ReleasedPoint, PaintCanvas PaintCanvas) {
        this.pressedPoint = PressedPoint;
        this.releasedPoint = ReleasedPoint;
        this.paintCanvas = PaintCanvas;
    }

    @Override
    public void run() {
        BoundingBox boundingBox = new BoundingBox(pressedPoint,releasedPoint);

        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            DetectCollision detectCollision = new DetectCollision(boundingBox, shape);
            shape.setSelected(detectCollision.run());
            if (shape.getSelected()) {
                selectedCount++;
            }
            paintCanvas.repaint();
        }
        System.out.println("Number of selected shapes: " + selectedCount);
        }
    }
