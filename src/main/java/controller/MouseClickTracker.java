package controller;

import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import controller.commands.CreateAShapeCommand;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

//S.R: tracks mouse click and release coordinates

public class MouseClickTracker extends MouseAdapter {
    private final PaintCanvas paintCanvas;
    private final ApplicationState appState;
    Point pressedPoint, releasedPoint;


    public MouseClickTracker(PaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }


    @Override
    public void mousePressed(MouseEvent e) {
        pressedPoint = e.getPoint();
        System.out.println("Pressed X: " + pressedPoint.x + ", Pressed Y: " + pressedPoint.y);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedPoint = e.getPoint();
        System.out.println("Released X: " + releasedPoint.x + ", Released Y: " + releasedPoint.y);

        CreateAShapeCommand newShape = new CreateAShapeCommand(pressedPoint, releasedPoint, paintCanvas, appState);
        newShape.run();
    }
}
