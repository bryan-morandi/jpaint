package view.Shapes;

import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements IShape {
    private List<IShape> children = new ArrayList<>();
    private  Point pressedPoint, releasedPoint;
    private int X, Y, width, height, pastedCount;
    private boolean selected;


    public ShapeGroup(List<IShape> ShapeList) {
        for (IShape shape: ShapeList) {
            if (shape.getSelected()) {
                addChild(shape);
            }
        }
        this.X = getX();
        this.Y = getY();
        this.width = getWidth();
        this.height = getHeight();
        this.selected = getSelected();
        this.pressedPoint = getPressedPoint();
        this.releasedPoint = getReleasedPoint();
    }

    public ShapeGroup(ShapeGroup group) {
        this.children = group.children;
        this.pressedPoint = group.pressedPoint;
        this.releasedPoint = group.releasedPoint;
        this.X = group.X;
        this.Y = group.Y;
        this.width = group.width;
        this.height = group.height;
        this.pastedCount = group.pastedCount;
        this.selected = group.selected;
    }


    public void addChild(IShape component) {
        children.add(component);
    }

    public IShape removeChild() {
        return children.remove(children.size()-1);
    }

    public void clear() {
        children.clear();
    }

    public void unGroup() {
        for (IShape child : children) {
            MasterShapeList.masterShapeList.add(child);
        }
        MasterShapeList.masterShapeList.remove(this);
    }

    @Override
    public void draw(Graphics2D g) {
        for (IShape child : children){
            child.draw(g);
            //child.setSelected(false);
        }
    }

    @Override
    public void setSelected(boolean selectedStatus) {
        for (IShape child : children) {
            child.setSelected(selectedStatus);
        }
        selected = selectedStatus;
    }

    @Override
    public boolean getSelected() {
        for (IShape child : children) {
            child.getSelected();
        }
        return this.selected;
    }

    @Override
    public int getX() {
        int X = children.get(0).getX();
        for (IShape child: children) {
            if (child.getX() < X) {
                X = child.getX();
            }
        }
        return X;
    }

    @Override
    public int getY() {
        int Y = children.get(0).getY();
        for (IShape child: children) {
            if (child.getY() < Y) {
                Y = child.getY();
            }
        }
        return Y;
    }

    @Override
    public int getWidth() {
        int maxWidth = 0;
        int x = getX();
        for (IShape child : children) {
            int childX = child.getX() - x;
            int childW = childX + child.getWidth();
            if (childW > maxWidth) {
                maxWidth = childW;
            }
        }
        return maxWidth;
    }

    @Override
    public int getHeight() {
        int maxHeight = 0;
        int y = getY();
        for (IShape child : children) {
            int childY = child.getY() - y;
            int childH = childY + child.getHeight();
            if (childH > maxHeight) {
                maxHeight = childH;
            }
        }
        return maxHeight;
    }

    @Override
    public int getPastedCount() {
        for (IShape child : children) {
            child.getPastedCount();
        }
        return pastedCount;
    }

    @Override
    public int incrementPastedCount() {
        for (IShape child : children) {
            child.incrementPastedCount();
        }
        return pastedCount++;
    }

    @Override
    public int decrementPastedCount() {
        for (IShape child : children) {
            child.decrementPastedCount();
        }
        return pastedCount--;
    }

    @Override
    public void resetPastedCount() {
        for (IShape child : children) {
            child.resetPastedCount();
        }
        pastedCount = 0; }

    @Override
    public Point getReleasedPoint() {
        for (IShape child : children) {
            child.getReleasedPoint();
        }
        return new Point(X + width, Y + height);
    }

    @Override
    public Point getPressedPoint() {
        for (IShape child : children) {
            child.getPressedPoint();
        }
        return new Point(X, Y);
    }

    @Override
    public Color getPrimaryColor() {
        for (IShape child : children) {
            child.getPrimaryColor();
        }
        return Color.darkGray;
    }

    @Override
    public Color getSecondaryColor() {
        for (IShape child : children) {
            child.getSecondaryColor();
        }
        return null;
    }

    @Override
    public ShapeType getShapeType() {
        for (IShape child : children) {
            child.getShapeType();
        }
        return ShapeType.RECTANGLE;
    }

    @Override
    public ShapeShadingType getShadingType() {
        for (IShape child : children) {
            child.getShadingType();
        }
        return ShapeShadingType.OUTLINE;
    }

    @Override
    public void setX(int newX) {
        for (IShape child : children) {
            child.setX(newX);
        }
        this.X = newX;
    }

    @Override
    public void setY(int newY) {
        for (IShape child : children) {
            child.setY(newY);
        }
        this.Y = newY;
    }

    @Override
    public IShape selectShape(BoundingBox boundingBox) {
        for (IShape child : children) {
            child.selectShape(boundingBox);
        }
        DetectCollision detectCollision = new DetectCollision(boundingBox, this);
        this.setSelected(detectCollision.run());
        SelectionOutlineDrawer outlinedShape =  new SelectionOutlineDrawer(this);
        if (this.getSelected()) {
            return outlinedShape.selectShape();
        }
        return outlinedShape.getOriginalShape();
    }

    public void copyShape() {
        this.resetPastedCount();
        MasterShapeList.clipBoard.add(this);
    }

}
