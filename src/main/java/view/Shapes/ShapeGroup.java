package view.Shapes;

import controller.commands.Move;
import controller.commands.Paste;
import model.ShapeShadingType;
import model.ShapeType;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShapeGroup implements IShape{
    private final ArrayList<IShape> children = new ArrayList<IShape>();
    private  Point pressedPoint, releasedPoint;
    private int X, Y, width, height, pastedCount;
    private boolean selected;


    public ShapeGroup(ArrayList<IShape> ShapeList) {

        for (IShape shape : ShapeList) {
            addChild(shape);
        }
        
        this.X = getX();
        this.Y = getY();
        this.width = getWidth();
        this.height = getHeight();
        this.selected = getSelected();
        this.pressedPoint = getPressedPoint();
        this.releasedPoint = getReleasedPoint();

    }

    public void addChild(IShape component) {
        if (!children.contains(component)){
            children.add(component);
        }
    }

    public void removeChild(IShape component) {
        children.remove(component);
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

    public ArrayList<IShape> getChildren() {
        return children;
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
    public void selectShape(BoundingBox boundingBox) {
//        for (IShape child : children) {
//            child.selectShape(boundingBox);
//        }
        DetectCollision detectCollision = new DetectCollision(boundingBox, this);
        this.setSelected(detectCollision.run());
    }

    @Override
    public void copyShape() {
//        for (IShape child : children) {
//            child.resetPastedCount();
//            child.copyShape();
//        }
        this.resetPastedCount();
        MasterShapeList.clipBoard.add(this);
    }

    @Override
    public void moveShape(int deltaX, int deltaY) {
        for (IShape child : children) {
            child.moveShape(deltaX, deltaY);
        }
    }
    @Override
    public void undoMove(int deltaX, int deltaY) {
        for (IShape child : children) {
            child.undoMove(deltaX, deltaY);
        }
    }

    @Override
    public void pasteShape(ArrayList<IShape> pastedShapes) {
        for (IShape child : children) {
            child.pasteShape(pastedShapes);
        }
        ArrayList<IShape> clonedList = (ArrayList<IShape>) pastedShapes.clone();
        ShapeGroup g = new ShapeGroup(clonedList);
        pastedShapes.clear();
        pastedShapes.add(g);
        MasterShapeList.masterShapeList.getShapeList().add(g);
        MasterShapeList.groupList.add(g);
        //MasterShapeList.masterShapeList.getShapeList().removeAll(pastedShapes);
    }

    @Override
    public void undoPaste(ArrayList<IShape> pastedShapes) {
        for (IShape child : children) {
            new Paste(pastedShapes, child).undo();
        }
        ArrayList<IShape> clonedList = (ArrayList<IShape>) pastedShapes.clone();
        ShapeGroup g = new ShapeGroup(clonedList);
        MasterShapeList.masterShapeList.getShapeList().remove(g);
    }
}

