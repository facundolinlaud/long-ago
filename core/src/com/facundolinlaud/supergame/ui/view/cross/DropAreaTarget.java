package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.events.Messages;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;

/**
 * Created by facundo on 3/30/16.
 */
public class DropAreaTarget extends Target {
    private MessageDispatcher messageDispatcher;

    public DropAreaTarget(Actor actor) {
        super(actor);
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        SlotSource slotSource = (SlotSource) source;
        Item item = (Item) payload.getObject();

        switch(slotSource.getSlotType()){
            case INVENTORY_SLOT:
                this.messageDispatcher.dispatchMessage(Messages.ITEM_FROM_INVENTORY_DROPPED, new ItemDroppedEvent(item));
                break;
            case EQUIPMENT_SLOT:
                this.messageDispatcher.dispatchMessage(Messages.ITEM_FROM_EQUIPMENT_DROPPED, new ItemDroppedEvent(item));
                break;
        }
    }
}
