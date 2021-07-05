package view.Shapes;

import java.util.ArrayList;

public class ShapeList {
    public ArrayList<IShape> shapeList = new ArrayList<>();


    public void add(IShape shape) {
        if(!shapeList.contains(shape))
            shapeList.add(shape);
            System.out.println("adding shape");
    }

    public void remove(IShape shape){

        shapeList.remove(shape);
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
