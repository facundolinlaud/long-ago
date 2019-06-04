package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.itemdropzone.ItemDropTable;
import com.facundolinlaud.supergame.ui.view.profile.ProfileTable;
import com.facundolinlaud.supergame.ui.view.skillsbar.Skillbar;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI, Telegraph {

    private Table table;
    private ItemDropTable itemDropZone;
    private ProfileTable profile;
    private Skillbar skillbar;

    public OverlayUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);

        this.itemDropZone = new ItemDropTable(skin);
        this.profile = new ProfileTable(skin);
        this.skillbar = new Skillbar(skin, new SkillsFactory().getSkills());

        this.table.add(this.profile).expandX().fillX().top().left();
        this.table.row();
        this.table.add(this.itemDropZone).expand().fill();
        this.table.row();
        this.table.add(this.skillbar);
    }

    public void setHealth(float health) {
        this.profile.setHealth(health);
    }

    public void setFPS(int fps) {
        this.profile.setFPS(fps);
    }

    public Table getItemDropZone(){
        return this.itemDropZone;
    }

    @Override
    public Table get() {
        return this.table;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        Skill skill = (Skill) msg.extraInfo;
        skillbar.beginCooldown(skill);

        return true;
    }
}
