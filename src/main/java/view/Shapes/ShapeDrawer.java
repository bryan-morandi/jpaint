package view.Shapes;

import view.interfaces.IShadingStrategy;
import view.interfaces.IShape;
import view.interfaces.IShapeStrategy;

import java.awt.*;
import java.awt.Shape;

public class ShapeDrawer {
    private final Graphics2D g2D;
    private Shape shapeToBeDrawn;

    public ShapeDrawer(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public void draw() {
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            switch (shape.getShapeType()) {
                case RECTANGLE:
                    IShapeStrategy shapeStrategy = new RectangleStrategy(shape);
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
                    IShadingStrategy currentState = new OutlineAndFilledInStrategy(shapeToBeDrawn, shape, g2D);
                    currentState.drawWithSelectedShadingType();
                    break;
                case OUTLINE:
                    currentState = new OutlineStrategy(shapeToBeDrawn, shape, g2D);
                    currentState.drawWithSelectedShadingType();
                    break;
                case FILLED_IN:
                    currentState = new FilledInStrategy(shapeToBeDrawn, shape, g2D);
                    currentState.drawWithSelectedShadingType();
                    break;
            }
        }
    }
}

