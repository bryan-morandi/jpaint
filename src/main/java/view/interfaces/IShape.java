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
    int getPasted();
    int incrementPasted();
    int decrementPasted();
    void resetPasted();
    Point getReleasedPoint();
    Point getPressedPoint();
    Color getPrimaryColor();
    Color getSecondaryColor();
    ShapeType getShapeType();
    ShapeShadingType getShadingType();
    void setX(int newX);
    void setY(int newY);
    void setPoints(IShape shape);
}
