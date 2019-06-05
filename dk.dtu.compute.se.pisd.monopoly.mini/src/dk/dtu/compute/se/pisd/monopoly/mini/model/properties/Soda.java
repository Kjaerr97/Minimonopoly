package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

/**
 * A specific property, which represents real estate on which houses
 * and hotels can be built. Note that this class does not have details
 * yet and needs to be implemented.
 * A specific property,
 */

/**
 * @author Andreas H s185029 og Sascha s171281
 * method for computing rent bases on dice and amount of sodas owned
 */

public class Soda extends Property {

    @Override
    public int computeRent(GameController controller) {
        int count = 0;
        int rent;

        for (Property property : getOwner().getOwnedProperties()) {
            if (property instanceof Soda) {
                count++;
            }
        }
        if (count == 2) {
            rent = ((controller.getDie1() + controller.getDie2()) * 200);
            return rent;

        } else {
            rent = ((controller.getDie1() + controller.getDie2()) * 100);
            return rent;
        }
    }
}