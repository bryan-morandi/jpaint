package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

//builder method to build shapes

public class ShapeBuilder implements IShape {
    private Point pressedPoint, releasedPoint;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;
    private Color primaryColor;
    private int X, Y, width, height;



public ShapeBuilder(Point pressedPoint, Point releasedPoint) {
    this.pressedPoint = pressedPoint;
    this.releasedPoint = releasedPoint;

    getCoordinates();

}

    public void getCoordinates() {
        this.X = Math.min(pressedPoint.x, releasedPoint.x);
        this.Y = Math.min(pressedPoint.y, releasedPoint.y);
        this.width = Math.max(pressedPoint.x, releasedPoint.x) - X;
        this.height = Math.max(pressedPoint.y, releasedPoint.y) - Y;
    }
    @Override
    public ShapeType getShapeType() {
        return shapeType;
    }

    public ShapeBuilder setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
        return this;
    }

    public ShapeShadingType getShadingType() {
        return shadingType;
    }

    public ShapeBuilder setShadingType(ShapeShadingType shadingType) {
        this.shadingType = shadingType;
        return this;
    }

    public Point getPressedPoint() {
        return pressedPoint;
    }
    @Override
    public Color getPrimaryColor() {
        return primaryColor;
    }

    public ShapeBuilder setPrimaryColor(Color primaryColor){
        this.primaryColor = primaryColor;
        return this;
    }

    public ShapeBuilder setPressedPoint(Point pressedPoint) {
        this.pressedPoint = pressedPoint;
        return this;
    }

    public Point getReleasedPoint() {
        return releasedPoint;
    }

    public ShapeBuilder setReleasedPoint(Point releasedPoint) {
        this.releasedPoint = releasedPoint;
        return this;
    }
    @Override
    public int getX() {
        return X;
    }

    public ShapeBuilder setX(int X) {
        this.X = X;
        return this;
    }
    @Override
    public int getY() {
        return Y;
    }

    public ShapeBuilder setY(int Y) {
        this.Y = Y;
        return this;
    }
    @Override
    public int getWidth() {
        return width;
    }

    public ShapeBuilder setWidth(int width) {
        this.width = width;
        return this;
    }
    @Override
    public int getHeight() {
        return height;
    }

    public int setHeight(int height) {
    this.height = height;
    return height;
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
    public void draw(Graphics2D g) {
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            g.setColor(shape.getPrimaryColor());
            g.fillRect(shape.getX(), shape.getY(), shape.getWidth(), shape.getHeight());
        }
    }

}


