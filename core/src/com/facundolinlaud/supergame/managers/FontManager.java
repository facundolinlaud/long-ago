package com.facundolinlaud.supergame.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Created by facundo on 3/24/16.
 */
public class FontManager {
    public static final String FONTS_GOUDY_MEDIAEVAL_BOLD = "fonts/goudy_mediaeval/bold.ttf";

    private FreeTypeFontGenerator generator;
    private BitmapFont healthFont;
    private SpriteBatch batch;

    public FontManager(SpriteBatch batch) {
        this.batch = batch;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONTS_GOUDY_MEDIAEVAL_BOLD));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 12;
//        parameter.borderColor = Color.BLACK;
//        parameter.borderWidth = 4;
//        parameter.color = Color.WHITE;

        healthFont = generator.generateFont(parameter);
        generator.dispose();
    }

    public void render(String text, float x, float y){
        healthFont.draw(batch, text, x, y);
    }
}
