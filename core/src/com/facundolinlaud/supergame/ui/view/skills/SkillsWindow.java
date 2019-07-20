package com.facundolinlaud.supergame.ui.view.skills;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.factory.SkillsFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillTreeModel;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SkillSlotSource;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SlotType;
import com.facundolinlaud.supergame.ui.view.utils.Themes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillsWindow extends GothicWindow {
    private static final int EMPTY_SPACE = -1;
    private static final int DOWN_ARROW = -2;
    private static final int LEFT_ARROW = -3;
    private static final int RIGHT_ARROW = -4;
    private static final int VERTICAL_LONG_ARROW = -5;

    private static final String TITLE = "Skills";

    private Skin skin;
    private Table grid;
    private Label pointsLeft;
    private SkillsFactory skillsFactory;
    private Map<Integer, Drawable> arrows;

    public SkillsWindow(Skin skin, SkillsFactory skillsFactory, DragAndDrop dragAndDrop) {
        super(TITLE, skin);
        this.skillsFactory = skillsFactory;
        this.skin = skin;
        this.arrows = new HashMap();
        this.arrows.put(DOWN_ARROW, skin.getDrawable("down-arrow"));
        this.arrows.put(LEFT_ARROW, skin.getDrawable("left-arrow"));
        this.arrows.put(RIGHT_ARROW, skin.getDrawable( "right-arrow"));
        this.arrows.put(VERTICAL_LONG_ARROW, skin.getDrawable("vertical-long-arrow"));

        setSize(300, 300);
        padBottom(20);
        padRight(20);
        SkillTreeModel skillTree = ModelFactory.getSkillTree();
        drawGrid(skillTree.getVisualRepresentation(), dragAndDrop);
        drawPointsLeft();
    }

    private void drawPointsLeft() {
        this.pointsLeft = new Label("Points left: 0", skin, Themes.Label.SMALL_CAPS_16);
        row();
        add(pointsLeft).right();
    }

    private void drawGrid(List<List<Integer>> visualRepresentation, DragAndDrop dragAndDrop){
        grid = new Table(skin);

        for(int x = 0; x < visualRepresentation.size(); x++){
            for(int y = 0; y < visualRepresentation.get(x).size(); y++){
                int cell = visualRepresentation.get(x).get(y);

                if(isSkill(cell)){
                    Skill skill = skillsFactory.get(cell);
                    SkillTreeFramedSlot frame = new SkillTreeFramedSlot(skin);
                    frame.setContent(skill);

                    grid.add(frame).size(42, 42);
                    registerAsDraggableSlot(dragAndDrop, frame);
                }else if(cell == EMPTY_SPACE){
                    grid.add().size(32, 32);
                }else{
                    Image arrow = new Image(arrows.get(cell));
                    Cell<Image> addedCell = grid.add(arrow);

                    if(!isAnHorizontalArrow(cell))
                        addedCell.fillY();
                    else
                        addedCell.fillX();
                }
            }

            grid.row();
        }

        add(grid).grow();
    }

    private void registerAsDraggableSlot(DragAndDrop dragAndDrop, SkillTreeFramedSlot frame) {
        dragAndDrop.addSource(new SkillSlotSource(frame.getSlot(), SlotType.SKILL_TREE_SLOT));
    }

    private boolean isAnHorizontalArrow(int cell) {
        return cell == LEFT_ARROW || cell == RIGHT_ARROW;
    }

    private boolean isSkill(int cell) {
        return cell >= 0;
    }
}
