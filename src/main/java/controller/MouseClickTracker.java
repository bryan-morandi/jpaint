package controller;

import controller.commands.MoveCommand;
import controller.commands.SelectCommand;
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

        switch (appState.getActiveMouseMode()) {
            case DRAW:
                CreateAShapeCommand newShape = new CreateAShapeCommand(pressedPoint, releasedPoint, paintCanvas, appState);
                newShape.run();
                break;
            case SELECT:
                SelectCommand selectShape = new SelectCommand(pressedPoint, releasedPoint, paintCanvas);
                selectShape.run();
                break;
            case MOVE:
                MoveCommand moveShape = new MoveCommand(pressedPoint, releasedPoint, paintCanvas);
                moveShape.run();
                break;
        }
    }
}
