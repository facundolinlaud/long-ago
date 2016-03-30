package com.facundolinlaud.supergame.ui.view;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.ui.view.itemdropzone.ItemDropTable;
import com.facundolinlaud.supergame.ui.view.profile.ProfileTable;

/**
 * Created by facundo on 3/27/16.
 */
public class OverlayUI implements UI {

    private Table table;
    private ItemDropTable itemDropZone;
    private ProfileTable profile;

    public OverlayUI(Skin skin) {
        this.table = new Table(skin);
        this.table.setFillParent(true);
        this.table.align(Align.topLeft);

        this.itemDropZone = new ItemDropTable(skin);
        this.profile = new ProfileTable(skin);

        this.table.add(this.profile).expandX().fillX().top().left();
        this.table.row();
        this.table.add(this.itemDropZone).expand().fill();
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
}
