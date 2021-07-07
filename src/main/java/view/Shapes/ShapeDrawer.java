package view.Shapes;

import view.interfaces.IShape;

import java.awt.*;

public class ShapeDrawer {
    private final Graphics2D g2D;

    public ShapeDrawer(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public void draw() {
        for ( IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            switch (shape.getShapeType()) {
                case RECTANGLE:
                    g2D.setColor(shape.getPrimaryColor());
                    g2D.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    break;
                case TRIANGLE:
                case ELLIPSE:
                System.out.println("Shape not yet implemented");
                    break;
            }
        }
    }

}
