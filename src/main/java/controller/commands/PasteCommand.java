package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.gui.PaintCanvas;
import view.interfaces.IShape;

import java.util.ArrayList;

public class PasteCommand implements ICommand, IUndoable {
    private final PaintCanvas paintCanvas;
    private final ArrayList<IShape> pastedShapes = new ArrayList<>();
    private final ArrayList<IShape> masterList = MasterShapeList.masterShapeList.getShapeList();
    private final ArrayList<IShape> clipBoard = MasterShapeList.clipBoard.getShapeList();

   public PasteCommand(PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {
        for (IShape shape : clipBoard) {
            shape.pasteShape(pastedShapes);
        }
        masterList.addAll(pastedShapes);
        paintCanvas.repaint();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape shape : clipBoard) {
            shape.undoPaste(pastedShapes);
        }
        masterList.removeAll(pastedShapes);
       paintCanvas.repaint();
    }

    @Override
    public void redo() {
        masterList.addAll(pastedShapes);
        for (IShape shape : clipBoard) {
            shape.pasteShape(pastedShapes);
        }
        paintCanvas.repaint();
    }
}
