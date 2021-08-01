package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.util.ArrayList;

public class DeleteCommand implements ICommand, IUndoable {
    PaintCanvas paintCanvas;
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
    private final ArrayList<IShape> deletedShapes = new ArrayList<>();

    public DeleteCommand(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    public void run() {
        for (IShape shape : masterList) {
            if (shape.getSelected()) {
                shape.setSelected(false);
                deletedShapes.add(shape);
            }
        }
        masterList.removeAll(deletedShapes);
        paintCanvas.repaint();
        CommandHistory.add(this);
    }

    public void undo() {
        masterList.addAll(deletedShapes);
        paintCanvas.repaint();

    }

    public void redo() {
        masterList.removeAll(deletedShapes);
        paintCanvas.repaint();
    }
}
