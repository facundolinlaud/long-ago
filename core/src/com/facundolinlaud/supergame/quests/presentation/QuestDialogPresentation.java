package com.facundolinlaud.supergame.quests.presentation;

public class QuestDialogPresentation implements QuestPresentation {
    private String message;

    public QuestDialogPresentation(String message) {
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
