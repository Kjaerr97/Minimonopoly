package dk.dtu.compute.se.pisd.monopoly.mini.model.cards;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Card;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.GameEndedException;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;

public class CardReceiveMoneyFromPlayer extends Card {
    private int amount;

    public int getAmount (){
        return amount;
    }
    public void setAmount(int amount){
        this.amount = amount;
    }

    public void doAction(GameController controller, Player player) throws PlayerBrokeException {
        try {
            for (Player player2: controller.getGame().getPlayers()){
                if (player2 != player){
                    controller.payment(player2,amount,player);
                }
            }
        } catch (GameEndedException e) {
            e.printStackTrace();
        } finally {

            super.doAction(controller, player);
        }
    }

}

