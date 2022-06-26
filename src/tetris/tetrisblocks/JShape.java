package tetris.tetrisblocks;

import tetris.TetrisBlock;

import java.awt.*;

public class JShape extends TetrisBlock {

    public JShape() {
        super(new int[][]{
                {0, 1},
                {0, 1},
                {1, 1}
        }, Color.blue);
    }
}
