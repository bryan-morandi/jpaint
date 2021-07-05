package main;

import controller.IJPaintController;
import controller.JPaintController;
import controller.MouseClickTracker;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args){
        PaintCanvas paintCanvas = new PaintCanvas();

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        MouseClickTracker mouseAdapter = new MouseClickTracker(paintCanvas, appState);
        paintCanvas.addMouseListener(mouseAdapter);







        // For example purposes only; remove all lines below from your final project.

//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // Filled in rectangle
//        while (appState.getActiveMouseMode() == MouseMode.DRAW) {
//            Graphics2D graphics2d = paintCanvas.getGraphics2D();
//            graphics2d.setColor(Color.BLUE);
//            graphics2d.fillRect(
//                    mouseAdapter.x,
//                    mouseAdapter.y,
//                    mouseAdapter.w,
//                    mouseAdapter.h
//            );
//        }

//        // Outlined rectangle
//        graphics2d.setStroke(new BasicStroke(5));
//        graphics2d.setColor(Color.BLUE);
//        graphics2d.drawRect(12, 13, 200, 400);
//
//        // Selected Shape
//        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
//        graphics2d.setStroke(stroke);
//        graphics2d.setColor(Color.BLACK);
//        graphics2d.drawRect(7, 8, 210, 410);

        // end of example
    }
}
