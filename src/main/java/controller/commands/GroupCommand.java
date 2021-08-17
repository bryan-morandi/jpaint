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
                //shape.setSelected(false);
                tempList.add(shape);
            }
        }
        if (selectedCount > 1) {
            //groupList.clear();
            masterList.removeAll(tempList);
            group = new ShapeGroup(tempList);
            group.setSelected(true);
            groupList.add(group);
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
        for (IShape child : group.getChildren()) {
            masterList.add(child);
        }
        masterList.remove(group);
        groupList.remove(group);
        paintCanvas.repaint();
    }

    @Override
    public void redo() {
        masterList.removeAll(tempList);
        masterList.add(group);
        groupList.add(group);
        paintCanvas.repaint();
    }
}
