package com.facundolinlaud.supergame.ui.view.overlay.dropzone;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SkillDragInformation;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SkillSlotSource;
import com.facundolinlaud.supergame.utils.events.Messages;

public class SkillDropZoneTarget extends DragAndDrop.Target {
    private MessageDispatcher messageDispatcher;

    public SkillDropZoneTarget(Actor actor) {
        super(actor);
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public boolean drag(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(DragAndDrop.Source source, DragAndDrop.Payload payload, float x, float y, int pointer) {
        SkillSlotSource slotSource = (SkillSlotSource) source;
        SkillDragInformation dragInformation = (SkillDragInformation) payload.getObject();

        switch(slotSource.getSlotType()){
            case SKILL_BAR_SLOT:
                this.messageDispatcher.dispatchMessage(Messages.SKILL_DROPPED, (Object) dragInformation.getIndex());
                break;
        }
    }
}
