package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShapeFactory;

import java.awt.*;
import java.awt.Shape;

public class ShapeDrawer {
    private final Graphics2D g2D;
    private IShapeFactory shapeFactory;
    private ShadingContext shadingContext;
    private Shape shapeToBeDrawn;

    public ShapeDrawer(Graphics2D g2D) {
        this.g2D = g2D;
    }

    public void draw() {
        for (IShape shape : MasterShapeList.masterShapeList.getShapeList()) {
            switch (shape.getShapeType()) {
                case RECTANGLE:
                    shapeFactory = new RectangleFactory(shape);
                    shapeToBeDrawn = shapeFactory.createShape();
                    break;
                case ELLIPSE:
                    shapeFactory = new EllipsesFactory(shape);
                    shapeToBeDrawn = shapeFactory.createShape();
                    break;
                case TRIANGLE:
                    shapeFactory = new TriangleFactory(shape);
                    shapeToBeDrawn = shapeFactory.createShape();
                    break;
            }
            shadingContext = new ShadingContext();
            switch (shape.getShadingType()) {
                case OUTLINE_AND_FILLED_IN:
                    shadingContext.setShadingStrategy(new OutlineAndFilledInStrategy(shapeToBeDrawn, shape, g2D));
                    break;
                case OUTLINE:
                    shadingContext.setShadingStrategy(new OutlineStrategy(shapeToBeDrawn, shape, g2D));
                    break;
                case FILLED_IN:
                    shadingContext.setShadingStrategy(new FilledInStrategy(shapeToBeDrawn, shape, g2D));
                    break;
            }
            shadingContext.executeShadingStrategy();
        }
    }
}

