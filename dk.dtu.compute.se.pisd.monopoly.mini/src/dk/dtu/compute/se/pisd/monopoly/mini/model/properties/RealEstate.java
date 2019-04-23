package dk.dtu.compute.se.pisd.monopoly.mini.model.properties;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Property;

/**
 * A specific property, which represents real estate on which houses
 * and hotels can be built. Note that this class does not have details
 * yet and needs to be implemented.
 * 
 * @author Ekkart Kindler, ekki@dtu.dk
 *
 */
public class RealEstate extends Property {

    private int houses;
    private int newRent;
    private int houseRent;
    private int housePrice;

    public int getHouses() {
        return houses;
    }

    public void setHouses(int houses) {
        this.houses = houses;
        notifyChange();
    }


    public int getHouseRent() {
        return houseRent;
    }

    public void setHouseRent(int houseRent) {
        this.houseRent = houseRent;
        notifyChange();
    }

    public int getHousePrice() {
        return housePrice;
    }

    public void setHousePrice(int housePrice) {
        this.housePrice = housePrice;
        notifyChange();
    }

//@s185034
public int computeRent() {
    switch (houses) {
        case 1:
            newRent = getRent() + (getRent() / 4);
            break;
        case 2:
            newRent = getRent() + ((getRent() / 4) * 2);
            break;
        case 3:
            newRent = getRent() + ((getRent() / 4) * 3);
            break;
        case 4:
            newRent = getRent() + (getRent());
            break;
    }
    return newRent;
}
}



