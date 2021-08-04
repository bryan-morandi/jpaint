package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;

public class Shape implements IShape {
    private final Point pressedPoint, releasedPoint;
    private final ShapeType shapeType;
    private final ShapeShadingType shadingType;
    private final Color primaryColor, secondaryColor;
    private int X, Y, width, height, pasted;
    private boolean selected;

    public Shape (Point pressedPoint, Point releasedPoint, ShapeType shapeType, ShapeShadingType shadingType,
                  Color primaryColor, Color secondaryColor, boolean selected, int pasted) {
        this.pressedPoint = pressedPoint;
        this.releasedPoint = releasedPoint;
        this.shapeType = shapeType;
        this.shadingType = shadingType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.selected = selected;
        this.pasted = pasted;
        getCoordinates();
    }

    public void getCoordinates() {
        this.X = Math.min(pressedPoint.x, releasedPoint.x);
        this.Y = Math.min(pressedPoint.y, releasedPoint.y);
        this.width = Math.max(pressedPoint.x, releasedPoint.x) - X;
        this.height = Math.max(pressedPoint.y, releasedPoint.y) - Y;
    }

    @Override
    public int getX() {
        return X;
    }

    @Override
    public int getY() {
        return Y;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    @Override
    public ShapeShadingType getShadingType() {
        return shadingType;
    }

    @Override
    public Color getPrimaryColor() {
        return primaryColor;
    }

    @Override
    public Color getSecondaryColor() {
        return secondaryColor;
    }

    @Override
    public Point getPressedPoint() {
        return pressedPoint;
    }

    @Override
    public Point getReleasedPoint() {
        return releasedPoint;
    }

    @Override
    public void draw(Graphics2D g2D) {
        ShapeDrawer shapeDrawer = new ShapeDrawer(g2D);
        shapeDrawer.draw();
    }
    @Override
    public void setSelected(boolean selectedStatus) {
        this.selected = selectedStatus;
    }
    @Override
    public boolean getSelected() {
        return this.selected;
    }
    @Override
    public void setX(int newX) { this.X = newX; }
    @Override
    public  void  setY(int newY) { this.Y = newY; }
    @Override
    public int incrementPasted() {
        return pasted++;
    }
    @Override
    public int decrementPasted() {
        return pasted--;
    }
    @Override
    public int getPasted() {
        return this.pasted;
    }
    @Override
    public void resetPasted() {
        pasted = 0;
    }
    @Override
    public void setPoints(IShape shape) {
        pressedPoint.setLocation(shape.getX(), shape.getY());
        releasedPoint.setLocation(shape.getX() + shape.getWidth(), shape.getY() + shape.getHeight());
    }

}
