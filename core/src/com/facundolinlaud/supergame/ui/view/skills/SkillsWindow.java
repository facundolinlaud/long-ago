package com.facundolinlaud.supergame.ui.view.skills;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillTreeModel;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.cross.SkillSlot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillsWindow extends GothicWindow {
    private static final int EMPTY_SPACE = -1;

    private static final String TITLE = "Skills";
    private SkillsFactory skillsFactory;
    private Table grid;
    private Skin skin;
    private Map<Integer, Drawable> arrows;

    public SkillsWindow(Skin skin, SkillsFactory skillsFactory) {
        super(TITLE, skin);
        this.skillsFactory = skillsFactory;
        this.skin = skin;

        setSize(250, 250);

        this.arrows = new HashMap();
        this.arrows.put(-2, skin.getDrawable("down-arrow"));
        this.arrows.put(-3, skin.getDrawable("left-arrow"));
        this.arrows.put(-4, skin.getDrawable( "right-arrow"));
        this.arrows.put(-5, skin.getDrawable("vertical-long-arrow"));

        SkillTreeModel skillTree = ModelFactory.getSkillTree();
        drawGrid(skillTree.getVisualRepresentation());
    }

    private void drawGrid(List<List<Integer>> visualRepresentation){
        grid = new Table(skin);

        for(int x = 0; x < visualRepresentation.size(); x++){
            for(int y = 0; y < visualRepresentation.get(x).size(); y++){
                int cell = visualRepresentation.get(x).get(y);

                if(isSkill(cell)){
                    Skill skill = skillsFactory.get(cell);
                    ResearchableSkillSlot skillSlot = new ResearchableSkillSlot(skin);
                    skillSlot.setContent(skill);

                    grid.add(skillSlot).size(42, 42);
                }else if(cell == EMPTY_SPACE){
                    grid.add().size(32, 32);
                }else{
                    Image arrow = new Image(arrows.get(cell));
                    grid.add(arrow).fillY();
                }
            }

            grid.row();
        }

        add(grid).grow();
    }

    private boolean isSkill(int cell) {
        return cell >= 0;
    }
}
