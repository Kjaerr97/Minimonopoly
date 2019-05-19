package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;

import dk.dtu.compute.se.pisd.monopoly.mini.controller.GameController;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

/**
 * A specific property, which represents real estate on which houses
 * and hotels can be built.
 * @author Andreas H 185029 og Sascha s171281
 */
public class RealEstate extends Property {

    private int houses;
    private int newRent;
    private int housePrice;

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
        notifyChange();
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
        notifyChange();
    }
    /**
     * Method for computing rent based on amount of houses owned
     * @author Andreas H 185029
     */

@Override
    public int computeRent(GameController controller) {
        if (getHouses() == 0) {
            newRent = getRent();

        } else if (getHouses() >= 1) {
            switch (getHouses()) {
                case 1:
                    newRent = getRent() * 5;
                    break;
                case 2:
                    newRent = getRent() * 12;
                    break;
                case 3:
                    newRent = getRent() * 26;
                    break;
                case 4:
                    newRent = getRent() * 34;
                    break;
            }
        }
        return newRent;
    }
}



