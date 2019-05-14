package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;

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
            // Make sure that the card is returned to the deck even when
            // an Exception should occur!
            super.doAction(controller, player);
        }
    }
}
