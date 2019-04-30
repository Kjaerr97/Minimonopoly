package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

/**
 * A specific property, which represents a utility which can
 * not be developed with houses or hotels.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class Utility extends Property {


    // TODO to be implemented
    // for computing rent of ferries
    public int rentOfFerry() {
        switch()

    }
// Andreas - færger og tapperi er nødt til at initialiseres som hver sit objekt og ikke
    // som fælles objekt? ellers kendes der ikke forskel på hvilken af disse to metoder
    // der skal kaldes i doAction i Property-klassen.

    // for computing rent of sodas
    public int rentOfSoda(GameController controller) {

        if (getOwner().getOwnedCards().contains("Tuborg") &&
                getOwner().getOwnedCards().contains("Carlsberg")) {

            setRent(( controller.getDie1()+ controller.getDie2()) * 200);

        } else {
            setRent((controller.getDie1()+controller.getDie2()) * 100);
        }
        return getRent();
    }
}
