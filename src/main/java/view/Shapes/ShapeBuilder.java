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
    private boolean selected;

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

    public ShapeBuilder selectedStatus(boolean status) {
        this.selected = status;
        return this;
    }

    //builder method
    public Shape buildShape() {
        return new Shape (pressedPoint, releasedPoint, shapeType, shadingType, primaryColor,
                secondaryColor, selected, 0);
    }

}



