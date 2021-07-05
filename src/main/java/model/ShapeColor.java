package model;

import java.awt.*;

public enum ShapeColor {
    BLACK (Color.BLACK),
    BLUE (Color.BLUE),
    CYAN (Color.CYAN) ,
    DARK_GRAY (Color.DARK_GRAY) ,
    GRAY (Color.GRAY),
    GREEN (Color.GREEN),
    LIGHT_GRAY (Color.LIGHT_GRAY),
    MAGENTA (Color.MAGENTA),
    ORANGE (Color.ORANGE),
    PINK (Color.PINK),
    RED (Color.RED),
    WHITE (Color.WHITE),
    YELLOW (Color.YELLOW);

    private Color color;

    ShapeColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
