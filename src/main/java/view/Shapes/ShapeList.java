package view.Shapes;

import view.interfaces.IShape;

import java.util.ArrayList;

public class ShapeList {
    private final ArrayList<IShape> shapeList = new ArrayList<>();


    public void add(IShape shape) {
        if(!shapeList.contains(shape))
            shapeList.add(shape);
    }

    public void remove(IShape shape){

        shapeList.remove(shape);
    }

    public int size() {
        return shapeList.size();
    }

    public ArrayList<IShape> getShapeList() {
        return shapeList;
    }

    }

