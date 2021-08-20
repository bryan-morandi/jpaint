package view.interfaces;

import model.ShapeShadingType;
import model.ShapeType;
import view.Shapes.BoundingBox;
import java.awt.*;
import java.util.ArrayList;

public interface IShape {
    void draw(Graphics2D g);

    void setSelected(boolean selectedStatus);

    boolean getSelected();

    int getX();

    int getY();

    int getWidth();

    int getHeight();

    int getPastedCount();

    int incrementPastedCount();

    int decrementPastedCount();

    void resetPastedCount();

    Point getReleasedPoint();

    Point getPressedPoint();

    Color getPrimaryColor();

    Color getSecondaryColor();

    ShapeType getShapeType();

    ShapeShadingType getShadingType();

    void setX(int newX);

    void setY(int newY);

    void selectShape(BoundingBox boundingBox);

    void copyShape();

    void moveShape(int deltaX, int deltaY);

    void undoMove(int deltaX, int deltaY);

    void pasteShape(ArrayList<IShape> pastedShapes);

    void undoPaste(ArrayList<IShape> pastedShapes);

}
