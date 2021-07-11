package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IBoundingBox;
import view.interfaces.IShape;

import java.awt.*;

public class Shape implements IShape {
    private final Point pressedPoint, releasedPoint;
    private final ShapeType shapeType;
    private final ShapeShadingType shadingType;
    private final Color primaryColor, secondaryColor;
    private final int X, Y, width, height;
    private boolean selected;

    public Shape (ShapeBuilder builder) {
        this.pressedPoint = builder.getPressedPoint();
        this.releasedPoint = builder.getReleasedPoint();
        this.shapeType = builder.getShapeType();
        this.shadingType = builder.getShadingType();
        this.primaryColor = builder.getPrimaryColor();
        this.secondaryColor = builder.getSecondaryColorColor();
        this.X = builder.getX();
        this.Y = builder.getY();
        this.width = builder.getWidth();
        this.height = builder.getHeight();
        this.selected = false;
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
    public void add(){
        MasterShapeList.masterShapeList.add(this);
    }

    @Override
    public void remove() {
        MasterShapeList.masterShapeList.remove(this);
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
}
