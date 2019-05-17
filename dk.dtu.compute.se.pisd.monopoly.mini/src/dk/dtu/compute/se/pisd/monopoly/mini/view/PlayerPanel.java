package dk.dtu.compute.se.pisd.monopoly.mini.view;

import dk.dtu.compute.se.pisd.monopoly.mini.model.*;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Markus s174879, Asger s180911
 */

public class PlayerPanel extends JFrame {
    private Game game;
    private Player player;
    private JPanel infoPanel;

    private Dimension dimension;

    private Map<ColorGroup, JPanel>colorGroupJPanelMap;

    public PlayerPanel (Game game, Player player){
     //Selve framet bliver konstrueret.
     this.game = game;
     this.player = player;
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     dimension = new Dimension(80,100);

     infoPanel = new JPanel();
     this.setSize(800,150);
     this.setLocation(800,game.getPlayers().indexOf(player) * 200);
     this.setTitle(player.getName());
     this.setResizable(true);
     //Panelet
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.X_AXIS));
        this.setContentPane(infoPanel);
        this.validate();
        this.setVisible(true);
        update();
    }
    //Dette er updatemetoden som skal kaldes hver gang panellerne skal laves.
    //Dette gør at playerpanelet ikke skal laves oppe i infopanelet, men at update-metoden blot skal kaldes hver gang.
    /**
     * @author Markus s174879
     */

    public void update() {
        infoPanel.removeAll();
        JPanel playerPanel = new JPanel();

        colorGroupJPanelMap = new HashMap<>();

        playerPanel.setBackground(player.getColor());
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));

        JLabel nameLable = new JLabel(player.getName());
        playerPanel.add(nameLable);
        JLabel balanceLable = new JLabel("" + player.getBalance());
        playerPanel.add(balanceLable);

        playerPanel.setPreferredSize(dimension);
        playerPanel.setMaximumSize(dimension);

        infoPanel.add(playerPanel);

       /* for (Space space: game.getSpaces()){
            if (space instanceof Property){
                Property property = (Property) space;
                if (property.getOwner() != null){
                    if (property.getOwner() == player){
                        ColorGroup colorGroup = property.getColorGroup();
                        if (!colorGroupJPanelMap.containsKey(colorGroup))
                            try{
                                JPanel jPanel = PanelSkaber(colorGroup);
                                colorGroupJPanelMap.put(colorGroup,jPanel);
                                LabelSkaber(jPanel,property.getName());
                            }catch (NullPointerException x){
                                x.getMessage();
                            }
                        else{
                            JPanel jPanel = colorGroupJPanelMap.get(colorGroup);
                            LabelSkaber(jPanel,property.getName());
                        }
                    }
                }
            }
        }*/

        for (ColorGroup colorGroup: ColorGroup.values()){
            JPanel jPanel = PanelSkaber(colorGroup);
            colorGroupJPanelMap.put(colorGroup,jPanel);
        }


        this.revalidate();
        this.repaint();
    }

    public JPanel PanelSkaber(ColorGroup colour){
        JPanel colorGroupPanel = new JPanel();
        colorGroupPanel.setBackground(ColorGroup.color(colour));
        colorGroupPanel.setLayout(new BoxLayout(colorGroupPanel, BoxLayout.Y_AXIS));
        colorGroupPanel.setBorder(new EtchedBorder());

        colorGroupPanel.setMaximumSize(dimension);
        colorGroupPanel.setPreferredSize(dimension);

        colorGroupPanel.setVisible(true);
        infoPanel.add(colorGroupPanel);

        return colorGroupPanel;
    }

    /*public void LabelSkaber(JPanel jPanel, String name){
        JLabel jLabel = new JLabel(name);
        jPanel.add(jLabel);
    }
*/
}
