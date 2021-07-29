package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeFactory;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class RectangleFactory implements IShapeFactory {
    private final IShape shape;

    public RectangleFactory(IShape Shape ) {
       this.shape = Shape;
    }

    @Override
    public Shape createShape() {
        Rectangle2D.Double rectangle;
        rectangle = new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        return rectangle;
    }
}
