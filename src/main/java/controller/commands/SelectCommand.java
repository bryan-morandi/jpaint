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
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();

    public  SelectCommand(Point PressedPoint, Point ReleasedPoint, PaintCanvas PaintCanvas) {
        this.pressedPoint = PressedPoint;
        this.releasedPoint = ReleasedPoint;
        this.paintCanvas = PaintCanvas;
    }

    @Override
    public void run() {
        BoundingBox boundingBox = new BoundingBox(pressedPoint,releasedPoint);

        for (IShape shape : masterList) {
             shape.selectShape(boundingBox);
        }
        paintCanvas.repaint();
    }
}
