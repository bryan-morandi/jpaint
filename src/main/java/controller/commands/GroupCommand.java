package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.ShapeGroup;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.util.ArrayList;

public class GroupCommand implements ICommand, IUndoable {
    private final PaintCanvas paintCanvas;
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
    private final ArrayList<IShape> groupList = MasterShapeList.groupList.getShapeList();
    private ShapeGroup group;
    private final ArrayList<IShape> tempList = new ArrayList<>();

    public GroupCommand(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {
        int selectedCount = 0;
        for (IShape shape : masterList) {
            if (shape.getSelected()) {
                selectedCount++;
                tempList.add(shape);
            }
        }
        if (selectedCount > 1) {
            masterList.removeAll(tempList);
            group = new ShapeGroup(tempList);
            group.setSelected(true);
            groupList.add(group);
            for (IShape shape: tempList) {
                if (shape instanceof ShapeGroup) {
                    groupList.remove(shape);
                }
            }
            masterList.add(group);
            System.out.println("Master list size: " + masterList.size());
        } else
            { return; }
        paintCanvas.repaint();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        //group.unGroup();
        masterList.addAll(group.getChildren());
        masterList.remove(group);
        groupList.remove(group);
        for (IShape shape: tempList) {
            if (shape instanceof ShapeGroup) {
                groupList.add(shape);
            }
        }
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        masterList.removeAll(tempList);
        masterList.add(group);
        groupList.add(group);
        for (IShape shape: tempList) {
            if (shape instanceof ShapeGroup) {
                groupList.remove(shape);
            }
        }
        paintCanvas.repaint();
    }
}
