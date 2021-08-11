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
    private int X, Y, width, height, pastedCount;
    private boolean selected;

    public Shape (Point pressedPoint, Point releasedPoint, ShapeType shapeType, ShapeShadingType shadingType,
                  Color primaryColor, Color secondaryColor, boolean selected, int pastedCount) {
        this.pressedPoint = pressedPoint;
        this.releasedPoint = releasedPoint;
        this.shapeType = shapeType;
        this.shadingType = shadingType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.selected = selected;
        this.pastedCount = pastedCount;
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
        shapeDrawer.draw(this);
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
    public int incrementPastedCount() {
        return pastedCount++;
    }
    @Override
    public int decrementPastedCount() {
        return pastedCount--;
    }
    @Override
    public int getPastedCount() {
        return this.pastedCount;
    }
    @Override
    public void resetPastedCount() {
        pastedCount = 0;
    }
    @Override
    public IShape selectShape(BoundingBox boundingBox) {
        DetectCollision detectCollision = new DetectCollision(boundingBox, this);
        this.setSelected(detectCollision.run());
        SelectionOutlineDrawer outlinedShape = new SelectionOutlineDrawer(this);
        return outlinedShape.selectShape();
    }
    @Override
    public void copyShape() {
        this.resetPastedCount();
        MasterShapeList.clipBoard.add(this);
    }
}
