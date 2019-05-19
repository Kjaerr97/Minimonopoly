package dk.dtu.compute.se.pisd.monopoly.mini.model;

import java.awt.*;

/**
 * @aurthor Sascha s171281
 * Kilde: Fluent Windows 8.1 App Development by Rebecca M. Riordan, page 699.
 */

public enum ColorGroup {

    // The colour and the number of spaces with that colour is defined:

    lightblue(2), salmon(3), green(3), grey(3), red(3), white(3), yellow(3), purple(2), turquoise(2), pink(4);


    private int groupNumber;

    ColorGroup(int groupNumber) {
        this.groupNumber = groupNumber;
    }

    public int GroupNumber() {
        return this.groupNumber;
    }




    //The colours are grouped:

    public static Color color(ColorGroup x) {

        switch (x) {
            case lightblue:
                return new Color(0, 177, 250); //Ligtblue

            case salmon:
                return new Color(244, 169, 159); //Salmon

            case green:
                return new Color(0, 193, 0); //Green

            case grey:
                return new Color(151, 151, 151); //Grey

            case red:
                return new Color(230, 0, 0); //Red

            case white:
                return new Color(255, 255, 255); //White

            case yellow:
                return new Color(255, 255, 0); //Yellow

            case purple:
                return new Color(146, 0, 146); //Purple

            case turquoise:
                return new Color(0, 250, 239); //Turquoise = brewery

            case pink:
                return new Color(255, 0, 127); //Pink = ferry
        }

        return null;
    }
}
