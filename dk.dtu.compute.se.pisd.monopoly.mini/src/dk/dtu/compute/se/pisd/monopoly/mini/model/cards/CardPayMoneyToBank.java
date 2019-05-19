package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;
/*
@author Asger, s180911
 */
public class CardPayMoneyToBank extends Card {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    @Override
    public void doAction(GameController controller, Player payer) throws PlayerBrokeException {
        try {
            controller.paymentToBank(payer,amount);
        } finally {
            super.doAction(controller, payer);
        }
    }

}
