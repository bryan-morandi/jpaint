package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeFactory;

import java.awt.*;
import java.awt.Shape;

public class TriangleFactory implements IShapeFactory {
    private final IShape shape;

    public TriangleFactory(IShape Shape ) {
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
            xPoints = new int[] {(shape.getX() + (shape.getX() + shape.getWidth())) / 2, shape.getX(), shape.getX() + shape.getWidth() };
            yPoints = new int[] {shape.getY() , shape.getY() + shape.getHeight(), shape.getY() + shape.getHeight()  };
        }
        //equilateral triangle pointing south
        else if (shape.getPressedPoint().x > shape.getReleasedPoint().x && shape.getPressedPoint().y < shape.getReleasedPoint().y ) {
            xPoints = new int[] {(shape.getX() + (shape.getX() + shape.getWidth())) / 2, shape.getX(), shape.getX() + shape.getWidth() };
            yPoints = new int[] { shape.getY() + shape.getHeight(), shape.getY(), shape.getY()  };
        }
        //equilateral triangle pointing west
        else if (shape.getPressedPoint().x > shape.getReleasedPoint().x && shape.getPressedPoint().y > shape.getReleasedPoint().y ) {
            xPoints = new int[] {shape.getX(), shape.getX() + shape.getWidth(), shape.getX() + shape.getWidth()  };
            yPoints = new int[] {(shape.getY() + (shape.getY() + shape.getHeight())) / 2, shape.getY(), shape.getY() + shape.getHeight()  };
        }
        //equilateral triangle pointing east
        else {
            xPoints = new int[] {shape.getX() + shape.getWidth(), shape.getX(), shape.getX()};
            yPoints = new int[] {(shape.getY() + (shape.getY() + shape.getHeight())) / 2, shape.getY() + shape.getHeight(), shape.getY() };
        }
        triangle = new Polygon(xPoints, yPoints, nPoints);
        return triangle;
    }
}
