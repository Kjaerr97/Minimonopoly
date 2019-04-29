package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

/**
 * A specific property, which represents a utility which can
 * not be developed with houses or hotels.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Utility extends Property {
    Property property = new Property();

    // TODO to be implemented
    // for computing rent of ferries
    public int rentOfFerry() {

    }

    // for computing rent of sodas / hiv fat i antal øjne. hvordan?
    public int rentOfSoda() {
        if (property.getOwner().getOwnedCards().contains("Squash") &&
                property.getOwner().getOwnedCards().contains("Coca Cola")) {

            setRent((die1 + die2) * 200);

        } else {
            setRent((die1 + die2) * 100);
        }
        return getRent();
    }
}
