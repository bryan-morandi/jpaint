package view.Shapes;

import view.interfaces.IShape;

import java.awt.*;


public abstract class SelectedShapeDecorator implements IShape {
    protected IShape decoratedSelectedShape;

    public SelectedShapeDecorator(IShape decoratedSelectedShape) {
        this.decoratedSelectedShape = decoratedSelectedShape;
    }

    public void draw(Graphics2D g) { decoratedSelectedShape.draw(g); }


}
