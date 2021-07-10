package view.Shapes;

import model.ShapeShadingType;
import view.interfaces.IShadingState;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.Shape;

public class DrawShadingType {
    private final ShapeShadingType shadingType;
    private IShadingState currentState;
    private final IShape shape;
    private final Shape shapeToBeDrawn;
    private final Graphics2D g2D;

    public DrawShadingType(Shape ShapeToBeDrawn, IShape Shape, Graphics2D G2D) {
        this.shape = Shape;
        this.shapeToBeDrawn = ShapeToBeDrawn;
        this.g2D = G2D;
        shadingType = shape.getShadingType();
        updateState();
    }

    private void updateState() {
        switch (shadingType) {
            case OUTLINE_AND_FILLED_IN:
                currentState = new OutlineAndFilledInState(shapeToBeDrawn, shape, g2D);
                break;
            case OUTLINE:
                currentState = new OutlineState(shapeToBeDrawn, shape, g2D);
                break;
            case FILLED_IN:
                currentState = new FilledInState(shapeToBeDrawn, shape, g2D);
                break;
        }
    }
    public void draw() {
        currentState.drawWithSelectedShadingType();
    }
}
