# Long Ago

## A 1 min video demo (no sound)
https://streamable.com/zfluc

## About
Long ago is a long time personal RPG game project. I still have a really long way ahead but it's getting there. Also, I'm trying to start documenting everything but the game got pretty big (however it still scales like a charm). The last time I counted I had over 190 classes implemented so it's going to take a while.

## Tech stack
* Libgdx as graphic engine
* Ashley (Entity Component System framework)
* Box2d (Physics and lights)
* Gradle
* Jackson

## Entity Component System
A friend of mine recommended me trying this paradigm instead of going with the classical OOP approach, so you won't see much inheritance except for the UI and some other casual stuff. No need to say, ECS is awesome.

## The game
Currently, you can move through the map with **WASD** keys. You can attack with a weapon of your choice by pressing **2** or you can press **1** and then **click somewhere in the map** to cast an explosion. Also, you can pick up whatever is on the floor with the **E** key. Furthermore, you can bring up your inventory and your equipment window by pressing the **I** and **C** keys respectively. The equipping system works so you can put on and off whatever you want and the sprite will be rendered accordingly. That took some time. You can also open the attributes window by pressing **P** but any changes you make on that window won't affect the game because the attributes system is yet to be implemented.

Thanks!

## Game components
### General components
* AttributesComponent
* BodyComponent
* HealthComponent
* ParticleComponent
* PositionComponent
* RenderComponent
* StatusComponent
* VelocityComponent

### Items system components
* EquipableComponent
* ItemComponent
* PickupableComponent

### Player's system components
* BagComponent
* KeyboardComponent
* WearComponent

### Skills system components
* SkillCastedComponent
* SkillCastingComponent
* SkillCastingRequestComponent
* SkillClickComponent
* SkillLockdownComponent
* SkillTargetedComponent

### Sprites system components
* AnimableSpriteComponent
* RefreshSpriteRequirementComponent
* StackableSpriteComponent
* StackedSpritesComponent

## Systems
### General systems
* CameraFocusSystem
* HealthSystem
* MovementSystem
* ParticleSystem
* PhysicsSystem
* PickUpSystem
* PlayerInputSystem
* RenderSystem

### Skills systems
* KeyPressSkillCastingRequestSystem
* KeyPressThenClickCastingRequestSystem
* SkillCastingSystem
* SkillLockdownSystem
* SkillTargetedSystem

### Sprites systems
* AnimableSpriteSystem
* StackableSpriteSystem
* StackedSpritesSystem

### UI systems
* AttributesUISystem
* EquipmentUISystem
* InventoryUISystem
* ProfileUISystem
* SkillCastingUISystem