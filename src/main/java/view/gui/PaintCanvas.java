package view.gui;

import view.Shapes.IShape;
import view.Shapes.MasterShapeList;
import view.interfaces.PaintCanvasBase;

import java.awt.*;
import java.util.ArrayList;

public class PaintCanvas extends PaintCanvasBase {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }

    @Override
    /**
     * This is an event handler.  If this function gets called, its time to
     * draw the entire picture.
     * It you want to force a paint event, call aPaintCanvas.repaint()
     */
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        ArrayList<IShape> shapeList = MasterShapeList.masterShapeList.getShapeList();

        for (IShape shape : shapeList) {
            shape.draw(g2d);
        }


        // Filled in rectangle
//        Graphics2D graphics2d = (Graphics2D) g;
//        graphics2d.setColor(Color.GREEN);
//        graphics2d.fillRect(12, 13, 200, 400);
//
//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);
    }
}
