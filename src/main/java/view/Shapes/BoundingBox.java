package view.Shapes;

import view.interfaces.IBoundingBox;

import java.awt.*;

public class BoundingBox implements IBoundingBox {
    private final Point pressedPoint;
    private final Point releasedPoint;
    private int X, Y, width, height;

    public BoundingBox(Point PressedPoint, Point ReleasedPoint) {
        this.pressedPoint = PressedPoint;
        this.releasedPoint = ReleasedPoint;
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
        return this.X;
    }
    @Override
    public int getY() {
        return this.Y;
    }
    @Override
    public int getHeight() {
        return this.height;
    }
    @Override
    public int getWidth() {
        return this.width;
    }
}
