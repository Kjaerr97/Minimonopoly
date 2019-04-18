package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.GameEndedException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;

public class CardMonopolyLegat extends Card {
    private int amount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void doAction(GameController controller, Player receiver) throws PlayerBrokeException {
        try {
            if(receiver.getBalance()>1000){
                controller.paymentFromBank(receiver,amount);
            }
            else{
                controller.paymentFromBank(receiver,0);
            }
        } finally {
            super.doAction(controller, receiver);
        }
    }
}
