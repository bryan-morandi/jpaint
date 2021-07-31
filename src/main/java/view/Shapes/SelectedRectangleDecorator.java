package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class SelectedRectangleDecorator extends SelectedShapeDecorator {

    public SelectedRectangleDecorator(IShape decoratedSelectedShape, Graphics2D g2D) {
        super(decoratedSelectedShape, g2D);
    }

    @Override
    public void draw(Graphics2D g) {
        setRectangleBorder(decoratedSelectedShape);
        decoratedSelectedShape.draw(g2D);
    }

    private void setRectangleBorder(IShape decoratedSelectedShape) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g2D.setStroke(stroke);
        g2D.setColor(Color.BLACK);
        Rectangle2D.Double rectangle;
        rectangle = new Rectangle2D.Double(decoratedSelectedShape.getX()-5, decoratedSelectedShape.getY()-5, decoratedSelectedShape.getWidth()+10, decoratedSelectedShape.getHeight()+10);
        g2D.draw(rectangle);
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
