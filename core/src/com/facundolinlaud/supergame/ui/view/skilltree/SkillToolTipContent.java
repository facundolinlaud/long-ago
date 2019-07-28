package com.facundolinlaud.supergame.ui.view.skilltree;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.facundolinlaud.supergame.model.skill.ProjectileInformation;
import com.facundolinlaud.supergame.model.skill.Skill;
import com.facundolinlaud.supergame.ui.view.utils.Themes;
import com.sun.xml.internal.ws.util.StringUtils;

import static com.facundolinlaud.supergame.ui.view.utils.Themes.Colors.BLUE;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Colors.GRAY;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.REGULAR_14;
import static com.facundolinlaud.supergame.ui.view.utils.Themes.Label.REGULAR_15;

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

        Label equipmentRequired = new Label("Requires " + skill.getEquipmentRequired().toString(),
                skin, REGULAR_15);
        equipmentRequired.setColor(Color.FIREBRICK);

        Label manaConsumption = new Label("Mana consumption: " + floatToString(skill.getManaConsumption()),
                skin, REGULAR_15);
        manaConsumption.setColor(Color.BLUE);

        Label coolDown = new Label("Cool down: " + floatToString(skill.getCooldown()), skin, REGULAR_14);
        coolDown.setColor(GRAY);

        Label castTime = new Label("Cast time: " + floatToString(skill.getCastTime()), skin, REGULAR_14);
        castTime.setColor(GRAY);

        Label baseDamage = new Label("Base damage: " + floatToString(skill.getBaseDamage()), skin, REGULAR_14);
        baseDamage.setColor(GRAY);

        Label baseHeal = new Label("Base heal: " + floatToString(skill.getBaseHeal()), skin, REGULAR_14);
        baseHeal.setColor(GRAY);

        add(title).padBottom(PAD_BOTTOM).row();
        add(skillType).padBottom(PAD_BOTTOM).row();
        add(description).padBottom(PAD_BOTTOM).left().row();
        add(equipmentRequired).padBottom(PAD_BOTTOM).left().row();
        add(coolDown).padBottom(PAD_BOTTOM).left().row();
        add(castTime).padBottom(PAD_BOTTOM).left().row();
        add(baseDamage).padBottom(PAD_BOTTOM).left().row();
        add(baseHeal).padBottom(PAD_BOTTOM).left().row();

        if(skill.isProjectile()){
            ProjectileInformation pi = skill.getProjectileInformation();
            Label maxTravelDistance = new Label("Max. Travel Distance: " + floatToString(pi.getMaxTravelDistance()),
                    skin, Themes.Label.REGULAR_14);
            maxTravelDistance.setColor(BLUE);

            Label projectileSpeed = new Label("Projectile Speed: " + floatToString(pi.getProjectileSpeed()),
                    skin, Themes.Label.REGULAR_14);
            projectileSpeed.setColor(BLUE);

            add(maxTravelDistance).padBottom(PAD_BOTTOM).left().row();
            add(projectileSpeed).padBottom(PAD_BOTTOM).left().row();
        }
    }

    private String capitalize(String str) {
        return StringUtils.capitalize(str.toLowerCase());
    }

    private String floatToString(float manaConsumption){
        return String.format("%.1f", manaConsumption);
    }

    private Color resolveTitleColor(boolean disabled){
        if(disabled)
            return Color.FIREBRICK;

        return Color.GREEN;
    }

    private void initializeDefaults() {
        setBackground("gothic-tooltip");
        pad(10);
    }

}
