package com.facundolinlaud.supergame.ui.view.overlay.controlbar;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class ControlBar {
    private Table leftControlBar;
    private Table rightControlBar;

    private ImageButton questsButton;
    private ImageButton inventoryButton;
    private ImageButton skillsButton;

    private ImageButton equipmentButton;
    private ImageButton statsButton;
    private ImageButton menuButton;

    public ControlBar(Skin skin) {
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
    }

    public Table getLeftControlBar() {
        return leftControlBar;
    }

    public Table getRightControlBar() {
        return rightControlBar;
    }
}
