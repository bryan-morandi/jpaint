package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

//builder method to build shapes

public class ShapeBuilder {
    private Point pressedPoint, releasedPoint;
    private ShapeType shapeType;
    private ShapeShadingType shadingType;
    private Color primaryColor, secondaryColor;
    private int X, Y, width, height;


    public ShapeBuilder(Point pressedPoint, Point releasedPoint) {
    this.pressedPoint = pressedPoint;
    this.releasedPoint = releasedPoint;
    getCoordinates();
    }

    //getters
    public int getX() { return X;}

    public int getY() { return Y;}

    public int getHeight() { return height;}

    public int getWidth() { return width;}

    public ShapeShadingType getShadingType() { return shadingType; }

    public ShapeType getShapeType() { return shapeType; }

    public Point getPressedPoint() { return pressedPoint; }

    public Point getReleasedPoint() { return releasedPoint; }

    public  Color getPrimaryColor() { return primaryColor; }

    public  Color getSecondaryColorColor() { return secondaryColor; }


    //setters
    public void getCoordinates() {
        this.X = Math.min(pressedPoint.x, releasedPoint.x);
        this.Y = Math.min(pressedPoint.y, releasedPoint.y);
        this.width = Math.max(pressedPoint.x, releasedPoint.x) - X;
        this.height = Math.max(pressedPoint.y, releasedPoint.y) - Y;
    }

    public ShapeBuilder shapeType(ShapeType shapetype) {
        this.shapeType = shapetype;
        return this;
    }

    public ShapeBuilder shadingType(ShapeShadingType shapeShadingType) {
        this.shadingType = shapeShadingType;
        return this;
    }

    public ShapeBuilder pressedPoint(Point pPoint) {
        this.pressedPoint = pPoint;
        return this;
    }

    public ShapeBuilder releasedPoint(Point rPoint) {
        this.releasedPoint = rPoint;
        return this;
    }

    public ShapeBuilder primaryColor(Color pColor) {
        this.primaryColor = pColor;
        return this;
    }

    public ShapeBuilder secondaryColor(Color sColor) {
        this.secondaryColor = sColor;
        return this;
    }

    //builder method
    public Shape buildShape() {
        return new Shape (this);
    }

}



