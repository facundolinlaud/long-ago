package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.overlay.controlbar.ControlBar;
import com.facundolinlaud.supergame.ui.view.overlay.itemdropzone.ItemDropTable;
import com.facundolinlaud.supergame.ui.view.overlay.profile.ProfileTable;
import com.facundolinlaud.supergame.ui.view.overlay.skillcasting.SkillCastingBar;
import com.facundolinlaud.supergame.ui.view.overlay.skillsbar.SkillBar;

import java.util.List;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI {
    private Table table;
    private ItemDropTable itemDropZone;
    private ProfileTable profile;
    private SkillCastingBar skillCastingBar;
    private SkillBar skillbar;
    private ControlBar controlBar;

    public OverlayUI(Skin skin, DragAndDrop skillsDAD) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);

        this.itemDropZone = new ItemDropTable(skin);
        this.profile = new ProfileTable(skin);

        this.table.add(profile).colspan(3).expandX().fillX().top().left();
        this.table.row();
        this.table.add(itemDropZone).colspan(3).expand().fill();
        this.table.row();

        this.skillCastingBar = new SkillCastingBar(skin);
        this.table.add(skillCastingBar).colspan(3);

        this.table.row();

        this.controlBar = new ControlBar(skin);
        this.skillbar = new SkillBar(skin, new SkillsFactory().getSkills(), skillsDAD);
        this.table.add(controlBar.getLeftControlBar()).expandX().right().bottom();
        this.table.add(skillbar).center();
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

    public void updateCastingBar(String skillName, float castingBarValue){
        skillCastingBar.updateCastingBar(skillName, castingBarValue);
    }

    public void beginCooldown(Skill skill) {
        this.skillbar.beginCooldown(skill);
    }

    public void updateSkillBar(List<Skill> skills) {
        skillbar.update(skills);
    }

    public Table getItemDropZone(){
        return this.itemDropZone;
    }

    @Override
    public Table get() {
        return this.table;
    }
}
