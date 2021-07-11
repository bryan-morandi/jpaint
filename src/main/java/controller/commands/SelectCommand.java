package controller.commands;

import controller.commands.interfaces.ICommand;
import view.Shapes.BoundingBox;
import view.Shapes.DetectCollision;
import view.Shapes.MasterShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;

public class SelectCommand implements ICommand {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private final PaintCanvas paintCanvas;
    private int selectedCount = 0;

    public  SelectCommand(Point PressedPoint, Point ReleasedPoint, PaintCanvas PaintCanvas) {
        this.pressedPoint = PressedPoint;
        this.releasedPoint = ReleasedPoint;
        this.paintCanvas = PaintCanvas;
    }

    @Override
    public void run() {
        BoundingBox boundingBox = new BoundingBox(pressedPoint,releasedPoint);
        ArrayList<IShape> shapeList = MasterShapeList.masterShapeList.getShapeList();

        for (IShape shape : shapeList) {
            DetectCollision detectCollision = new DetectCollision(boundingBox, shape);
            shape.setSelected(detectCollision.run());
            if (shape.getSelected()) {
                selectedCount++;
            }
        }
        System.out.println("Number of selected shapes: " + selectedCount);
        }
    }
