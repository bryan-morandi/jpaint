package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeFactory;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class EllipsesFactory implements IShapeFactory {
    private final IShape shape;

    public EllipsesFactory(IShape Shape ) {
        this.shape = Shape;
    }

    @Override
    public Shape createShape() {
        Ellipse2D.Double ellipses;
        ellipses = new Ellipse2D.Double(shape.getX(),shape.getY(),shape.getWidth(), shape.getHeight());
        return ellipses;
    }
}