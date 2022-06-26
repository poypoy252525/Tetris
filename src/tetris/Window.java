package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Window extends JFrame {

    private GamePanel gamePanel;

    //constructor
    public Window() {
        //setting the title of the window to Tetris
        this.setTitle("Tetris");
        //instance of gamePanel
        gamePanel = new GamePanel();
        //placeholder for game panel
        JPanel panel = new JPanel();
        //add the game panel to panel
        panel.add(gamePanel);
        //add the placeholder to the window
        this.add(panel);
        //pack the window so the size of components you add
        // will be the size of the window
        this.pack();
        //set the location to null to generate the window
        // automatically to the center
        this.setLocationRelativeTo(null);
        //set the close operation to exit on close,
        // this means if you click the close button
        //at the upper-right corner it will stop the program.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //other codes here...
        initControls();
        startGame();
    }

    //call it to start the game
    public void startGame() {
        //we directly instance the GameThread and call the start method.
        new GameThread(gamePanel).start();
    }

    //methods for the controls
    private void initControls() {
        //actions whenever we pressed the key
        ActionMap ac = this.getRootPane().getActionMap();
        //for input key value
        InputMap in = this.getRootPane().getInputMap();
        //registering the 'A' key to input map and give an ID of left,
        //and same to the other keys
        in.put(KeyStroke.getKeyStroke("A"), "left");
        in.put(KeyStroke.getKeyStroke("D"), "right");
        in.put(KeyStroke.getKeyStroke("W"), "rotate");
        in.put(KeyStroke.getKeyStroke("S"), "drop");

        //the first argument that you see is the keys
        //that we address in the Input map
        //and the next argument is the the action
        //itself when we pressed the keys
        ac.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //execute all the codes here when pressing the left key
                gamePanel.moveTetrisBlockLeft();
            }
        });
        ac.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //execute all the codes here when pressing the right key
                gamePanel.moveTetrisBlockRight();
            }
        });
        ac.put("rotate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //execute all the codes here when pressing the rotate key
                gamePanel.rotateTetrisBlock();
            }
        });
        ac.put("drop", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //execute all the codes here when pressing the drop key
                gamePanel.dropTetrisBLock();
            }
        });
    }

    public static void main(String[] args) {
        //Window object
        Window window = new Window();
        //set the window visibility to true
        window.setVisible(true);
    }
}
