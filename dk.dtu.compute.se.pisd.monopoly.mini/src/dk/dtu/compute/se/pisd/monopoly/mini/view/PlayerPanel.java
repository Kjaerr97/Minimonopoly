package dk.dtu.compute.se.pisd.monopoly.mini.view;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import javax.swing.*;

/**
 * @author Markus s174879, Asger s180911
 */

public class PlayerPanel extends JFrame {
    private Game game;
    private Player player;
    private JPanel infoPanel;

    public PlayerPanel (Game game, Player player){
     //Selve framet bliver konstrueret.
     this.game = game;
     this.player = player;
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
    public void update() {
        infoPanel.removeAll();
        JPanel playerPanel = new JPanel();

        playerPanel.setBackground(player.getColor());
        playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));

        JLabel nameLable = new JLabel(player.getName());
        playerPanel.add(nameLable);
        JLabel balanceLable = new JLabel("" + player.getBalance());
        playerPanel.add(balanceLable);

        infoPanel.add(playerPanel);
        this.revalidate();
        this.repaint();
    }
}
