package tetris.tetrisblocks;

import tetris.TetrisBlock;

import java.awt.*;

public class OShape extends TetrisBlock {

    public OShape() {
        super(new int[][]{
                {1, 1},
                {1, 1}
        }, Color.red);
    }
}
