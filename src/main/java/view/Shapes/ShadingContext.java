package view.Shapes;

import view.interfaces.IShadingStrategy;

public class ShadingContext {
    private IShadingStrategy shadingStrategy;

    public void setShadingStrategy(IShadingStrategy shadingStrategy) {
        this.shadingStrategy = shadingStrategy;
    }

    public void executeShadingStrategy() {
        shadingStrategy.drawWithSelectedShadingType();
    }
}
