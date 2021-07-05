package view.Shapes;

import model.ShapeType;

import java.awt.*;

public interface IShape {
    void draw(Graphics2D g);
    void add();
    void remove();
    int getX();
    int getY();
    int getWidth();
    int getHeight();
    Color getPrimaryColor();
    ShapeType getShapeType();
}
