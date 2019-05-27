# Long Ago

## A 1 min video demo (no sound)
https://streamable.com/zfluc

## About
Long ago is a long time personal RPG game project. I still have a really long way ahead but it's getting there. Also, I'm trying to start documenting everything but the game got pretty big (however, it still scales like a charm). The last time I counted I had over 190 classes implemented so it's going to take a while.

## Tech stack
* Libgdx as graphic engine
* Ashley (Entity Component System framework)
* Box2d (Physics and lights)
* Gradle
* Jackson

## The game
Currently, you can move through the map with **WASD** keys. You can attack with a weapon of your choice by pressing **2** or you can press **1** and then **click somewhere in the map** to cast an explosion. Also, you can pick up whatever is on the floor with the **E** key. Furthermore, you can bring up your inventory and your equipment window by pressing the **I** and **C** keys respectively. The equipping system works so you can put on and off whatever you want and the sprite will be rendered accordingly. That took some time. You can also open the attributes window by pressing **P** but any changes you make on that window won't affect the game because the attributes system is yet to be implemented.

Thanks!

# Systems
## Status Component
Each agent in the game will have a **Status Component**. An agent will be an NPC spawned by the game or the player itself. The Status Component is conformed by two important states:
* The Action state
* The Direction state
The Action state will hold the state of it's respective agent. In other words: what is the agent doing. Is he walking? Is he attacking? Is he casting a spell? That is the information that will be held by the Action State.

On the other side, the Direction state will hold the direction the agent is facing. If he is walking to the right, then the action of the agent will be *WALKING* and it's direction will be *RIGHT*. As the game is a 2-dimensional world, each agent will always posses a valid direction.


## Rendering systems
This game has many requeriments when it comes to rendering things. These are:
- Rendering static textures. For example a plant
- Drawing a sprite (that is, a group of subtextures within a parent texture)
- Drawing several sprites on top of each other. These are necessary for rendering the equipment of an agent. For example, in order to draw a player with his armor and sword, we will need to first draw the body of the agent, then, on top of the body, we will draw the armor and finally the sword.

The approach I chose uses four components:
* AnimableSpriteComponent
* RefreshSpriteRequirementComponent
* StackableSpriteComponent
* StackedSpritesComponent

Suppose we want to spawn a static zombie on the map. We will need to add a RenderComponent to the entity providing the path to the picture of the zombie. Then the **Render System** will catch the zombie entity and will draw the object accordingly at the position provided by a **Position Component**.

If you want to go further and add some movement to the zombie, you will need to define the set of possible movements that the zombie can make, and a spritesheet with each movement. Let's say we have a zombie that can walk 2-dimensionally, that is upside, downside, leftside and rightside. For each walking direction, we will need a set of textures to perform the corresponding animation. So, if we have a sprite sheet of a zombie walking, each row of texture regions will belong to certain walking directions. In Long Ago, I decided to put this information into json files like this:

```
{
	"width": 64,
	"height": 64,
	"frameDuration": 0.1,
	"subanimations": {
		"WALKING_UP": {
			"x": 0,
			"y": 8,
			"length": 9,
			"playMode": "LOOP"
		}, "WALKING_LEFT": {
			"x": 0,
			"y": 9,
			"length": 9,
			"playMode": "LOOP"
		}, "WALKING_DOWN": {
			"x": 0,
			"y": 10,
			"length": 9,
			"playMode": "LOOP"
		}, "WALKING_RIGHT": {
			"x": 0,
			"y": 11,
			"length": 9,
			"playMode": "LOOP"
(...)
```

This code snipped is a chunk of assets/model/animations/default.json. It basically says which rows of a sprite sheet are used for walking up, walking down, etc. This generic approach was needed because I'm not a graphic designer, therefore I'm obliged to consume free 2d sprites from the internet and they don't always maintain the same order and stuff.

So, this json is inserted into a **AnimableSpriteComponent** among it's sprites and their animations put into a Map<Action, Animations>. To make it short, for each tick, the StackedSpriteSystem will put all of the corresponding texture regions to be drawn (according to the action and direction of the agent) on the Render Component.