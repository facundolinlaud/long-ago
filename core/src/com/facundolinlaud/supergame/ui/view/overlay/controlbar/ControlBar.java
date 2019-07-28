package com.facundolinlaud.supergame.ui.view.overlay.controlbar;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.facundolinlaud.supergame.ui.view.WindowsOrchestrator;
import com.facundolinlaud.supergame.ui.view.utils.Window;
import com.facundolinlaud.supergame.utils.events.AttributeUpgradeEvent;
import com.facundolinlaud.supergame.utils.events.Messages;

public class ControlBar {
    private Table leftControlBar;
    private Table rightControlBar;

    private ImageButton questsButton;
    private ImageButton inventoryButton;
    private ImageButton skillsButton;

    private ImageButton equipmentButton;
    private ImageButton statsButton;
    private ImageButton menuButton;

    public ControlBar(Skin skin, WindowsOrchestrator wo) {
        leftControlBar = new Table(skin);
        rightControlBar = new Table(skin);

        questsButton = new ImageButton(skin, "control-quest");
        inventoryButton = new ImageButton(skin, "control-inventory");
        skillsButton = new ImageButton(skin, "control-skills");

        equipmentButton = new ImageButton(skin, "control-equipment");
        statsButton = new ImageButton(skin, "control-stats");
        menuButton = new ImageButton(skin, "control-menu");

        leftControlBar.add(questsButton);
        leftControlBar.add(inventoryButton);
        leftControlBar.add(skillsButton);

        rightControlBar.add(equipmentButton);
        rightControlBar.add(statsButton);
        rightControlBar.add(menuButton);

        registerButtons(wo);
    }

    private void registerButtons(WindowsOrchestrator wo){
        registerButton(wo, Window.INVENTORY, inventoryButton);
        registerButton(wo, Window.SKILL_TREE, skillsButton);
        registerButton(wo, Window.EQUIPMENT, equipmentButton);
        registerButton(wo, Window.ATTRIBUTES, statsButton);
    }

    private void registerButton(WindowsOrchestrator wo, Window window, ImageButton button){
        wo.register(window, button);
        button.addListener(new ClickListener(Input.Buttons.LEFT){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                wo.onControlButtonClicked(window, button);
            }
        });
    }

    public Table getLeftControlBar() {
        return leftControlBar;
    }

    public Table getRightControlBar() {
        return rightControlBar;
    }
}
