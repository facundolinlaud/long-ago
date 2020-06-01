package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.ai.msg.MessageDispatcher;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.IdComponent;
import com.facundolinlaud.supergame.components.InteractionComponent;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.StatusComponent;
import com.facundolinlaud.supergame.components.player.KeyboardComponent;
import com.facundolinlaud.supergame.managers.world.PlayerInputManager;
import com.facundolinlaud.supergame.managers.world.WorldEntitiesManager;
import com.facundolinlaud.supergame.model.status.Action;
import com.facundolinlaud.supergame.model.status.Direction;
import com.facundolinlaud.supergame.ui.controller.DialogUIController;
import com.facundolinlaud.supergame.utils.Mappers;
import com.facundolinlaud.supergame.utils.PositionUtils;
import com.facundolinlaud.supergame.utils.events.Messages;

public class InteractionSystem extends EntitySystem {
    private final static float MINIMUM_DISTANCE_FOR_CONVERSATION = 1.8f;

    private ComponentMapper<IdComponent> idm = Mappers.id;
    private ComponentMapper<StatusComponent> sm = Mappers.status;
    private ComponentMapper<PositionComponent> pm = Mappers.position;

    private ImmutableArray<Entity> interactables;
    private DialogUIController dialogUIController;
    private PlayerInputManager playerInputManager;
    private WorldEntitiesManager worldEntitiesManager;
    private MessageDispatcher messageDispatcher;

    public InteractionSystem(DialogUIController dialogUIController, PlayerInputManager playerInputManager,
                             WorldEntitiesManager worldEntitiesManager) {
        this.dialogUIController = dialogUIController;
        this.playerInputManager = playerInputManager;
        this.worldEntitiesManager = worldEntitiesManager;
        this.messageDispatcher = MessageManager.getInstance();
    }

    @Override
    public void addedToEngine(Engine engine) {
        interactables = engine.getEntitiesFor(Family.all(
                IdComponent.class,
                InteractionComponent.class,
                PositionComponent.class).exclude(KeyboardComponent.class).get());
    }

    @Override
    public void update(float deltaTime){
        Entity player = worldEntitiesManager.getPlayer();

        if(cantInteract(player))
            return;

        Vector2 playerPosition = pm.get(player).getPosition();
        Entity closestInteractable = getClosestInteractable(playerPosition);

        if(closestInteractable == null)
            return;

        lookAt(closestInteractable, playerPosition);
        dispatchPlayerInteractionMessage(closestInteractable);
    }

    private boolean cantInteract(Entity player) {
        StatusComponent statusComponent = sm.get(player);
        return !playerInputManager.isInteractionRequested() || dialogUIController.isBusy()
                || !statusComponent.getAction().equals(Action.STANDING);
    }

    private Entity getClosestInteractable(Vector2 playerPosition) {
        Entity bestInteractable = interactables.first();
        Vector2 bestPosition = pm.get(bestInteractable).getPosition();

        for(Entity interactable : interactables){
            Vector2 position = pm.get(interactable).getPosition();

            if(position.dst(playerPosition) < bestPosition.dst(playerPosition)){
                bestInteractable = interactable;
                bestPosition = position;
            }
        }

        if(bestPosition.dst(playerPosition) > MINIMUM_DISTANCE_FOR_CONVERSATION)
            return null;

        return bestInteractable;
    }

    private void lookAt(Entity closestInteractable, Vector2 position){
        Vector2 closesInteractablePosition = pm.get(closestInteractable).getPosition();
        Direction newAgentDirection = PositionUtils.getFacingDirection(closesInteractablePosition, position);

        StatusComponent statusComponent = sm.get(closestInteractable);
        statusComponent.setDirection(newAgentDirection);
    }

    private void dispatchPlayerInteractionMessage(Entity closestInteractable) {
        String id = idm.get(closestInteractable).getId();
        messageDispatcher.dispatchMessage(Messages.PLAYER_INTERACTION, id);
    }
}
