package controller.commands;

import controller.commands.interfaces.ICommand;
import controller.commands.interfaces.IUndoable;
import view.Shapes.MasterShapeList;
import view.Shapes.ShapeGroup;
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
//            Shape copiedShape = new Shape(shape.getPressedPoint(), shape.getReleasedPoint(), shape.getShapeType(), shape.getShadingType(), shape.getPrimaryColor(), shape.getSecondaryColor(),false, shape.incrementPastedCount());
//            copiedShape.setX(shape.getX() + 50 * shape.getPastedCount());
//            copiedShape.setY(shape.getY() + 50 * shape.getPastedCount());
//            masterList.add(copiedShape);
//            pastedShapes.add(copiedShape);
        }
        masterList.addAll(pastedShapes);
        paintCanvas.repaint();
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
       //masterList.removeAll(pastedShapes);
        for (IShape shape : clipBoard) {
            shape.undoPaste(pastedShapes);
//            if (shape.getPastedCount() > 0) {
//                shape.decrementPastedCount();
//            }
        }
        masterList.removeAll(pastedShapes);
       paintCanvas.repaint();
    }

    @Override
    public void redo() {
        masterList.addAll(pastedShapes);
        for (IShape shape : clipBoard) {
            shape.pasteShape(pastedShapes);
//            if (shape.getPastedCount() > 0) {
//                shape.incrementPastedCount();
//            }

        }
        paintCanvas.repaint();
    }
}
