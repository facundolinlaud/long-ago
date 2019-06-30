package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.overlay.controlbar.ControlBar;
import com.facundolinlaud.supergame.ui.view.overlay.itemdropzone.ItemDropTable;
import com.facundolinlaud.supergame.ui.view.overlay.profile.ProfileTable;
import com.facundolinlaud.supergame.ui.view.overlay.skillsbar.Skillbar;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI {

    private Table table;
    private ItemDropTable itemDropZone;
    private ProfileTable profile;
    private Skillbar skillbar;
    private ControlBar controlBar;

    public OverlayUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);

        this.itemDropZone = new ItemDropTable(skin);
        this.profile = new ProfileTable(skin);

        this.table.add(this.profile).colspan(3).expandX().fillX().top().left();
        this.table.row();
        this.table.add(this.itemDropZone).colspan(3).expand().fill();
        this.table.row();

        this.controlBar = new ControlBar(skin);
        this.skillbar = new Skillbar(skin, new SkillsFactory().getSkills());
        this.table.add(controlBar.getLeftControlBar()).expandX().right().bottom();
        this.table.add(this.skillbar).center();
        this.table.add(controlBar.getRightControlBar()).expandX().left().bottom();
    }

    public void setHealth(float health) {
        this.profile.setHealth(health);
    }

    public void setFPS(int fps) {
        this.profile.setFPS(fps);
    }

    public void setPosition(Vector2 position) {
        this.profile.setPosition(position);
    }

    public void setBodyPosition(Vector2 bodyPosition) {
        this.profile.setBodyPosition(bodyPosition);
    }

    public void beginCooldown(Skill skill) {
        this.skillbar.beginCooldown(skill);
    }

    public Table getItemDropZone(){
        return this.itemDropZone;
    }

    @Override
    public Table get() {
        return this.table;
    }
}
