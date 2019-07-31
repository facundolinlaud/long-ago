package com.facundolinlaud.supergame.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ai.msg.MessageManager;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class BagComponent implements Component {
    private boolean gathering;
    private IntegerProperty gold;
    private ObservableList<Entity> items;

    public BagComponent(List<Entity> items, int gold) {
        this.items = FXCollections.observableArrayList(items);
        this.gold = new SimpleIntegerProperty(gold);
    }

    public BagComponent(List<Entity> items, int gold, int onChangeMessage) {
        this(items, gold);
        this.items.addListener((ListChangeListener<? super Entity>)
                c -> MessageManager.getInstance().dispatchMessage(onChangeMessage));
        this.gold.addListener((observable, oldValue, newValue) ->
                MessageManager.getInstance().dispatchMessage(onChangeMessage));
    }

    public void add(Entity item){
        this.items.add(item);
    }

    public List<Entity> get() {
        return items;
    }

    public Entity get(int i){
        return items.get(i);
    }

    public int size(){
        return items.size();
    }

    public void remove(Entity item){
        items.remove(item);
    }

    public Entity remove(int index){
        return items.remove(index);
    }

    public void set(int index, Entity element){
        items.set(index, element);
    }

    public boolean isGathering() {
        return gathering;
    }

    public void setGathering(boolean gathering) {
        this.gathering = gathering;
    }

    public void addGold(int gold){
        this.gold.set(getGold() + gold);
    }

    public void subtractGold(int gold){
        this.gold.set(getGold() - gold);
    }

    public int getGold() {
        return gold.get();
    }
}
