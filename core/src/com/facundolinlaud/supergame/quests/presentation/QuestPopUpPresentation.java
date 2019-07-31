package com.facundolinlaud.supergame.quests.presentation;

public class QuestPopUpPresentation implements QuestPresentation {
    private String message;

    public QuestPopUpPresentation(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void present() {
        System.out.println(message);
    }
}
