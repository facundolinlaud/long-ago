package com.facundolinlaud.supergame.components.player;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

import java.util.List;

/**
 * Created by facundo on 3/26/16.
 */
public class BagComponent implements Component {
    private boolean gathering;
    private ObservableList<Entity> items;

    public BagComponent(List<Entity> items) {
        this.items = FXCollections.observableArrayList(items);
    }

    public BagComponent(List<Entity> items, ListChangeListener<? super Entity> listener) {
        this(items);
        this.items.addListener(listener);
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
}
