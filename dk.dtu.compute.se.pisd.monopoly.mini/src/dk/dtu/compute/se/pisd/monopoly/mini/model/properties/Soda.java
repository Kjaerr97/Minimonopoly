package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;
import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

public class Soda extends Property {
    /**
     * A specific property, which represents a utility which can
     * not be developed with houses or hotels.
     *
     * @author Ekkart Kindler, ekki@dtu.dk
     */
    public class Sodas extends Property {

// Andreas - færger og tapperi er nødt til at initialiseres som hver sit objekt og ikke
        // som fælles objekt? ellers kendes der ikke forskel på hvilken af disse to metoder
        // der skal kaldes i doAction i Property-klassen.

        // for computing rent of sodas

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

            } else {
                rent = ((controller.getDie1() + controller.getDie2()) * 100);
            }

            return rent;
        }
    }
}