package com.facundolinlaud.supergame.ui.view.skilltree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import org.apache.commons.lang3.StringUtils;

import static com.facundolinlaud.supergame.ui.view.utils.Themes.Colors.GRAY;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.REGULAR_14;

public class SkillToolTipContent extends Table {
    private static final float PAD_BOTTOM = 5;

    public SkillToolTipContent(Skill skill, Skin skin, boolean disabled) {
        super(skin);
        align(Align.topLeft);

        initializeDefaults();
        Color titleColor = resolveTitleColor(disabled);

        Label title = new Label(skill.getName(), skin, Themes.Label.SMALL_CAPS_16);
        title.setColor(titleColor);

        Label skillType = new Label("(" + capitalize(skill.getSkillType().toString()) + ")", skin, REGULAR_14);
        skillType.setColor(titleColor);

        Label description = new Label(skill.getDescription(), skin, Themes.Label.ITALIC_15);
        description.setColor(Color.GOLDENROD);

        Label coolDown = new Label("Cool down: " + floatToString(skill.getCooldown()), skin, REGULAR_14);
        coolDown.setColor(GRAY);

        add(title).padBottom(PAD_BOTTOM).row();
        add(skillType).padBottom(PAD_BOTTOM).row();
        add(description).padBottom(PAD_BOTTOM).left().row();
        add(coolDown).padBottom(PAD_BOTTOM).left().row();
    }

    private String capitalize(String str) {
        return StringUtils.capitalize(str.toLowerCase());
    }

    private String floatToString(float manaConsumption) {
        return String.format("%.1f", manaConsumption);
    }

    private Color resolveTitleColor(boolean disabled) {
        if (disabled)
            return Color.FIREBRICK;

        return Color.GREEN;
    }

    private void initializeDefaults() {
        setBackground("gothic-tooltip");
        pad(10);
    }

}
