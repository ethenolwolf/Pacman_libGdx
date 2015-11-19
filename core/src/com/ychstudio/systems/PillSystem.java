package com.ychstudio.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.physics.box2d.Body;
import com.ychstudio.components.MovementComponent;
import com.ychstudio.components.PillComponent;
import com.ychstudio.gamesys.GameManager;

public class PillSystem extends IteratingSystem {

    private ComponentMapper<PillComponent> pillM = ComponentMapper.getFor(PillComponent.class);
    private ComponentMapper<MovementComponent> movementM = ComponentMapper.getFor(MovementComponent.class);

    public PillSystem() {
        super(Family.all(PillComponent.class, MovementComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

        PillComponent pill = pillM.get(entity);
        MovementComponent movement = movementM.get(entity);

        Body body = movement.body;
        if (pill.eaten) {
            body.getWorld().destroyBody(body);
            getEngine().removeEntity(entity);
            
            GameManager.instance.totalPills--;
        }

    }

}