package com.facundolinlaud.supergame.model.skill;

import com.facundolinlaud.supergame.model.status.Action;

public class TwoClickInformation {
    private Action waitingForClickAction;

    public Action getWaitingForClickAction() {
        return waitingForClickAction;
    }

    public void setWaitingForClickAction(Action waitingForClickAction) {
        this.waitingForClickAction = waitingForClickAction;
    }
}
