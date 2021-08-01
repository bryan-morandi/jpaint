package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;

public class SelectedTriangleDecorator extends SelectedShapeDecorator {

    public SelectedTriangleDecorator(IShape decoratedSelectedShape, Graphics2D g2D) {
        super(decoratedSelectedShape, g2D);
    }

    @Override
    public void draw(Graphics2D g) {
        setTriangleBorder(decoratedSelectedShape);
        decoratedSelectedShape.draw(g2D);
    }

    private void setTriangleBorder(IShape decoratedSelectedShape) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g2D.setStroke(stroke);
        g2D.setColor(Color.BLACK);
        Polygon triangle;
        int nPoints = 3;
        int[] xPoints;
        int[] yPoints;
        //equilateral triangle pointing north
        if (decoratedSelectedShape.getPressedPoint().x < decoratedSelectedShape.getReleasedPoint().x && decoratedSelectedShape.getPressedPoint().y < decoratedSelectedShape.getReleasedPoint().y ) {
            xPoints = new int[] {
                    (decoratedSelectedShape.getX() + (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())) / 2,
                    decoratedSelectedShape.getX()-10,
                    (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())+10 };
            yPoints = new int[] {
                    decoratedSelectedShape.getY()-10,
                    (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())+5,
                    (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())+5 };
        }
        //equilateral triangle pointing south
        else if (decoratedSelectedShape.getPressedPoint().x > decoratedSelectedShape.getReleasedPoint().x && decoratedSelectedShape.getPressedPoint().y < decoratedSelectedShape.getReleasedPoint().y ) {
            xPoints = new int[] {
                    (decoratedSelectedShape.getX() + (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())) / 2,
                    decoratedSelectedShape.getX()-10,
                    (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())+10
            };
            yPoints = new int[] {
                    (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())+15,
                    decoratedSelectedShape.getY()-5,
                    decoratedSelectedShape.getY()-5
            };
        }
        //equilateral triangle pointing west
        else if (decoratedSelectedShape.getPressedPoint().x > decoratedSelectedShape.getReleasedPoint().x && decoratedSelectedShape.getPressedPoint().y > decoratedSelectedShape.getReleasedPoint().y ) {
            xPoints = new int[] {
                    decoratedSelectedShape.getX()-10,
                    (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())+5,
                    (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())+5
            };
            yPoints = new int[] {
                    (decoratedSelectedShape.getY() + (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())) / 2,
                    decoratedSelectedShape.getY()-10,
                    (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())+10
            };
        }
        //equilateral triangle pointing east
        else {
            xPoints = new int[] {
                    (decoratedSelectedShape.getX() + decoratedSelectedShape.getWidth())+10,
                    decoratedSelectedShape.getX()-5,
                    decoratedSelectedShape.getX()-5};
            yPoints = new int[] {
                    (decoratedSelectedShape.getY() + (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())) / 2,
                    (decoratedSelectedShape.getY() + decoratedSelectedShape.getHeight())+10,
                    decoratedSelectedShape.getY()-10
            };
        }
        triangle = new Polygon(xPoints, yPoints, nPoints);
        g2D.draw(triangle);
    }

    @Override
    public void setSelected(boolean selectedStatus) {

    }

    @Override
    public boolean getSelected() {
        return decoratedSelectedShape.getSelected();
    }

    @Override
    public int getX() {
        return decoratedSelectedShape.getX();
    }

    @Override
    public int getY() {
        return decoratedSelectedShape.getY();
    }

    @Override
    public int getWidth() {
        return decoratedSelectedShape.getWidth();
    }

    @Override
    public int getHeight() {
        return decoratedSelectedShape.getHeight();
    }

    @Override
    public Point getReleasedPoint() {
        return decoratedSelectedShape.getReleasedPoint();
    }

    @Override
    public Point getPressedPoint() {
        return decoratedSelectedShape.getPressedPoint();
    }

    @Override
    public Color getPrimaryColor() {
        return decoratedSelectedShape.getPrimaryColor();
    }

    @Override
    public Color getSecondaryColor() {
        return decoratedSelectedShape.getSecondaryColor();
    }

    @Override
    public ShapeType getShapeType() {
        return decoratedSelectedShape.getShapeType();
    }

    @Override
    public ShapeShadingType getShadingType() {
        return decoratedSelectedShape.getShadingType();
    }

    @Override
    public void setX(int newX) {

    }

    @Override
    public void setY(int newY) {

    }
}
