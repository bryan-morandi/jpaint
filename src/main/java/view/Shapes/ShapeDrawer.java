package view.Shapes;

import view.interfaces.IShadingStrategy;
import view.interfaces.IShape;
import view.interfaces.IShapeStrategy;

import java.awt.*;
import java.awt.Shape;

public class ShapeDrawer {
    private final Graphics2D g2D;
    private IShapeStrategy shapeStrategy;
    private IShadingStrategy shadingStrategy;
    private Shape shapeToBeDrawn;

    public ShapeDrawer(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public void draw() {
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            switch (shape.getShapeType()) {
                case RECTANGLE:
                    shapeStrategy = new RectangleStrategy(shape);
                    shapeToBeDrawn = shapeStrategy.createShape();
                    break;
                case ELLIPSE:
                    shapeStrategy = new EllipsesStrategy(shape);
                    shapeToBeDrawn = shapeStrategy.createShape();
                    break;
                case TRIANGLE:
                    shapeStrategy = new TriangleStrategy(shape);
                    shapeToBeDrawn = shapeStrategy.createShape();
                    break;
            }
            switch (shape.getShadingType()) {
                case OUTLINE_AND_FILLED_IN:
                    shadingStrategy = new OutlineAndFilledInStrategy(shapeToBeDrawn, shape, g2D);
                    shadingStrategy.drawWithSelectedShadingType();
                    break;
                case OUTLINE:
                    shadingStrategy = new OutlineStrategy(shapeToBeDrawn, shape, g2D);
                    shadingStrategy.drawWithSelectedShadingType();
                    break;
                case FILLED_IN:
                    shadingStrategy = new FilledInStrategy(shapeToBeDrawn, shape, g2D);
                    shadingStrategy.drawWithSelectedShadingType();
                    break;
            }
        }
    }
}

