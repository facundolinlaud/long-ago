package com.facundolinlaud.supergame.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Vector2;
import com.facundolinlaud.supergame.components.PositionComponent;
import com.facundolinlaud.supergame.components.RenderComponent;
import com.facundolinlaud.supergame.utils.Dimensions;
import com.facundolinlaud.supergame.utils.Mappers;

import java.util.Comparator;
import java.util.List;

/**
 * Created by facundo on 3/18/16.
 */
public class RenderSystem extends SortedIteratingSystem {
    private ComponentMapper<PositionComponent> pm = Mappers.position;
    private ComponentMapper<RenderComponent> rm = Mappers.render;

    private SpriteBatch spriteBatch;

    public RenderSystem(SpriteBatch spriteBatch) {
        super(Family.all(PositionComponent.class, RenderComponent.class).get(), new ZComparator());
        this.spriteBatch = spriteBatch;
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        super.forceSort();

        PositionComponent positionComponent = pm.get(entity);
        RenderComponent renderComponent = rm.get(entity);

        List<TextureRegion> regions = renderComponent.getRegions();

        if(regions.isEmpty())
            return;

        for(TextureRegion region : regions){
            float width = Dimensions.toMeters(region.getRegionWidth());
            float height = Dimensions.toMeters(region.getRegionHeight());

            Vector2 pos = renderComponent.getRenderPositionStrategy().process(positionComponent.x, positionComponent.y);

            float x = pos.x - width / 2;
            float y = pos.y - height / 2;

            spriteBatch.draw(region, x, y,
                    width / 2, height / 2,
                    width, height,
                    1f, 1f, renderComponent.getRotation());
        }
    }

    /** This ZComparator can be really expensive if there are too many render entities **/
    private static class ZComparator implements Comparator<Entity> {
        private static final int DIFF_MULTIPLIER = 10;

        private ComponentMapper<PositionComponent> pm = Mappers.position;
        private ComponentMapper<RenderComponent> rm = Mappers.render;

        @Override
        public int compare(Entity e1, Entity e2) {
            int priorityDiff = getPriorityDifference(e1, e2);

            if(priorityDiff == 0)
                return getVerticalPositionDifferente(e1, e2);

            return priorityDiff;
        }

        private int getVerticalPositionDifferente(Entity e1, Entity e2){
            PositionComponent p1 = pm.get(e1);
            PositionComponent p2 = pm.get(e2);

            return (int) ((p2.y - p1.y) * DIFF_MULTIPLIER);
        }

        private int getPriorityDifference(Entity e1, Entity e2){
            RenderComponent r1 = rm.get(e1);
            RenderComponent r2 = rm.get(e2);

            return r2.getPriority().getValue() - r1.getPriority().getValue();
        }
    }
}
