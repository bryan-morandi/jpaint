package view.Shapes;

import view.interfaces.IShape;

import java.awt.*;

public class BorderShapeSelector {
    private IShape shape;
    private final Graphics2D g2d;

    public BorderShapeSelector(IShape Shape, Graphics2D g2D) {
        this.shape = Shape;
        this.g2d = g2D;

    }

    public IShape selectShape() {
        switch (shape.getShapeType()) {
            case RECTANGLE:
                shape = new SelectedRectangleDecorator(shape, g2d);
                break;
            case ELLIPSE:
                shape = new SelectedEllipsesDecorator(shape, g2d);
                break;
            case TRIANGLE:
                shape = new SelectedTriangleDecorator(shape, g2d);
                break;
        }
        return shape;
    }
}
