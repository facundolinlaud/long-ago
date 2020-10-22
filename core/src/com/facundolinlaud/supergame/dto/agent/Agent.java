package com.facundolinlaud.supergame.dto.agent;

import com.facundolinlaud.supergame.model.equip.EquipSlot;

import java.util.Map;

public class Agent {
    private String name;
    private int attack;
    private int defense;
    private int health;
    private float velocity;
    private boolean talkable;
    private String factionId;
    private String animationModel;
    private Map<EquipSlot, String> body;
    private Map<EquipSlot, String> equipment;

    private BagInformation bagInformation;
    private CombatInformation combatInformation;
    private BehaviorInformation behaviorInformation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public boolean isTalkable() {
        return talkable;
    }

    public void setTalkable(boolean talkable) {
        this.talkable = talkable;
    }

    public String getAnimationModel() {
        return animationModel;
    }

    public void setAnimationModel(String animationModel) {
        this.animationModel = animationModel;
    }

    public Map<EquipSlot, String> getBody() {
        return body;
    }

    public void setBody(Map<EquipSlot, String> body) {
        this.body = body;
    }

    public Map<EquipSlot, String> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<EquipSlot, String> equipment) {
        this.equipment = equipment;
    }

    public BagInformation getBagInformation() {
        return bagInformation;
    }

    public void setBagInformation(BagInformation bagInformation) {
        this.bagInformation = bagInformation;
    }

    public CombatInformation getCombatInformation() {
        return combatInformation;
    }

    public void setCombatInformation(CombatInformation combatInformation) {
        this.combatInformation = combatInformation;
    }

    public BehaviorInformation getBehaviorInformation() {
        return behaviorInformation;
    }

    public void setBehaviorInformation(BehaviorInformation behaviorInformation) {
        this.behaviorInformation = behaviorInformation;
    }

    public boolean hasBehavior() {
        return this.behaviorInformation != null;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }

    public boolean hasCombat() {
        return this.combatInformation != null;
    }

    public boolean hasBag() {
        return this.bagInformation != null;
    }
}
