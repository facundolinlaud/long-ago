package com.facundolinlaud.supergame.ui.view.cross;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

public class GothicWindow extends Window {
    private static final float TOP_PADDING = 52;//50;
    private static final float LEFT_PADDING = 8;//5;
    private static final float BOTTOM_PADDING = 7;//14;
    private static final float RIGHT_PADDING = 6;//5;

    private Table content;

    public GothicWindow(String title, Skin skin) {
        super(title, skin, Themes.Window.GOTHIC);

        setup();
        adjustTitlePosition();

        this.content = new Table(skin);
        super.add(this.content).grow();
    }

    public GothicWindow(String title, Skin skin, String backgroundStyle){
        this(title, skin);
        this.content.setBackground(backgroundStyle);
    }

    private void setup(){
        super.pad(TOP_PADDING, LEFT_PADDING, BOTTOM_PADDING, RIGHT_PADDING);
        super.align(Align.topLeft);
    }

    private void adjustTitlePosition() {
        getTitleTable().align(Align.top).padTop(13);
        getTitleLabel().setAlignment(Align.center);
    }

    @Override
    public Table pad(Value pad) {
        return content.pad(pad);
    }

    @Override
    public Table pad(Value top, Value left, Value bottom, Value right) {
        return content.pad(top, left, bottom, right);
    }

    @Override
    public Table padTop(Value padTop) {
        return content.padTop(padTop);
    }

    @Override
    public Table padLeft(Value padLeft) {
        return content.padLeft(padLeft);
    }

    @Override
    public Table padBottom(Value padBottom) {
        return content.padBottom(padBottom);
    }

    @Override
    public Table padRight(Value padRight) {
        return content.padRight(padRight);
    }

    @Override
    public Table pad(float pad) {
        return content.pad(pad);
    }

    @Override
    public Table pad(float top, float left, float bottom, float right) {
        return content.pad(top, left, bottom, right);
    }

    @Override
    public Table padTop(float padTop) {
        return content.padTop(padTop);
    }

    @Override
    public Table padLeft(float padLeft) {
        return content.padLeft(padLeft);
    }

    @Override
    public Table padBottom(float padBottom) {
        return content.padBottom(padBottom);
    }

    @Override
    public Table padRight(float padRight) {
        return content.padRight(padRight);
    }

    @Override
    public Table align(int align) {
        return content.align(align);
    }

    @Override
    public Table center() {
        return content.center();
    }

    @Override
    public Table top() {
        return content.top();
    }

    @Override
    public Table left() {
        return content.left();
    }

    @Override
    public Table bottom() {
        return content.bottom();
    }

    @Override
    public Table right() {
        return content.right();
    }

    @Override
    public Cell row() {
        return content.row();
    }

    @Override
    public <T extends Actor> Cell<T> add(T actor) {
        return content.add(actor);
    }

    @Override
    public Table add(Actor... actors) {
        return content.add(actors);
    }

    @Override
    public Cell add() {
        return content.add();
    }
}
