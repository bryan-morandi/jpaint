package view.Shapes;

import view.interfaces.IShadingStrategy;
import view.interfaces.IShape;

import java.awt.*;
import java.awt.Shape;

public class OutlineStrategy implements IShadingStrategy {
    private final IShape shape;
    private final java.awt.Shape shapeToBeDrawn;
    private final Graphics2D g2D;

    public OutlineStrategy(Shape ShapeToBeDrawn, IShape Shape, Graphics2D G2D) {
        this.shape = Shape;
        this.shapeToBeDrawn = ShapeToBeDrawn;
        this.g2D = G2D;
    }
    @Override
    public void drawWithSelectedShadingType() {
        g2D.setColor(shape.getPrimaryColor());
        g2D.setStroke(new BasicStroke(4));
        g2D.draw(shapeToBeDrawn);
    }
}
