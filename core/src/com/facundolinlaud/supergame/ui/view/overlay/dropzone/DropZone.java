package com.facundolinlaud.supergame.ui.view.overlay.dropzone;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.facundolinlaud.supergame.ui.view.overlay.skillsbar.SkillBarSlotTarget;

/**
 * Created by facundo on 3/29/16.
 */
public class DropZone extends Table {

    public DropZone(Skin skin, DragAndDrop itemsDAD, DragAndDrop skillsDAD) {
        super(skin);

        itemsDAD.addTarget(new ItemDropZoneTarget(this));
        skillsDAD.addTarget(new SkillDropZoneTarget(this));
    }
}
