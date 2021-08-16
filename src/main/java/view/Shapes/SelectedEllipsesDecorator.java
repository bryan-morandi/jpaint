package view.Shapes;

import controller.commands.Move;
import model.ShapeShadingType;
import model.ShapeType;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.geom.Ellipse2D;

public class SelectedEllipsesDecorator extends SelectedShapeDecorator {

    public SelectedEllipsesDecorator(IShape decoratedSelectedShape) {
        super(decoratedSelectedShape);
    }

    @Override
    public void draw(Graphics2D g) {
        //decoratedSelectedShape.draw(g);
        setEllipsesBorder(decoratedSelectedShape, g);
    }

    private void setEllipsesBorder(IShape decoratedSelectedShape, Graphics2D g) {
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        g.setStroke(stroke);
        g.setColor(Color.BLACK);
        Ellipse2D.Double ellipses;
        ellipses = new Ellipse2D.Double(decoratedSelectedShape.getX()-5,decoratedSelectedShape.getY()-5,decoratedSelectedShape.getWidth()+10, decoratedSelectedShape.getHeight()+10);
        g.draw(ellipses);
    }

    @Override
    public void setSelected(boolean selectedStatus) { }

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
    public int getPastedCount() { return decoratedSelectedShape.getPastedCount(); }

    @Override
    public int incrementPastedCount() { return decoratedSelectedShape.incrementPastedCount();}

    @Override
    public int decrementPastedCount() { return decoratedSelectedShape.decrementPastedCount();}

    @Override
    public void resetPastedCount() { decoratedSelectedShape.resetPastedCount(); }

    @Override
    public void setX(int newX) { }

    @Override
    public void setY(int newY) { }

    @Override
    public void selectShape(BoundingBox boundingBox) { }

    @Override
    public void copyShape() {}

    @Override
    public void moveShape(int deltaX, int deltaY) {
        Move move = new Move(deltaX, deltaY, this);
    }




}
