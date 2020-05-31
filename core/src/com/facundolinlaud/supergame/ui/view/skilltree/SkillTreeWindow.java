package com.facundolinlaud.supergame.ui.view.skilltree;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.msg.MessageManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.facundolinlaud.supergame.factory.ModelFactory;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.model.skill.SkillTreeModel;
import com.facundolinlaud.supergame.ui.view.cross.GothicWindow;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SkillSlotSource;
import com.facundolinlaud.supergame.ui.view.cross.draganddrop.SlotType;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.facundolinlaud.supergame.utils.events.Messages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SkillTreeWindow extends GothicWindow {
    private static final String TITLE = "Skills";

    private static final String EMPTY_SPACE = "";
    private static final String DOWN_ARROW = "↓";
    private static final String LEFT_ARROW = "←";
    private static final String RIGHT_ARROW = "→";
    private static final String VERTICAL_LONG_ARROW = "|";

    private static final int SKILL_FRAME_SIZE = 42;
    private static final int EMPTY_SPACE_SIZE = 32;

    private Skin skin;
    private Table grid;
    private Label pointsLeft;
    private Map<String, Drawable> arrows;
    private DragAndDrop dragAndDrop;
    private List<List<String>> visualRepresentation;

    public SkillTreeWindow(Skin skin, DragAndDrop dragAndDrop) {
        super(TITLE, skin, Themes.Background.DARK);
        this.skin = skin;
        this.dragAndDrop = dragAndDrop;

        SkillTreeModel skillTree = ModelFactory.getSkillTree();
        this.visualRepresentation = skillTree.getVisualRepresentation();

        initializeArrows(skin);
        setSize(300, 400);
        padBottom(6);
        padRight(6);
        setVisible(false);

        this.grid = new Table(skin);
        add(grid).grow();
        row();
        this.pointsLeft = new Label("Points Left: 0", skin, Themes.Label.SMALL_CAPS_16);
        this.pointsLeft.setColor(Color.GOLDENROD);
        add(pointsLeft).right();
    }

    public void update(Map<String, Skill> allSkills, List<Skill> playerSkills, int assignablePoints) {
        drawGrid(allSkills, playerSkills);
        drawPointsLeft(assignablePoints);
    }

    private void initializeArrows(Skin skin) {
        this.arrows = new HashMap();
        this.arrows.put(DOWN_ARROW, skin.getDrawable("down-arrow"));
        this.arrows.put(LEFT_ARROW, skin.getDrawable("left-arrow"));
        this.arrows.put(RIGHT_ARROW, skin.getDrawable("right-arrow"));
        this.arrows.put(VERTICAL_LONG_ARROW, skin.getDrawable("vertical-long-arrow"));
    }

    private void drawGrid(Map<String, Skill> allSkills, List<Skill> playerSkills) {
        this.grid.reset();

        for (int x = 0; x < visualRepresentation.size(); x++) {
            for (int y = 0; y < visualRepresentation.get(x).size(); y++) {
                String cell = visualRepresentation.get(x).get(y);

                if (isSkill(cell)) {
                    boolean disabled = !isSkillUnlocked(allSkills, playerSkills, cell);

                    Skill skill = allSkills.get(cell);
                    SkillTreeFramedSlot frame = new SkillTreeFramedSlot(skin, skill, disabled);
                    grid.add(frame).size(SKILL_FRAME_SIZE, SKILL_FRAME_SIZE);

                    if (disabled) {
                        registerUnlockRequestListener(skill, frame);
                    } else {
                        registerAsDraggableSlot(dragAndDrop, frame);
                    }
                } else if (cell == EMPTY_SPACE) {
                    grid.add().size(EMPTY_SPACE_SIZE, EMPTY_SPACE_SIZE);
                } else {
                    Image arrow = new Image(arrows.get(cell));
                    Cell<Image> addedCell = grid.add(arrow);

                    if (!isHorizontalArrow(cell))
                        addedCell.fillY();
                    else
                        addedCell.fillX();
                }
            }

            grid.row();
        }
    }

    private void registerUnlockRequestListener(Skill skill, SkillTreeFramedSlot frame) {
        frame.addListener(new ClickListener(Input.Buttons.RIGHT) {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MessageManager.getInstance().dispatchMessage(Messages.SKILL_UNLOCK_REQUEST, skill);
            }
        });
    }

    private void drawPointsLeft(int assignablePoints) {
        this.pointsLeft.setText("Assignable Points Left: " + assignablePoints);
    }

    private boolean isSkillUnlocked(Map<String, Skill> allSkills, List<Skill> playerSkills, String cell) {
        Skill skill = allSkills.get(cell);
        return playerSkills.contains(skill);
    }

    private void registerAsDraggableSlot(DragAndDrop dragAndDrop, SkillTreeFramedSlot frame) {
        dragAndDrop.addSource(new SkillSlotSource(frame.getSlot(), SlotType.SKILL_TREE_SLOT));
    }

    private boolean isHorizontalArrow(String cell) {
        return LEFT_ARROW.equals(cell) || RIGHT_ARROW.equals(cell);
    }

    private boolean isVerticalArrow(String cell) {
        return VERTICAL_LONG_ARROW.equals(cell) || DOWN_ARROW.equals(cell);
    }

    private boolean isEmptySpace(String cell) {
        return EMPTY_SPACE.equals(cell);
    }

    private boolean isSkill(String cell) {
        return !isVerticalArrow(cell) && !isHorizontalArrow(cell) && !isEmptySpace(cell);
    }
}
