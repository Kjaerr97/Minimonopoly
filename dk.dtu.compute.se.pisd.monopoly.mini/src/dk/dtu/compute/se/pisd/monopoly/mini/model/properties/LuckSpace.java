package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Space;
import dk.dtu.compute.se.pisd.monopoly.mini.model.exceptions.PlayerBrokeException;
//@author s180911 Asger
public class LuckSpace extends Space {
    @Override
    public void doAction(GameController controller, Player player) throws PlayerBrokeException {
        controller.takeChanceCard(player);
        super.doAction(controller, player);
    }
}
