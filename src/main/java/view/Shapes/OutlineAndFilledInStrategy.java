package view.Shapes;

import view.interfaces.IShape;
import view.interfaces.IShadingStrategy;

import java.awt.*;
import java.awt.Shape;

public class OutlineAndFilledInStrategy implements IShadingStrategy {
    private final IShape shape;
    private final java.awt.Shape shapeToBeDrawn;
    private final Graphics2D g2D;

    public OutlineAndFilledInStrategy(Shape ShapeToBeDrawn, IShape Shape, Graphics2D G2D) {
        this.shape = Shape;
        this.shapeToBeDrawn = ShapeToBeDrawn;
        this.g2D = G2D;
    }

    @Override
    public void drawWithSelectedShadingType() {
        g2D.setColor(shape.getPrimaryColor());
        g2D.fill(shapeToBeDrawn);
        g2D.setStroke(new BasicStroke(4));
        g2D.setColor(shape.getSecondaryColor());
        g2D.draw(shapeToBeDrawn);

    }
}
