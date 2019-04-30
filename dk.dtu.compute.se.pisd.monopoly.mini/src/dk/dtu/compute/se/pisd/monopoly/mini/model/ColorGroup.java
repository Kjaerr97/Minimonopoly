package dk.dtu.compute.se.pisd.monopoly.mini.model;

import java.awt.*;

/**
 * @aurthor Sascha s171281
 * Kilde: Fluent Windows 8.1 App Development by Rebecca M. Riordan, page 699.
 */

public enum ColorGroup {

    // The colour and the number of spaces with that colour is defined:

    lightblue(2), salmon(3), green(3), grey(3), red(3), white(3), yellow(3), puple(2), darkgreen(2), blue(4);


    private int groupID;


    public int getGroupID() {
        return this.groupID;
    }


    ColorGroup(int groupID) {
        this.groupID = groupID;
    }

    //The colours are grouped:

    public static Color color(ColorGroup x) {

        switch (x) {
            case lightblue:
                return new Color(0, 187, 238); //Ligtblue

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

            case puple:
                return new Color(146, 0, 146); //Purple

            case darkgreen:
                return new Color(0, 60, 0); //Darkgreen = brewery

            case blue:
                return new Color(0, 0, 100); //Blue = ferry
        }

        return null;
    }
}
