package view.Shapes;

import view.Shapes.IShape;
import view.Shapes.ShapeList;

import java.awt.*;

public class ShapeDrawer {
    private ShapeList shapeList;

    public ShapeDrawer(ShapeList shapeList) {
        this.shapeList = shapeList;
    }
    public void draw(Graphics2D g) {
        for (IShape shape : shapeList.getShapeList()) {
            g.setColor(shape.getPrimaryColor());
            g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        }
    }

}
