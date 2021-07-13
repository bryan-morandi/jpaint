package view.interfaces;

import model.ShapeShadingType;
import model.ShapeType;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D g);
    void setSelected(boolean selectedStatus);
    boolean getSelected();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    Point getReleasedPoint();
    Point getPressedPoint();
    Color getPrimaryColor();
    Color getSecondaryColor();
    ShapeType getShapeType();
    ShapeShadingType getShadingType();
    void setX(int newX);
    void setY(int newY);
    void setHeight(int newH);
    void setWidth(int newW);
}
