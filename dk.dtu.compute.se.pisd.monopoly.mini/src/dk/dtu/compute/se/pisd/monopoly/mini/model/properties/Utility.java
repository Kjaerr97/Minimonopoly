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
    public int rentOfFerry(){


    }

    // for computing rent of sodas / hiv fat i antal øjne.
    public int rentOfSoda(){
        int rent = 0;
        if(property.getOwner().isInPrison()){
            return 0;
        } else if(property.getOwner().getOwnedCards().contains("Squash") &&
                  property.getOwner().getOwnedCards().contains("Coca Cola")){

            rent = (die1 + die2) * 200;

        }return rent;
    } else{
        rent = (die1 + die2) * 100;
    }
}
