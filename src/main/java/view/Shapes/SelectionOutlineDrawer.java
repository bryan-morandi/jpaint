package view.Shapes;

import view.interfaces.IShape;


public class SelectionOutlineDrawer {
    private IShape shape;

    public SelectionOutlineDrawer(IShape Shape) {
        this.shape = Shape;

    }

    public IShape selectShape() {
        switch (shape.getShapeType()) {
            case RECTANGLE:
                shape = new SelectedRectangleDecorator(shape);
                break;
            case ELLIPSE:
                shape = new SelectedEllipsesDecorator(shape);
                break;
            case TRIANGLE:
                shape = new SelectedTriangleDecorator(shape);
                break;
        }
        return shape;
    }
}
