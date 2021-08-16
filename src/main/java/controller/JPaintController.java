package controller;

import controller.commands.*;
import model.interfaces.IApplicationState;
import view.EventName;
import view.gui.PaintCanvas;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;
    private final PaintCanvas paintCanvas;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState, PaintCanvas paintCanvas) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, applicationState::setActiveShape);
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, applicationState::setActivePrimaryColor);
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, applicationState::setActiveSecondaryColor);
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, applicationState::setActiveShadingType);
        uiModule.addEvent(EventName.CHOOSE_MOUSE_MODE, applicationState::setActiveStartAndEndPointMode);
        uiModule.addEvent(EventName.UNDO, () -> new UndoCommand().run());
        uiModule.addEvent(EventName.REDO, () -> new RedoCommand().run());
        uiModule.addEvent(EventName.COPY, () -> new CopyCommand().run());
        uiModule.addEvent(EventName.PASTE, () -> new PasteCommand(paintCanvas).run());
        uiModule.addEvent(EventName.DELETE, () -> new DeleteCommand(paintCanvas).run());
        uiModule.addEvent(EventName.GROUP, () -> new  GroupCommand(paintCanvas).run());
        uiModule.addEvent(EventName.UNGROUP, () -> new UngroupCommand(paintCanvas).run());
    }
}
