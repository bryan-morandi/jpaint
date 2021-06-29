package controller;

import model.interfaces.IApplicationState;
import view.interfaces.PaintCanvasBase;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class paintingMouseAdapter extends MouseAdapter {
    private PaintCanvasBase paintCanvas;
    private IApplicationState appState;
    private int pressedX, pressedY, releasedX, releasedY;
    public int rectX, rectY, rectW, rectH;

    public paintingMouseAdapter(PaintCanvasBase paintCanvas, IApplicationState appState){
        this. paintCanvas = paintCanvas;
        this.appState = appState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedX = e.getX();
        pressedY = e.getY();
        System.out.println("Pressed X: " + pressedX + ", Pressed Y: " + pressedY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        releasedX = e.getX();
        releasedY = e.getY();
        System.out.println("Released X: " +releasedX + ", Released Y: " + releasedY);

        if (pressedX < releasedX && pressedY < releasedY) {
            rectX = pressedX;
            rectY = pressedY;
            rectW = releasedX - rectX;
            rectH = releasedY - rectY;
        }
        else if (pressedX > releasedX && pressedY > releasedY) {
            rectX = releasedX;
            rectY = releasedY;
            rectW = pressedX - rectX;
            rectH = pressedY - rectY;
        }
        else if (pressedX > releasedX && pressedY < releasedY) {
            rectX = releasedX;
            rectY = pressedY;
            rectW = pressedX - rectX;
            rectH = releasedY - rectY;
        }
        else {
            rectX = pressedX;
            rectY = releasedY;
            rectW = releasedX - rectX;
            rectH = pressedY - rectY;
        }
        System.out.println("X: " + rectX + ", Y: " + rectY + ", W: " + rectW + ", H: " + rectH);
    }
}
