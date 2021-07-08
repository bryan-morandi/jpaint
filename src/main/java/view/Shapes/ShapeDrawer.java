package view.Shapes;

import view.interfaces.IShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class ShapeDrawer {
    private final Graphics2D g2D;

    public ShapeDrawer(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public void draw() {
        for ( IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            switch (shape.getShapeType()) {
                case RECTANGLE:
                    Rectangle2D.Double rectangle;
                    rectangle = new Rectangle2D.Double(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
                    g2D.setColor(shape.getPrimaryColor());
                    g2D.fill(rectangle);
                    break;
                case ELLIPSE:
                    Ellipse2D.Double ellipses;
                    ellipses = new Ellipse2D.Double(shape.getX(),shape.getY(),shape.getWidth(), shape.getHeight());
                    g2D.setColor(shape.getPrimaryColor());
                    g2D.fill(ellipses);
                    break;
                case TRIANGLE:
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
                    g2D.setColor(shape.getPrimaryColor());
                    g2D.fill(triangle);
                //System.out.println("Shape not yet implemented");
                    break;
            }
        }
    }

}
