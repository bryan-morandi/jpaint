package view.Shapes;

import view.interfaces.IBoundingBox;
import view.interfaces.IShape;

public class DetectCollision {
    private final IBoundingBox mouseBox;
    private final IShape shapeBox;

    public DetectCollision(IBoundingBox MouseBox, IShape ShapeBox) {
        this.mouseBox = MouseBox;
        this.shapeBox = ShapeBox;
    }

    public boolean run() {
        return (mouseBox.getX() < shapeBox.getX() + shapeBox.getWidth() &&
                mouseBox.getX() + mouseBox.getWidth() > shapeBox.getX() &&
                mouseBox.getY() < shapeBox.getY() + shapeBox.getHeight() &&
                mouseBox.getY() + mouseBox.getHeight() > shapeBox.getY());
    }
}
