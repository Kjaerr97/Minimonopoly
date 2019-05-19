package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.ColorGroup;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

public class Ferry extends Property {
    /**
     * A specific property that cannot be upgraded with houses
     * @author Andreas H s185029
     *  Method for computing rent of ferries based on amount of ferries owned.
     */


@Override
    public int computeRent(GameController controller) {
        int count = 0;
        int rent = 0;
        for (Property property : getOwner().getOwnedProperties()) {
            if (property instanceof Ferry) {
                count++;
            }
        }
        switch(count){
            case 1:
                rent = 500;
                break;
            case 2:
                rent = 1000;
                break;
            case 3:
                rent = 2000;
                break;
            case 4:
                rent = 4000;
                break;
        }
        return rent;
    }
}
