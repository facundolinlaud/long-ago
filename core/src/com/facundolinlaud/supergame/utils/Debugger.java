package com.facundolinlaud.supergame.utils;

import com.badlogic.gdx.ai.msg.MessageManager;
import com.facundolinlaud.supergame.utils.events.Messages;

public class Debugger {
    public static void debug(String text){
        MessageManager.getInstance().dispatchMessage(Messages.CUSTOM_MESSAGE, text);
        System.out.println(text);
    }
}
