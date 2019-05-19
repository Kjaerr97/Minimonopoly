package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;
/*
@author Asger, s180911
 */
public class CardMoveJail extends Card {
    private Space target;

    public Space getTarget() {
        return target;
    }

    public void setTarget(Space target) {
        this.target = target;
    }

    @Override
    public void doAction(GameController controller, Player player) throws PlayerBrokeException {
        try {
            controller.gotoJail(player);
        } finally {
            super.doAction(controller, player);
        }

    }}
