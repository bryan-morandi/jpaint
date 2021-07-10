package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeStrategy;

import java.awt.*;
import java.awt.Shape;

public class TriangleStrategy implements IShapeStrategy {
    private final IShape shape;

    public TriangleStrategy (IShape Shape ) {
        this.shape = Shape;
    }

    @Override
    public Shape createShape() {
        Polygon triangle;
        int nPoints = 3;
        int[] xPoints;
        int[] yPoints;
        //equilateral triangle pointing north
        if (shape.getPressedPoint().x < shape.getReleasedPoint().x && shape.getPressedPoint().y < shape.getReleasedPoint().y ) {
            xPoints = new int[] {(shape.getPressedPoint().x + shape.getReleasedPoint().x) / 2, shape.getPressedPoint().x, shape.getReleasedPoint().x };
            yPoints = new int[] {shape.getPressedPoint().y , shape.getReleasedPoint().y, shape.getReleasedPoint().y  };
        }
        //equilateral triangle pointing south
        else if (shape.getPressedPoint().x > shape.getReleasedPoint().x && shape.getPressedPoint().y < shape.getReleasedPoint().y ) {
            xPoints = new int[] {(shape.getPressedPoint().x + shape.getReleasedPoint().x) / 2, shape.getPressedPoint().x, shape.getReleasedPoint().x };
            yPoints = new int[] { shape.getReleasedPoint().y, shape.getPressedPoint().y, shape.getPressedPoint().y  };
        }
        //equilateral triangle pointing east/west depending on pressed and released point
        else {
            xPoints = new int[] {shape.getPressedPoint().x, shape.getReleasedPoint().x, shape.getReleasedPoint().x  };
            yPoints = new int[] {(shape.getPressedPoint().y + shape.getReleasedPoint().y) / 2, shape.getPressedPoint().y, shape.getReleasedPoint().y  };
        }
        triangle = new Polygon(xPoints, yPoints, nPoints);
        return triangle;
    }
}
