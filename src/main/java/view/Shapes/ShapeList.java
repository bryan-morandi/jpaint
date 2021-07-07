package view.Shapes;

import view.interfaces.IShape;

import java.util.ArrayList;

public class ShapeList {
    private final ArrayList<IShape> shapeList = new ArrayList<>();


    public void add(IShape shape) {
        if(!shapeList.contains(shape))
            shapeList.add(shape);
            System.out.println("adding shape\n" + MasterShapeList.masterShapeList.size());
    }

    public void remove(IShape shape){

        shapeList.remove(shape);
        System.out.println("removing shape\n" + MasterShapeList.masterShapeList.size());
    }

    public IShape get(int index) {
        return shapeList.get(index);
    }

    public int size() {
        return shapeList.size();
    }

    public ArrayList<IShape> getShapeList() {
        return shapeList;
    }

    }

