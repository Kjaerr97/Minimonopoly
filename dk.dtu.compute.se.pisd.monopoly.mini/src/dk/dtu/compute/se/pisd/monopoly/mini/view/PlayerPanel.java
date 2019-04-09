package dk.dtu.compute.se.pisd.monopoly.mini.view;

import dk.dtu.compute.se.pisd.monopoly.mini.model.Game;
import dk.dtu.compute.se.pisd.monopoly.mini.model.Player;

import javax.swing.*;
/*
public class PlayerPanel extends JFrame {
    Game game;
    Player player;
    JPanel infoPanel;

    public PlayerPanel (Game game, Player player){
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     //Selve framet bliver konstrueret.
     this.player = player;
     this.game = game;
     this.setSize(800,150);
     this.setLocation(800,game.getPlayers().indexOf(player) * 200);
     this.setTitle(player.getName());
     this.setResizable(false);
     //Panelet
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.X_AXIS));
        this.setContentPane(infoPanel);
        this.validate();
        this.setVisible(true);
        //Spillerens tilstand og navn
        JLabel nameLable = new JLabel(player.getName());
        JLabel balanceLable = new JLabel(String.valueOf(player.getBalance()));
        infoPanel.add(nameLable,balanceLable);
        infoPanel.setBackground(player.getColor());

    }

    public void update(PlayerPanel playerpanel) {
        this.removeAll();

        JLabel nameLable = new JLabel(player.getName());
        JLabel balanceLable = new JLabel(String.valueOf(player.getBalance()));
        infoPanel.add(nameLable,balanceLable);
        infoPanel.setBackground(player.getColor());

        this.revalidate();
        this.repaint();
    }

}*/
