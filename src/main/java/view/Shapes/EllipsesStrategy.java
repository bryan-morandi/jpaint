package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeStrategy;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipsesStrategy implements IShapeStrategy {
    private final IShape shape;

    public EllipsesStrategy (IShape Shape ) {
        this.shape = Shape;
    }

    @Override
    public Shape createShape() {
        Ellipse2D.Double ellipses;
        ellipses = new Ellipse2D.Double(shape.getX(),shape.getY(),shape.getWidth(), shape.getHeight());
        return ellipses;
    }
}