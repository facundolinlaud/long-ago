package com.facundolinlaud.supergame.ui.view.inventory;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Payload;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Source;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop.Target;
import com.facundolinlaud.supergame.ui.model.Item;
import com.facundolinlaud.supergame.utils.events.ItemDroppedEvent;
import com.facundolinlaud.supergame.utils.mediator.Mediator;
import com.facundolinlaud.supergame.utils.mediator.Messenger;

/**
 * Created by facundo on 3/30/16.
 */
public class DropAreaTarget extends Target implements Messenger {
    private Mediator uiMediator;

    public DropAreaTarget(Actor actor, Mediator uiMediator) {
        super(actor);

        this.uiMediator = uiMediator;
    }

    @Override
    public boolean drag(Source source, Payload payload, float x, float y, int pointer) {
        return true;
    }

    @Override
    public void drop(Source source, Payload payload, float x, float y, int pointer) {
        Item item = (Item) payload.getObject();

        uiMediator.raise(this, ItemDroppedEvent.class, new ItemDroppedEvent(item));
    }
}
