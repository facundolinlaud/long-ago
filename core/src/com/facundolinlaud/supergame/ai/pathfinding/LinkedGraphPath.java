package com.facundolinlaud.supergame.ai.pathfinding;

import com.badlogic.gdx.ai.pfa.GraphPath;
import com.badlogic.gdx.utils.Array;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedGraphPath<N> implements GraphPath<N> {
    private static final int FIRST_ELEMENT = 0;

    private boolean reversed = false;
    private LinkedList<N> nodes;

    public LinkedGraphPath() {
        this.nodes = new LinkedList();
    }

    public LinkedGraphPath(Array<N> nodes) {
        for(N n : nodes)
            this.nodes.add(n);
    }

    public N pop(){
        if(reversed)
            return nodes.removeLast();
        else
            return nodes.removeFirst();
    }

    @Override
    public int getCount() {
        return nodes.size();
    }

    @Override
    public N get(int index) {
        if(reversed)
            return nodes.get(getCount() - 1 - index);
        else
            return nodes.get(index);
    }

    @Override
    public void add(N node) {
        if(reversed)
            nodes.addFirst(node);
        else
            nodes.addLast(node);
    }

    @Override
    public void clear() {
        nodes.clear();
        reversed = false;
    }

    @Override
    public void reverse() {
        reversed = !reversed;
    }

    @Override
    public Iterator<N> iterator() {
        return nodes.iterator();
    }

    public N first() {
        if(reversed)
            return nodes.getLast();
        else
            return nodes.getFirst();
    }

    @Override
    public String toString() {
        return "LinkedGraphPath{" +
                "reversed=" + reversed +
                ", nodes=" + nodes +
                '}';
    }

    public boolean isEmpty() {
        return getCount() == 0;
    }
}
