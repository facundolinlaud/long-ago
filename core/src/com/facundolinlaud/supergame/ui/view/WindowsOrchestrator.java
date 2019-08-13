package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.facundolinlaud.supergame.ui.view.utils.Window;

import java.util.HashMap;
import java.util.Map;

public class WindowsOrchestrator extends InputListener {
    private Map<Window, Group> groups;
    private Map<Integer, Window> keymap;
    private Map<Window, ImageButton> buttons;
    private DialogUI dialogUI;

    public WindowsOrchestrator() {
        groups = new HashMap();
        keymap = new HashMap();
        buttons = new HashMap();

        keymap.put(Input.Keys.C, Window.EQUIPMENT);
        keymap.put(Input.Keys.I, Window.INVENTORY);
        keymap.put(Input.Keys.P, Window.SKILL_TREE);
        keymap.put(Input.Keys.K, Window.ATTRIBUTES);
    }

    public void register(Window window, Group group){
        groups.put(window, group);
    }

    public void register(Window window, ImageButton button){
        buttons.put(window, button);
    }

    @Override
    public boolean keyDown(InputEvent event, int keycode) {
        if(keymap.containsKey(keycode)){
            Window window = keymap.get(keycode);
            Group group = groups.get(window);

            boolean newVisibility = !group.isVisible();

            group.setVisible(newVisibility);
            buttons.get(window).setChecked(newVisibility);
        }else if(keycode == Input.Keys.F){
            dialogUI.onContinueKeyPressed();
        }

        return super.keyDown(event, keycode);
    }

    public void onControlButtonClicked(Window window, ImageButton button) {
        Group group = groups.get(window);
        boolean newVisibility = !group.isVisible();

        group.setVisible(newVisibility);
        button.setChecked(newVisibility);
    }

    public void register(DialogUI dialogUI) {
        this.dialogUI = dialogUI;
    }
}
