package view.Shapes;

import view.interfaces.IShape;

import java.awt.*;

public abstract class SelectedShapeDecorator implements IShape {
    protected IShape decoratedSelectedShape;
    protected final Graphics2D g2D;

    public SelectedShapeDecorator(IShape decoratedSelectedShape, Graphics2D g2D) {
        this.decoratedSelectedShape = decoratedSelectedShape;
        this.g2D = g2D;
    }

    public void draw(){
        decoratedSelectedShape.draw(g2D);
    }
}
